package fr.ocr.view.servlets.grimpeur;

import fr.ocr.business.grimpeur.CtrlMetierGrimpeur;
import fr.ocr.model.entities.DbGrimpeur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(description = "Permet au grimpeur proprietaire du topo d'accepter/refuser une resa de topo" ,
            name = "Pri_ListerLesGrimpeurs",
        urlPatterns = {"/ListerLesGrimpeurs"})

public class Pri_ListerLesGrimpeurs extends HttpServlet {

    private static final long serialVersionUID =1L;

    public Pri_ListerLesGrimpeurs() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            CtrlMetierGrimpeur ctrlMetierGrimpeur = CtrlMetierGrimpeur.CTRL_METIER_GRIMPEUR;

            List<DbGrimpeur> dbGrimpeurs = ctrlMetierGrimpeur.listerTousGrimpeurs();

            request.setAttribute("dbGrimpeurs", dbGrimpeurs);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_ListeGrimpeurs");

            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.removeAttribute("dbGrimpeurs");

            request.setAttribute("messageErreur", " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request, response);
        }

    }
}
