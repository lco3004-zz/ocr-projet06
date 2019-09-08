package fr.ocr.view.filters;

import fr.ocr.model.entities.DbGrimpeur;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filt_CtrlAcces",
        dispatcherTypes ={DispatcherType.FORWARD,DispatcherType.REQUEST,DispatcherType.ASYNC,DispatcherType.ERROR},
        servletNames = {"Svt_AccepterResaTopo", "Svt_ListeDemandeResaTopo",
                "Svt_EnregistrerTopo", "Svt_PublierTopo", "Svt_DemanderResaTopo" })

public class Filt_CtrlAcces implements Filter {

    private final Logger logger;

    public Filt_CtrlAcces() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletResponse response =(HttpServletResponse)resp;
        HttpServletRequest request  = (HttpServletRequest)req;

        RequestDispatcher requestDispatcher;

        logger.debug(this.getClass().getSimpleName()+" doFilter");

        if ((DbGrimpeur) request.getSession().getAttribute("dbGrimpeur") == null) {
            logger.debug(this.getClass().getSimpleName()+" doFilter : session non ouverte");
            requestDispatcher = request.getServletContext().getNamedDispatcher("Svt_Connexion");
            requestDispatcher.forward(request,response);
        }
        logger.debug(this.getClass().getSimpleName()+" doFilter ;: session ouverte => chain");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        logger.debug("Hello from :" + this.getClass().getSimpleName());

    }

}
