package fr.ocr.prj06.servlets.topo;

import fr.ocr.prj06.business.topo.CtrlMetierTopo;
import fr.ocr.prj06.constantes.MessageDeBase;
import fr.ocr.prj06.entities.DbTopo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static fr.ocr.prj06.constantes.MessageDeBase.*;

@WebServlet(description = "Servlet qui liste tous les topos DISPONIBLES (publié et non réservé) du site",
        name = "Pub_ListerTousLesTopos" ,
        urlPatterns = {"/gestionDesTopos/listeCompleteTopos"},
        initParams = {
                @WebInitParam( description = "rang   servlet dans le plan du site",name = "rang_servlet",value = "2"),
                @WebInitParam(description = "niveau protection servlet", name = "niveau_protection",value = "private")})

public class Pub_ListerTousLesTopos extends HttpServlet {

    private static final long serialVersionUID =1L;
    private Integer rangServletPlan;

    @Override
    public void init() {
        this.rangServletPlan =  Integer.valueOf( this.getInitParameter("rang_servlet"));
    }

    public Pub_ListerTousLesTopos() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            List<DbTopo> dbTopos =  CtrlMetierTopo.CTRL_METIER_TOPO.listerTousTopos();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
