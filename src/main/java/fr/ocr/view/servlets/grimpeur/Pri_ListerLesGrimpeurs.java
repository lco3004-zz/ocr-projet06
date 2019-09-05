package fr.ocr.view.servlets.grimpeur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Permet au grimpeur proprietaire du topo d'accepter/refuser une resa de topo" ,
            name = "Pri_ListerLesGrimpeurs",
            urlPatterns = {"/gestionGrimpeurs/ListerLesGrimpeurs"})

public class Pri_ListerLesGrimpeurs extends HttpServlet {

    private static final long serialVersionUID =1L;

    public Pri_ListerLesGrimpeurs() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
