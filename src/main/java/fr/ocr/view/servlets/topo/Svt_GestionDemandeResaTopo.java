/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * g�re la demande de r�servation d'un topo
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

    /**
     * renseigne l'attribut ctrlMetierTopo � chaque appel (GET ou POST ,...)
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierTopo = CtrlMetierTopo.CTRL_METIER_TOPO;

        super.service(req, resp);
    }


    /**
     *  traitement des appels Post
     *  Si le param�tre d'appel idValTopo est vide, ne rien faire
     *  Sinon , appel m�thode "business" pour accepter demande de r�servation
     *
     *  Forward vers la servlet "Svt_AcceuilTopo"
     *
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String msgResultat = " Demande envoy�e ";

            String s = request.getParameter("idValTopo");
            if (s != null && !s.equals("")) {
                int idDuTopo = Integer.parseInt(s);
                ctrlMetierTopo.accepterResaCeTopo(idDuTopo);
            } else {
                msgResultat = "Rien � Accepter";
            }
            request.setAttribute("msgResultat", msgResultat);
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilTopo");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

    /**
     *
     * Pr�pare param�tre pour affichage de "MesTopos", de "Demande"DeResaRe�ues" (en section main et section aside)
     *
     * Concerne utilisateur connect� (sinon, exception est lev�e)
     *
     * Forward vers la JSP "Jsp_ListerDemandeDeResa"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
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
