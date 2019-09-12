package fr.ocr.view.servlets.topo;

import fr.ocr.constantes.MessageDeBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.constantes.MessageDeBase.*;

@WebServlet(name = "Svt_VoirDetailTopo", urlPatterns = {"/VoirDetailTopo"})
public class Svt_VoirDetailTopo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger;

    public Svt_VoirDetailTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : detail du topo </h3>");
            out.print(BR.getValeur());
            out.print(PDEBUT.getValeur());
            out.print("Hello from servlet : " + this.getServletName());
            Integer idDuTop = Integer.valueOf(request.getParameter("idValTopo"));
            out.print("Hello from servlet : le id topo = " + idDuTop);
            out.print(PFIN.getValeur());
            out.print(BR.getValeur());

            out.print(HTML_FIN.getValeur());
            out.flush();
        }
    }
}
