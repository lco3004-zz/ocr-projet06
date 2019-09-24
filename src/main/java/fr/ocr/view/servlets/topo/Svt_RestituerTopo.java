/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 *  gère la fonction "restitution" du Topo emprunté
 * ************************************************************
 */
package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.model.entities.DbGrimpeur;
import fr.ocr.view.utile.MsgExcpStd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Svt_RestituerTopo", urlPatterns = {"/RestituerTopo"})
public class Svt_RestituerTopo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CtrlMetierTopo ctrlMetierTopo;

    private final Logger logger;
    public Svt_RestituerTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    /**
     * renseigne l'attribut ctrlMetierTopo à chaque appel (GET ou POST ,...)
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;
        logger.debug("Hello from :" + this.getClass().getSimpleName() + " methode service appelée");
        super.service(req, resp);
    }

    /**
     * traitement des appels Post
     * Si le paramètre d'appel idValTopo est vide, ne rien faire
     * Sinon , appel méthode "business" pour modifier statut de ce topo (=> de nouveau dispo pour prêt)
     *
     * L'utilisateur doit être connecté (pas de contrôle car passe par filter qui contrôle cela)
     * si erreur , il ya aura une exception de levée par "Business" et "Model"
     *
     * Forward vers la Servlet "Svt_AcceuilTopo"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String msgResultat = " Demande envoyée ";
            String s = request.getParameter("idValTopo");

            if ( s != null && !s.equals("")) {
                int idDuTopo = Integer.parseInt(request.getParameter("idValTopo"));

                Object o = request.getSession().getAttribute("dbGrimpeur");

                DbGrimpeur grimpeurDemandeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

                ctrlMetierTopo.restituerResaCeTopo(idDuTopo);

            } else {
                msgResultat = "Rien à Restituer !";
            }

            request.setAttribute("msgResultat", msgResultat);
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");

            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

}
