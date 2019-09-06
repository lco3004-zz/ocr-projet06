package fr.ocr.view.servlets.topo;

import fr.ocr.constantes.MessageDeBase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.constantes.MessageDeBase.*;


@WebServlet(description = "Permet au grimpeur proprietaire du topo d'accepter/refuser une resa de topo" ,
        name = "Pri_AccepterReservationTopo",
        urlPatterns = {"/gestionDesTopos/accepterReservationTopo"})

public class Pri_AccepterReservationTopo extends HttpServlet {

    private static final long serialVersionUID =1L;

    public Pri_AccepterReservationTopo() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Topos </h3>");
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
