package fr.ocr.prj06.servlets.topo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Permet au grimpeur d'enregistrer/créer/ajouter un topo sur le site",
        name = "Pri_AjouterUnTopo",
        urlPatterns = {"/gestionDesTopos/ajouterUnTopo"},
        initParams = {
                @WebInitParam( description = "rang   servlet dans le plan du site",name = "rang_servlet",value = "2"),
                @WebInitParam(description = "niveau protection servlet", name = "niveau_protection",value = "private")})

public class Pri_AjouterUnTopo extends HttpServlet {

    private static final long serialVersionUID =1L;
    private Integer rangServletPlan;

    @Override
    public void init() {
        this.rangServletPlan =  Integer.valueOf( this.getInitParameter("rang_servlet"));
    }

    public Pri_AjouterUnTopo() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
