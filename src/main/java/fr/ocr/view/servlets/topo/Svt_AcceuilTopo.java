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

@WebServlet(name = "Svt_AcceuilTopo", urlPatterns = {"/AcceuilTopo"})
public class Svt_AcceuilTopo extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Logger logger;

    public Svt_AcceuilTopo() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    private CtrlMetierTopo ctrlMetierTopo;

    /**
     * renseigne l'attribut ctrlMetierTopo à chaque appel (GET ou POST ,...)
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;

        super.service(req, resp);
    }

    /**
     * Prépare affichage de "les topos dispos pour résa", "mes topos" , "mes demandes de resa reçues"
     * "mes réservations", "mes topos réservés"
     *
     * concerne user connecté sinon une exception est levée
     *
     * Forward vers Jsp "Jsp_GestionTopo"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<DbTopo> dbTopos = ctrlMetierTopo.listerTousToposDisponibles();

            request.setAttribute("dbTopos", dbTopos);

            Object o = request.getSession().getAttribute("dbGrimpeur");
            DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;

            if (dbGrimpeur != null) {
                List<DbTopo> dbToposGrimpeur = ctrlMetierTopo.listerMesTopos(dbGrimpeur.getIdgrimpeur());
                request.setAttribute("dbToposGrimpeur", dbToposGrimpeur);

                List<DbTopo> dbDemandeDeResa = ctrlMetierTopo.listerMesDemandeDeResa(dbGrimpeur.getIdgrimpeur());
                request.setAttribute("dbDemandeDeResa", dbDemandeDeResa);

                List<DbTopo> dbMesToposReserver = ctrlMetierTopo.listerMesToposReserver(dbGrimpeur.getIdgrimpeur());
                request.setAttribute("dbMesToposReserver", dbMesToposReserver);

                List<DbTopo> dbToposMesReservation = ctrlMetierTopo.listerMesReservationTopo(dbGrimpeur.getIdgrimpeur());
                request.setAttribute("dbToposMesReservation", dbToposMesReservation);

            }

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_GestionTopo");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }
}
