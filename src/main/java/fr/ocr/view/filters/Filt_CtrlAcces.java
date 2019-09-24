/*
 * **********************************************************
 * Projet 06
 * Vue : "Filter" en lien avec les méthodes uniquement accessible aux users connectés
 *
 * Le nom de chaque méthode suffit à comprendre sa fonction
 * ************************************************************
 */

package fr.ocr.view.filters;

import fr.ocr.model.entities.DbGrimpeur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@WebFilter(filterName = "Filt_CtrlAcces",
        dispatcherTypes ={DispatcherType.FORWARD,
                DispatcherType.REQUEST,
                DispatcherType.ASYNC,
                DispatcherType.ERROR},
        servletNames = {"Svt_GestionDemandeResaTopo",
                "Svt_EnregistrerTopo",
                "Svt_PublierTopo",
                "Svt_DemanderResaTopo",
                "Svt_AjouterSpot",
                "Svt_AdminSpot"},
        urlPatterns ={"/AcceuilCommenterSpot"} )

public class Filt_CtrlAcces implements Filter {

    private final Logger logger;

    public Filt_CtrlAcces() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException {

        try {
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpServletRequest request = (HttpServletRequest) req;

            RequestDispatcher requestDispatcher;

            logger.debug(this.getClass().getSimpleName() + " doFilter");
            Object o = request.getSession().getAttribute("dbGrimpeur");

            DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

            if (dbGrimpeur == null) {
                logger.debug(this.getClass().getSimpleName() + " doFilter : session non ouverte");
                requestDispatcher = request.getServletContext().getNamedDispatcher("Svt_Connexion");
                requestDispatcher.forward(request, response);
            } else {
                logger.debug(this.getClass().getSimpleName() + " doFilter ;: session ouverte => chain" + " Nom grimpeur =" + dbGrimpeur.getUserName());
            }
            chain.doFilter(req, resp);

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new ServletException(ex);
        }
    }
}
