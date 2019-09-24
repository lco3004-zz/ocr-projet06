/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * gère la connexion d'un utilisateur
 * utilise la "session"
 * ************************************************************
 */


package fr.ocr.view.servlets.grimpeur;

import fr.ocr.business.grimpeur.CtrlMetierGrimpeur;
import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.model.entities.DbGrimpeur;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "Svt_Connexion", urlPatterns = {"/Connexion"})
public class Svt_Connexion extends HttpServlet {
    private static final long serialVersionUID =1L;
    private final Logger logger;

    public Svt_Connexion() {
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
     * Supprime l'attribut de session qui contient l'id Grimpeur, si existe (ne devrait se produire)
     *
     *  appel la méthode connecterCeGrimpeur du "business"
     *
     * Forward "Svt_AcceuilSite" si cnx réussie (avec mise en Session de l'id du grimepur)
     *
     * Forward ves "Jsp_Connexion" si erreur avec msg "erreur cnx"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            try {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " invalidation session");
                HttpSession httpSession = request.getSession();
                httpSession.removeAttribute("dbGrimpeur");
                request.getSession().invalidate();

            } catch (IllegalStateException ex) {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " invalidation session : excpetion" + ex.getLocalizedMessage() );
            }

            CtrlMetierGrimpeur ctrlMetierGrimpeur =  CtrlMetierGrimpeur.CTRL_METIER_GRIMPEUR;

            String nomGrimpeur = request.getParameter("nomGrimpeur");
            String mdpGrimpeur = request.getParameter("mdpGrimpeur");

            DbGrimpeur dbGrimpeur = ctrlMetierGrimpeur.connecterCeGrimpeur(nomGrimpeur, mdpGrimpeur);

            RequestDispatcher requestDispatcher;

            if (dbGrimpeur != null) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("dbGrimpeur", dbGrimpeur);
                request.setAttribute("dbGrimpeur", dbGrimpeur);
                requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilSite");
            } else {
                request.setAttribute("messageCnx", "Erreur : Nom/Mdp incorrect");
                requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_Connexion");
            }

            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.getSession().removeAttribute("dbGrimpeur");
            request.removeAttribute("dbGrimpeur");

            request.setAttribute("messageErreur"," "+e.getLocalizedMessage()+" "+ Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request,response);
        }

    }

    /**
     * Supprime l'attribut de session qui contient l'id Grimpeur, si existe
     * (utile pour se déconnecter)
     *
     * Forward ves "Jsp_Connexion"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            try {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " invalidation session");
                HttpSession httpSession = request.getSession();
                httpSession.removeAttribute("dbGrimpeur");
                request.getSession().invalidate();

            } catch (IllegalStateException ex) {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " invalidation session : excpetion" + ex.getLocalizedMessage() );
            }

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_Connexion");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("messageErreur", " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
