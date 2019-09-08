package fr.ocr.view.servlets.topo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Svt_ListeDemandeResaTopo", urlPatterns = {"/ListeDemandeResaTopo"})
public class Svt_ListeDemandeResaTopo extends HttpServlet {

    private static final long serialVersionUID =1L;
    private final Logger logger;

    public Svt_ListeDemandeResaTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
