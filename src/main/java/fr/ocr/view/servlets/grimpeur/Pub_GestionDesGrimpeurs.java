package fr.ocr.view.servlets.grimpeur;

import fr.ocr.constantes.MessageDeBase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.constantes.MessageDeBase.*;

@WebServlet(description = "Permet au grimpeur proprietaire du topo d'accepter/refuser une resa de topo" ,
        name = "Pub_GestionDesGrimpeurs",
        urlPatterns = {"/gestionDesGrimpeurs"})


public class Pub_GestionDesGrimpeurs extends HttpServlet {
    private static final long serialVersionUID =1L;



    public Pub_GestionDesGrimpeurs() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {

            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Spots </h3>");

            out.print(BR.getValeur());


            out.print("<a href=\"ConnexionGrimpeur\" >Se connecter</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"InscriptionGrimpeur\">S'inscrire</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"ListerLesGrimpeurs\">Liste des Grimpeurs</a>");
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
