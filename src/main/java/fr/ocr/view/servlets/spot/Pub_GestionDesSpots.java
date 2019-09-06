package fr.ocr.view.servlets.spot;

import fr.ocr.constantes.MessageDeBase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.constantes.MessageDeBase.*;


@WebServlet(description = "Servlet proposant les opérations disponibles sur les Spots",
        name = "Pub_GestionDesSpots",
        urlPatterns = {"/gestionDesSpots"})


public class Pub_GestionDesSpots extends HttpServlet {

    private static final long serialVersionUID =1L;

    public Pub_GestionDesSpots() {

        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        try (PrintWriter out = response.getWriter()) {

            try {
                request.getSession().invalidate();

            }catch (IllegalStateException ex) {
                //pas grave, on fera un log pour info
            }
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Spots </h3>");

            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesSpots/listeCompleteSpots\">Consulter la liste de nos Spots</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesSpots/listerMesSpots\">Consulter la liste de mes Spots </a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesSpots/ajouterUnSpot\">Ajouter un Spot</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesSpots/taggerCeSpot\">Tagger Ce Spot</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesSpots/voirCeSpot\">Voir Ce Spot  en detail</a>");
            out.print(BR.getValeur());
            out.print(BR.getValeur());
            out.print("Veuillez cliquer sur ce lien pour continuer -> ");
            String cheminRet = request.getContextPath() +"/index.jsp";
            out.print("<a href="+ cheminRet +"> <b> Cliquez ici  </b> </a>");
            out.print(HTML_FIN.getValeur());
            out.flush();
        }

    }
}
