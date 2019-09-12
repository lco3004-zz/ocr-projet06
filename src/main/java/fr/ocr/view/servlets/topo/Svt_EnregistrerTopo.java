package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.model.entities.DbGrimpeur;
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
import java.util.Arrays;


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

            Object o =  request.getSession().getAttribute("dbGrimpeur");

            DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

            if (dbGrimpeur !=null) {

                logger.debug("Hello from :" + this.getClass().getSimpleName()+" Dbgrimpeur = " +dbGrimpeur.getUserName());
                DbTopo dbTopo = ctrlMetierTopo.enregistrerCeTopo(dbGrimpeur.getIdgrimpeur(), lieuTopo, nomTopo, resumeTopo);
                request.setAttribute("dbTopo", dbTopo);
                RequestDispatcher requestDispatcher;
                requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_GestionSpot");
                requestDispatcher.forward(request,response);
            }
            else {
                logger.debug("Hello from :" + this.getClass().getSimpleName()+" Dbgrimpeur = NULL");
                throw  new ServletException("dbGrimpeur est null");
            }

        } catch (Exception e) {
            request.removeAttribute("dbTopo");
            request.setAttribute("messageErreur",e.getCause()+" "+e.getLocalizedMessage()+" "+ Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnTopo");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("messageErreur", " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
