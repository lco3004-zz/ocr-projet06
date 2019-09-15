package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.model.entities.DbGrimpeur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "Svt_RestituerTopo", urlPatterns = {"/RestituerTopo"})
public class Svt_RestituerTopo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger;
    private CtrlMetierTopo ctrlMetierTopo;

    public Svt_RestituerTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;
        logger.debug("Hello from :" + this.getClass().getSimpleName() + " methode service appelée");
        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer idDuTopo = Integer.valueOf(request.getParameter("idValTopo"));

            Object o = request.getSession().getAttribute("dbGrimpeur");

            DbGrimpeur grimpeurDemandeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

            ctrlMetierTopo.restituerResaCeTopo(idDuTopo);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");

            requestDispatcher.forward(request, response);

        } catch (Exception e) {

            request.setAttribute("messageErreur", e.getCause() + " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
