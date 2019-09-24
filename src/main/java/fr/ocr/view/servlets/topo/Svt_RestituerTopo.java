/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 *  g�re la fonction "restitution" du Topo emprunt�
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
     * renseigne l'attribut ctrlMetierTopo � chaque appel (GET ou POST ,...)
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;
        logger.debug("Hello from :" + this.getClass().getSimpleName() + " methode service appel�e");
        super.service(req, resp);
    }

    /**
     * traitement des appels Post
     * Si le param�tre d'appel idValTopo est vide, ne rien faire
     * Sinon , appel m�thode "business" pour modifier statut de ce topo (=> de nouveau dispo pour pr�t)
     *
     * L'utilisateur doit �tre connect� (pas de contr�le car passe par filter qui contr�le cela)
     * si erreur , il ya aura une exception de lev�e par "Business" et "Model"
     *
     * Forward vers la Servlet "Svt_AcceuilTopo"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String msgResultat = " Demande envoy�e ";
            String s = request.getParameter("idValTopo");

            if ( s != null && !s.equals("")) {
                int idDuTopo = Integer.parseInt(request.getParameter("idValTopo"));

                Object o = request.getSession().getAttribute("dbGrimpeur");

                DbGrimpeur grimpeurDemandeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

                ctrlMetierTopo.restituerResaCeTopo(idDuTopo);

            } else {
                msgResultat = "Rien � Restituer !";
            }

            request.setAttribute("msgResultat", msgResultat);
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");

            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

}
