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

@WebServlet(name = "Svt_ListerSpots", urlPatterns = {"/ListerSpots"})

public class Svt_ListerSpots extends HttpServlet {
    private static final long serialVersionUID =1L;

    public Svt_ListerSpots() {
        super();
        final Logger logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try  {
            request.setAttribute("idGrimpeur",null);
            RequestDispatcher rd = this.getServletContext().getNamedDispatcher("Pri_ListerMesSpots");
            rd.forward(request,response);
        } catch (Exception e) {
            request.removeAttribute("idGrimpeur");
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request,response);
        }
    }
}
