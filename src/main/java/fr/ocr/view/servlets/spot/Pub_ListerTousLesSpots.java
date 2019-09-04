package fr.ocr.view.servlets.spot;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Servlet qui liste tous les Spots taggés ou non",
        name = "Pub_ListerTousLesSpots",
        urlPatterns = {"/gestionDesSpots/listeCompleteSpots"})

public class Pub_ListerTousLesSpots extends HttpServlet {
    private static final long serialVersionUID =1L;

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
