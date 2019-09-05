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
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "Pri_CtrlConnexionGrimpeur",
        urlPatterns = {"/gestionGrimpeurs/CtrlConnexionGrimpeur"})

public class Pri_CtrlConnexionGrimpeur extends HttpServlet {
    private static final long serialVersionUID =1L;

    public Pri_CtrlConnexionGrimpeur() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            CtrlMetierGrimpeur ctrlMetierGrimpeur =  CtrlMetierGrimpeur.CTRL_METIER_GRIMPEUR;

            String nomGrimpeur = request.getParameter("nomGrimpeur");
            String mdpGrimpeur = request.getParameter("mdpGrimpeur");

            DbGrimpeur dbGrimpeur = ctrlMetierGrimpeur.ajouterGrimpeur(nomGrimpeur,mdpGrimpeur);

            ArrayList<DbGrimpeur> dbGrimpeurs =new ArrayList<>();

            dbGrimpeurs.add(dbGrimpeur);

            request.setAttribute("dbGrimpeurs",dbGrimpeurs);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_confirmationInscription");

            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.removeAttribute("dbGrimpeurs");

            request.setAttribute("messageErreur"," "+e.getLocalizedMessage()+" "+ Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request,response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
