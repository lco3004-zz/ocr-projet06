/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * gère la page d'acceuil
 * ************************************************************
 */

package fr.ocr.view.servlets.acceuil;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.model.entities.DbSpot;
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
import java.util.List;

@WebServlet(name = "Svt_AcceuilSite", urlPatterns = {"/home","/"})
public class Svt_AcceuilSite extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger;

    public Svt_AcceuilSite() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    /**
     * prépare affichage de "tous les spots" et de "tous les topos dispos à la réservation"
     *
     * Forward vers la JSP "Jsp_LandingPage"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CtrlMetierTopo ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;
            CtrlMetierSpot ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

            List<DbSpot> dbSpots = ctrlMetierSpot.listerTousSpots();
            request.setAttribute("dbSpots", dbSpots);

            List<DbTopo> dbTopos = ctrlMetierTopo.listerTousToposDisponibles();
            request.setAttribute("dbTopos", dbTopos);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_LandingPage");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.removeAttribute("dbTopos");
            request.removeAttribute("dbSpots");
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
