package fr.ocr.view.servlets.spot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Svt_AcceuilSpot", urlPatterns = {"/AcceuilSpot"})
public class Svt_AcceuilSpot extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Svt_AcceuilSpot() {
        super();
        final Logger logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_GestionSpot");
        requestDispatcher.forward(request, response);

    }
}
