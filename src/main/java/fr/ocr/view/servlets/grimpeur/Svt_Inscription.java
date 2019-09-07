package fr.ocr.view.servlets.grimpeur;

import fr.ocr.business.grimpeur.CtrlMetierGrimpeur;
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


@WebServlet(name = "Svt_Inscription", urlPatterns = {"/Inscription"})

public class Svt_Inscription extends HttpServlet {
    private static final long serialVersionUID =1L;
    private final Logger logger;

    public Svt_Inscription() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

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
