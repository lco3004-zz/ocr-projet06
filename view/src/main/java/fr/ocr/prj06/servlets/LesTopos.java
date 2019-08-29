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

@WebServlet(name = "lestopos", urlPatterns = {"/lestopos"})
public class LesTopos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Topos </h3>");
            out.print("<a href=\"listertouslestopos\">Voir tous nos topos</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"ajouteruntopo\">ajouter un topo</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"publieruntopo\">publier un topo</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"demanderresauntopo\">demander réservation topo</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"voirmalistedetopos\">voir ma liste de topos</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"voirtoposenattenteresaok\">voir mes topos en attente de ok réservation</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"voirdetailuntopo\">voir detail un topo</a>");
            out.print(BR.getValeur());

            out.print(HTML_FIN.getValeur());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
