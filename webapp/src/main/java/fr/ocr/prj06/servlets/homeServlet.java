package fr.ocr.prj06.servlets;

import fr.ocr.prj06.msghtml.MessageDeBase;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//hello

import static fr.ocr.prj06.msghtml.MessageDeBase.*;

public class homeServlet extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> OnlineStore , votre site multemedia en ligne </h3>");
            out.print("Bonjour : "+request.getSession().getAttribute(CODESESSION_LOGIN.getValeur()) +BR.getValeur());
            out.print(" <a href=\"ajouter-travail-form.html\"> Ajouter un article au catalogue</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"catalogue\">Voir le Catalogue</a>");
            out.print(BR.getValeur());
            out.print("<a  href=\"login.html\">se connecter</a>");
            out.print(BR.getValeur());
            out.print("<a href=\"logout\">se déconnecter</a>");
            out.print(HTML_FIN.getValeur());
            out.flush();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
