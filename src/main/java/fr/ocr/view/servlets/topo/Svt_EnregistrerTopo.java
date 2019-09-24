/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * gère l'enregistrement d'un topo
 * ************************************************************
 */
package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.model.entities.DbGrimpeur;
import fr.ocr.model.entities.DbTopo;
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


@WebServlet(name = "Svt_EnregistrerTopo", urlPatterns = {"/EnregistrerTopo"})

public class Svt_EnregistrerTopo extends HttpServlet {

    private static final long serialVersionUID =1L;
    private final Logger logger;
    private CtrlMetierTopo ctrlMetierTopo;

    public Svt_EnregistrerTopo() {
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
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;
        logger.debug("Hello from :" + this.getClass().getSimpleName() + " methode service appelée");
        super.service(req, resp);
    }

    /**
     *
     * Récupère les infos saisie par le Grimpeur (nom, lieu, résumé)
     *
     * appel la méthode "business" d'enregistrement du Topo
     *
     * Concerne user connecté (grimpeur, membre) sinon une exception est levée
     *
     * Forward vers la servlet "Svt_AcceuilTopo"
     *
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomTopo = request.getParameter("nomTopo");
            String lieuTopo   = request.getParameter("lieuTopo");
            String resumeTopo = request.getParameter("resumeTopo");

            Object o =  request.getSession().getAttribute("dbGrimpeur");
            DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;
            if (dbGrimpeur !=null) {

                logger.debug("Hello from :" + this.getClass().getSimpleName()+" Dbgrimpeur = " +dbGrimpeur.getUserName());
                DbTopo dbTopo = ctrlMetierTopo.enregistrerCeTopo(dbGrimpeur.getIdgrimpeur(), lieuTopo, nomTopo, resumeTopo);
                RequestDispatcher requestDispatcher;
                requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");
                requestDispatcher.forward(request,response);
            }
            else {
                logger.debug("Hello from :" + this.getClass().getSimpleName()+" Dbgrimpeur = NULL");
                throw  new ServletException("dbGrimpeur est null");
            }

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

    /**
     *
     * Forward vers la JSP "Jsp_AjouterUnTopo"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnTopo");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }
}
