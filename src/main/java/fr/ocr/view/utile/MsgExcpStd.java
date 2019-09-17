package fr.ocr.view.utile;

import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class MsgExcpStd {

    public void execute(HttpServlet that, Exception e, Logger logger, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.error("ERROR" + this.getClass().getSimpleName() + "  " + e.getLocalizedMessage() + "  " + Arrays.toString(e.getStackTrace()));
        request.setAttribute("messageErreur", " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
        RequestDispatcher requestDispatcher = that.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
        requestDispatcher.forward(request, response);
    }
}
