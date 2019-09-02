package fr.ocr.prj06.servlets.spot;

import fr.ocr.prj06.business.spot.CtrlMetierSpot;
import fr.ocr.prj06.entities.DbSpot;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(description = "Servlet qui liste tous les Spots taggés ou non",
        name = "Pub_ListerTousLesSpots",
        urlPatterns = {"/gestionDesSpots/listeCompleteSpots"})

public class Pub_ListerTousLesSpots extends HttpServlet {
    private static final long serialVersionUID =1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try  {
            CtrlMetierSpot ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;
            List<DbSpot> dbSpots = ctrlMetierSpot.listerTousSpots();
            request.setAttribute("dbSpots",dbSpots);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_ListeCompleteSpots");
            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.removeAttribute("dbSpots");
            request.setAttribute("messageErreur"," "+e.getLocalizedMessage()+" "+e.getStackTrace());
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request,response);

        }
    }
}
