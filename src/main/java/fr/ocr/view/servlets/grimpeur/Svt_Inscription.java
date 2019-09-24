/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * gère l'inscription d'un utilisateur
 * utilise la "session"
 * ************************************************************
 */

package fr.ocr.view.servlets.grimpeur;

import fr.ocr.business.grimpeur.CtrlMetierGrimpeur;
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
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "Svt_Inscription", urlPatterns = {"/Inscription"})

public class Svt_Inscription extends HttpServlet {
    private static final long serialVersionUID =1L;
    private final Logger logger;

    public Svt_Inscription() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    /**
     * Liste les topos dispos à la résa et les spots - pour affichage "aside"
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            CtrlMetierTopo ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;
            CtrlMetierSpot ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

            List<DbSpot> dbSpots = ctrlMetierSpot.listerTousSpots();
            req.setAttribute("dbSpots", dbSpots);

            List<DbTopo> dbTopos = ctrlMetierTopo.listerTousToposDisponibles();
            req.setAttribute("dbTopos", dbTopos);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,req,resp);
        }
        super.service(req, resp);
    }

    /**
     * Traite inscription
     * Si données ok et enregistrment Database ok, forward vers connexion ("Svt_Connexion")
     * Sinon Forward vers "Jsp_ErrCnxOuIns"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            CtrlMetierGrimpeur ctrlMetierGrimpeur =  CtrlMetierGrimpeur.CTRL_METIER_GRIMPEUR;

            String nomGrimpeur = request.getParameter("nomGrimpeur");
            String mdpGrimpeur = request.getParameter("mdpGrimpeur");

            RequestDispatcher requestDispatcher;

            if (ctrlMetierGrimpeur.ajouterGrimpeur(nomGrimpeur, mdpGrimpeur) != null) {
                requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_Connexion");

            } else {
                requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrCnxOuIns");
            }

            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.setAttribute("messageErreur"," "+e.getLocalizedMessage()+" "+ Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request,response);
        }
    }

    /**
     *
     * Forward vers "Jsp_Inscription" ou "Jsp_ErrInterne" si erreur
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_Inscription");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("messageErreur", " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
