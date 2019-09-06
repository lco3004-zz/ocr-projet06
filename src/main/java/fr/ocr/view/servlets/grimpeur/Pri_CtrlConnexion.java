package fr.ocr.view.servlets.grimpeur;

import fr.ocr.business.grimpeur.CtrlMetierGrimpeur;
import fr.ocr.model.entities.DbGrimpeur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "Pri_CtrlConnexion",
        urlPatterns = {"/PriCtrlConnexion"})

public class Pri_CtrlConnexion extends HttpServlet {
    private static final long serialVersionUID =1L;

    public Pri_CtrlConnexion() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            try {
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
                request.setAttribute("dbGrimpeur", dbGrimpeur.getUserName());
                requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
            } else {
                requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_ErreurGrimpeur");
            }

            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.getSession().removeAttribute("dbGrimpeur");
            request.removeAttribute("dbGrimpeur");

            request.setAttribute("messageErreur"," "+e.getLocalizedMessage()+" "+ Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
