/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * ************************************************************
 */
package fr.ocr.view.servlets.topo;

import fr.ocr.business.topo.CtrlMetierTopo;
import fr.ocr.model.entities.DbGrimpeur;
import fr.ocr.model.entities.DbTopo;
import fr.ocr.view.utile.MsgExcpStd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Svt_GestionDemandeResaTopo", urlPatterns = {"/GestionDemandeResaTopo"})
public class Svt_GestionDemandeResaTopo extends HttpServlet {

    private static final long serialVersionUID =1L;
    private final Logger logger;
    private CtrlMetierTopo ctrlMetierTopo;

    public Svt_GestionDemandeResaTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;

        super.service(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String msgResultat = " Demande envoyée ";

            String s = request.getParameter("idValTopo");

            if (s != null) {
                int idDuTopo = Integer.parseInt(s);
                ctrlMetierTopo.accepterResaCeTopo(idDuTopo);
            } else {
                msgResultat = "Rien à Accepter";
            }
            request.setAttribute("msgResultat", msgResultat);
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Object o = request.getSession().getAttribute("dbGrimpeur");

            DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

            if (dbGrimpeur != null) {
                Integer idGrimpeur = dbGrimpeur.getIdgrimpeur();

                List<DbTopo> dbToposEnAttenteResa = ctrlMetierTopo.listerMesDemandeDeResa(idGrimpeur);
                request.setAttribute("dbToposEnAttenteResa", dbToposEnAttenteResa);
                request.setAttribute("dbDemandeDeResa", dbToposEnAttenteResa);

                List<DbTopo> dbToposGrimpeur = ctrlMetierTopo.listerMesTopos(idGrimpeur);
                request.setAttribute("dbToposGrimpeur", dbToposGrimpeur);

                RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ListerDemandeDeResa");
                requestDispatcher.forward(request, response);
            } else {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " Dbgrimpeur = NULL");
                throw new ServletException("dbGrimpeur est null");
            }

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }
}
