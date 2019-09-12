package fr.ocr.view.servlets.grimpeur;

import fr.ocr.business.grimpeur.CtrlMetierGrimpeur;
import fr.ocr.model.entities.DbGrimpeur;
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

@WebServlet(name = "Svt_Connexion", urlPatterns = {"/Connexion"})
public class Svt_Connexion extends HttpServlet {
    private static final long serialVersionUID =1L;
    private final Logger logger;

    public Svt_Connexion() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            try {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " invalidation session");
                HttpSession httpSession = request.getSession();
                httpSession.removeAttribute("dbGrimpeur");
                request.getSession().invalidate();

            } catch (IllegalStateException ex) {
                //pas grave, on fera un log pour info
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
                requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_LandingPage");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            try {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " invalidation session");
                HttpSession httpSession = request.getSession();
                httpSession.removeAttribute("dbGrimpeur");
                request.getSession().invalidate();

            } catch (IllegalStateException ex) {
                //pas grave, on fera un log pour info
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
