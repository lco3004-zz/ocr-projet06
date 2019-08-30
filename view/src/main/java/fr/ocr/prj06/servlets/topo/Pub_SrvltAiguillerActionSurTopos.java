package fr.ocr.prj06.servlets.topo;

import fr.ocr.prj06.constantes.MessageDeBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.prj06.constantes.MessageDeBase.*;

@WebServlet( description = "Servlet proposant les opérations disponibles sur le stopos",
        name = "Pub_SrvltAiguillerActionSurTopos",
        urlPatterns = {"/gestionDesTopos"},
        initParams = {@WebInitParam(description = "rang servlet dans le plan du site",name = "rang_servlet",value = "1")})

public class Pub_SrvltAiguillerActionSurTopos extends HttpServlet {

    private static final long serialVersionUID =1L;
    private Integer rangServletPlan;

    @Override
    public void init() {
        this.rangServletPlan =  Integer.valueOf( this.getInitParameter("rang_servlet"));
    }

    public Pub_SrvltAiguillerActionSurTopos() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Topos </h3>");
            out.print(PDEBUT.getValeur());
            out.print("--> NiveauServlet :" + rangServletPlan.toString());
            out.print(PFIN.getValeur());
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/listeCompleteTopos\">Voir tous nos topos</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/listerMesTopos\">Voir mes topos</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"WEB-INF/Pri_AjouterUnTopo.html\">Ajouter un Topo</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/partagerUnTopo\">Partager un topo</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/demanderReservationTopo\">Demander la réservation d'un topo</a>");
            out.print(BR.getValeur());

            out.print("<a href=\"gestionDesTopos/accepterReservationTopo\">Accepter une réservation d'un topo</a>");
            out.print(BR.getValeur());

            out.print(HTML_FIN.getValeur());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
