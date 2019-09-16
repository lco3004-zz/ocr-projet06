package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.entities.DbSpot;
import fr.ocr.view.utile.GestionCookies;
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
import java.util.List;

import static fr.ocr.view.utile.ConstantesSvt.DATASESSION;

@WebServlet(name = "Svt_AcceuilSpot", urlPatterns = {"/AcceuilSpot"})
public class Svt_AcceuilSpot extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Logger logger;

    public Svt_AcceuilSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    private CtrlMetierSpot ctrlMetierSpot;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        Object o = req.getSession().getAttribute(DATASESSION);

        if (o != null) {
            req.getSession().removeAttribute(DATASESSION);
        }
        GestionCookies gestionCookies = new GestionCookies();
        gestionCookies.supprimeCookiesSpot(req,resp);

        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            List<DbSpot> dbSpots = ctrlMetierSpot.listerTousSpots();

            request.setAttribute("dbSpots", dbSpots);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_GestionSpot");
            requestDispatcher.forward(request, response);

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            request.setAttribute("messageErreur", " " + ex.getLocalizedMessage() + " " + Arrays.toString(ex.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
