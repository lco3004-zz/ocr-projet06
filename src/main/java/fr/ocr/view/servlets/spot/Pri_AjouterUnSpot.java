package fr.ocr.view.servlets.spot;

import fr.ocr.utility.constantes.MessageDeBase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.utility.constantes.MessageDeBase.*;


@WebServlet( description = "Permet au grimpeur d'ajouter un spot sur le site",
        name = "Pri_AjouterUnSpot",
        urlPatterns = {"/gestionDesSpots/ajouterUnSpot"})

public class Pri_AjouterUnSpot extends HttpServlet {
    private static final long serialVersionUID =1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Spots </h3>");
            out.print(BR.getValeur());
            out.print(PDEBUT.getValeur());
            out.print("Hello from servlet : " +this.getServletName());
            out.print(PFIN.getValeur());
            out.print(BR.getValeur());

            out.print(HTML_FIN.getValeur());
            out.flush();
        }
    }
}
