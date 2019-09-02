package fr.ocr.prj06.servlets.spot;

import fr.ocr.prj06.constantes.MessageDeBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.prj06.constantes.MessageDeBase.*;

@WebServlet(description = "Servlet proposant les opérations disponibles sur les Spots",
        name = "Pub_GestionDesSpots",
        urlPatterns = {"/gestionDesSpots"})


public class Pub_GestionDesSpots extends HttpServlet {

    private static final long serialVersionUID =1L;

    public Pub_GestionDesSpots() {

        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Spots </h3>");

            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesSpots/listeCompleteSpots\">Consulter la liste de nos Spots</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/listerMesSpots\">Consulter la liste de mes Spots </a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/ajouterUnSpot\">Ajouter un Spot</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/taggerCeSpot\">Tagger Ce Spot</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/voirCeSpot\">Voir Ce Spot  en detail</a>");
            out.print(BR.getValeur());

            out.print(HTML_FIN.getValeur());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
