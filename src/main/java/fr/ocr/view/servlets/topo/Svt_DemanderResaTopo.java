package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
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
    private CtrlMetierTopo ctrlMetierTopo;

    public Svt_DemanderResaTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;

        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer idDuTopo = Integer.valueOf(request.getParameter("idValTopo"));
            ctrlMetierTopo.demanderResaCeTopo(idDuTopo);

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
