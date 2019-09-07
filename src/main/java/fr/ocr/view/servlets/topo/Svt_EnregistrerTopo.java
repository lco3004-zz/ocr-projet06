package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.constantes.MessageDeBase;
import fr.ocr.model.entities.DbTopo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static fr.ocr.constantes.MessageDeBase.*;


@WebServlet(name = "Svt_EnregistrerTopo", urlPatterns = {"/EnregistrerTopo"})

public class Svt_EnregistrerTopo extends HttpServlet {

    private static final long serialVersionUID =1L;
    private final Logger logger;


    public Svt_EnregistrerTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomTopo = request.getParameter("nomTopo");

            String lieuTopo   = request.getParameter("lieuTopo");
            String resumeTopo = request.getParameter("resumeTopo");

            CtrlMetierTopo  ctrlMetierTopo =  CtrlMetierTopo.CTRL_METIER_TOPO;

            DbTopo dbTopo = ctrlMetierTopo.enregistrerCeTopo(2, lieuTopo, nomTopo, resumeTopo);
            request.setAttribute("dbTopo", dbTopo.getNom());

            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/Jsp_AcceuilSite.jsp");
            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.removeAttribute("dbTopo");

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
