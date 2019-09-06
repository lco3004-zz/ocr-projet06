package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.constantes.MessageDeBase;
import fr.ocr.model.entities.DbTopo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import static fr.ocr.constantes.MessageDeBase.*;


@WebServlet(description = "Permet au grimpeur d'enregistrer/créer/ajouter un topo sur le site",
        name = "Pri_Ctrl_AjouterUnTopo",
        urlPatterns = {"/CtrlAjouterUnTopo"})

public class Pri_Ctrl_AjouterUnTopo extends HttpServlet {

    private static final long serialVersionUID =1L;

    public Pri_Ctrl_AjouterUnTopo() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomTopo = request.getParameter("nomTopo");

            String lieuTopo   = request.getParameter("lieuTopo");
            String resumeTopo = request.getParameter("resumeTopo");

            CtrlMetierTopo  ctrlMetierTopo =  CtrlMetierTopo.CTRL_METIER_TOPO;

            DbTopo dbTopo = ctrlMetierTopo.enregistrerCeTopo(2, lieuTopo, nomTopo, resumeTopo);
            ArrayList<DbTopo> dbTopos =new ArrayList<>();
            dbTopos.add(dbTopo);
            request.setAttribute("dbTopos",dbTopos);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_ListeDesTopos");

            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.removeAttribute("dbTopos");

            request.setAttribute("messageErreur",e.getCause()+" "+e.getLocalizedMessage()+" "+ Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request,response);
        }

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
