package fr.ocr.prj06.servlets;

import fr.ocr.prj06.constantes.MessageDeBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.prj06.constantes.MessageDeBase.*;


@WebServlet(name = "lesspots", urlPatterns = {"/lesspots"}, displayName = "Les topos de la grimpette")
public class LesSpots extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Spots </h3>");
            out.print(BR.getValeur());
            out.print("<a href=\"listetouslesspots\">Voir tous nos Spots</a>");
            out.print(BR.getValeur());
            out.print(HTML_FIN.getValeur());
            out.flush();
        }

    }
}
