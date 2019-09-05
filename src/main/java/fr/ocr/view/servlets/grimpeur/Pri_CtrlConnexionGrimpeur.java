package fr.ocr.view.servlets.grimpeur;

import fr.ocr.utility.constantes.MessageDeBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.utility.constantes.MessageDeBase.*;

@WebServlet(name = "Pri_CtrlConnexionGrimpeur",
        urlPatterns = {"/gestionGrimpeurs/CtrlConnexionGrimpeur"})

public class Pri_CtrlConnexionGrimpeur extends HttpServlet {
    private static final long serialVersionUID =1L;

    public Pri_CtrlConnexionGrimpeur() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Grimpeurs </h3>");
            out.print(BR.getValeur());
            out.print(PDEBUT.getValeur());
            out.print("Hello from servlet : " +this.getServletName());
            out.print(PFIN.getValeur());
            out.print(BR.getValeur());

            out.print(HTML_FIN.getValeur());
            out.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
