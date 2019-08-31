package fr.ocr.prj06.servlets.topo;

import fr.ocr.prj06.business.topo.CtrlMetierTopo;
import fr.ocr.prj06.constantes.MessageDeBase;
import fr.ocr.prj06.entities.DbTopo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static fr.ocr.prj06.constantes.MessageDeBase.*;

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
        try {
            String nomTopo = request.getParameter("nomTopo");

            String lieuTopo   = request.getParameter("lieuTopo");
            String resumeTopo = request.getParameter("resumeTopo");

            CtrlMetierTopo  ctrlMetierTopo =  CtrlMetierTopo.CTRL_METIER_TOPO;

            DbTopo dbTopo = ctrlMetierTopo.enregistrerMonTopo(2, lieuTopo, nomTopo, resumeTopo);
            ArrayList<DbTopo> dbTopos =new ArrayList<>();
            dbTopos.add(dbTopo);
            request.setAttribute("dbTopos",dbTopos);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_confirmationCreationTopo");

            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.removeAttribute("dbTopo");

            request.setAttribute("messageErreur",e.getCause()+" "+e.getLocalizedMessage()+" "+e.getStackTrace());
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
