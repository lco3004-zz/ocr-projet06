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

@WebServlet(description = "Servlet qui liste les spots d'un grimpeur ",
        name =  "Pri_ListerMesSpots",
        urlPatterns = {"/gestionDesSpots/listerMesSpots"})


public class Pri_ListerMesSpots extends HttpServlet {
    private static final long serialVersionUID =1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
