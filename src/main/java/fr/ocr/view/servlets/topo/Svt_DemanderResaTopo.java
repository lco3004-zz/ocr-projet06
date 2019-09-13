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


@WebServlet(name = "Svt_DemanderResaTopo", urlPatterns = {"/DemanderResaTopo"})

public class Svt_DemanderResaTopo extends HttpServlet {

    private static final long serialVersionUID =1L;
    private final Logger logger;


    public Svt_DemanderResaTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    private CtrlMetierTopo ctrlMetierTopo;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;

        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer idDuTopo = Integer.valueOf(request.getParameter("idValTopo"));

            Object o = request.getSession().getAttribute("dbGrimpeur");

            DbGrimpeur grimpeurDemandeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

            ctrlMetierTopo.demanderResaCeTopo(idDuTopo, grimpeurDemandeur.getIdgrimpeur());

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");

            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.removeAttribute("dbTopo");
            request.setAttribute("messageErreur", e.getCause() + " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
