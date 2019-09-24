/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * g�re la demande de r�servation d'un topo
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


@WebServlet(name = "Svt_DemanderResaTopo", urlPatterns = {"/DemanderResaTopo"})

public class Svt_DemanderResaTopo extends HttpServlet {

    private static final long serialVersionUID =1L;
    private final Logger logger;


    public Svt_DemanderResaTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    private CtrlMetierTopo ctrlMetierTopo;

    /**
     * renseigne l'attribut ctrlMetierTopo � chaque appel (GET ou POST ,...)
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;

        super.service(req, resp);
    }

    /**
     * appel la m�thode business de changement de statut "demande r�sa" d'un topo
     * si le param�tre "idValTopo", id du topo objet de la demande", est null ou vide . pas d'action
     *
     * Forward vers la JSP "Jsp_AjouterUnTopo"
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
                int idDuTopo = Integer.parseInt(s);

                Object o = request.getSession().getAttribute("dbGrimpeur");
                DbGrimpeur grimpeurDemandeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;
                if (grimpeurDemandeur !=null) {
                    ctrlMetierTopo.demanderResaCeTopo(idDuTopo, grimpeurDemandeur.getIdgrimpeur());
                }
                else {
                    logger.debug("Hello from :" + this.getClass().getSimpleName()+" Dbgrimpeur = NULL");
                    throw  new ServletException("dbGrimpeur est null");
                }
            } else {
                msgResultat = "Rien � r�server !";
            }
            request.setAttribute("msgResultat", msgResultat);
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");

            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

}
