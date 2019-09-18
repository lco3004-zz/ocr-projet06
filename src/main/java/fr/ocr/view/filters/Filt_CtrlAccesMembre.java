package fr.ocr.view.filters;

import fr.ocr.model.constantes.UserProfile;
import fr.ocr.model.entities.DbGrimpeur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "Filt_CtrlAccesMembre",
        dispatcherTypes ={DispatcherType.FORWARD,DispatcherType.REQUEST,DispatcherType.ASYNC,DispatcherType.ERROR},
        servletNames = {"Svt_AdminSpot"})
public class Filt_CtrlAccesMembre implements Filter {

    private final Logger logger;

    public Filt_CtrlAccesMembre() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        try {
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpServletRequest request = (HttpServletRequest) req;
            RequestDispatcher requestDispatcher;

            logger.debug(this.getClass().getSimpleName() + " doFilterMembre");
            Object o = request.getSession().getAttribute("dbGrimpeur");

            DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

            if (dbGrimpeur == null || dbGrimpeur.getRoleName() != UserProfile.MEMBRE) {
                logger.debug(this.getClass().getSimpleName() + " doFilter : session non ouverte ou non connecté Membre");
                requestDispatcher = request.getServletContext().getNamedDispatcher("Svt_Connexion");
                requestDispatcher.forward(request, response);
            }else {
                logger.debug(this.getClass().getSimpleName() + " doFilter ;: session ouverte => chain" + " Nom grimpeur =" + dbGrimpeur.getUserName());
            }
            chain.doFilter(req, resp);

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new ServletException(ex);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        try {
            logger.debug("Hello from :" + this.getClass().getSimpleName());

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new ServletException(ex);
        }


    }

}
