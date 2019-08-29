package fr.ocr.prj06.servlets;

import fr.ocr.prj06.business.BusinessMgmt;
import fr.ocr.prj06.constantes.MessageDeBase;
import fr.ocr.prj06.entities.DbGrimpeur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.prj06.constantes.MessageDeBase.*;

@WebServlet(name = "tlisteSpot", urlPatterns = {"/listeSpot"})
public class tlisteSpot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            DbGrimpeur[] dbGrimpeurs = new DbGrimpeur[2];
            dbGrimpeurs[0] = new DbGrimpeur();
            dbGrimpeurs[0].setNom("Le Grimpeur à la main");
            BusinessMgmt businessMgmt =new BusinessMgmt();
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<p>"+ dbGrimpeurs[0].toString() + " </p>");
            out.print(BR.getValeur());
            out.print(BR.getValeur());
            out.print(HTML_FIN.getValeur());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
