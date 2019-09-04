
import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.prj06.entities.DbSpot;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(description = "Servlet qui liste les spots d'un grimpeur ",
        name =  "Pri_ListerMesSpots",
        urlPatterns = {"/gestionDesSpots/listerMesSpots"})


public class Pri_ListerMesSpots extends HttpServlet {
    private static final long serialVersionUID =1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try  {
            CtrlMetierSpot ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;
            List<DbSpot> dbSpots=null;
            Integer idGrimpeur =null;

            if ( request.getAttribute("idGrimpeur") != null) {
                idGrimpeur =  (Integer)request.getAttribute("idGrimpeur");
            }

            dbSpots = ctrlMetierSpot.listerMesSpots(idGrimpeur);
            request.setAttribute("dbSpots",dbSpots);

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_ListeDesSpots");
            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            request.removeAttribute("dbSpots");
            request.setAttribute("LocalizedMessage","LocalizedMsg : "+e.getLocalizedMessage()+" "+e.getStackTrace());
            request.setAttribute("StackTrace","StackTrace : "+ e.getLocalizedMessage()+" "+e.getStackTrace());
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Pri_PageErreurInterne");
            requestDispatcher.forward(request,response);

        }
    }
}
