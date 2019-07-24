package fr.ocr.prj06.controllers;

import fr.ocr.prj06.msghtml.MessageDeBase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.prj06.msghtml.MessageDeBase.*;

public class homeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Transmission de la paire d'objets request/response à notre JSP */
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/jsp/about.jsp");
        if (requestDispatcher != null) {
            requestDispatcher.forward( request, response );
        } else  {
            try (PrintWriter out = response.getWriter()) {
                response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
                out.print(HTML_DEBUT.getValeur());
                out.print("<h3> OnlineStore , votre site multemedia en ligne </h3>");
                out.print("Bonjour : "+ " requestDispatcher est null" +BR.getValeur());
                out.print(HTML_FIN.getValeur());
                out.flush();
            }
        }
    }
}
