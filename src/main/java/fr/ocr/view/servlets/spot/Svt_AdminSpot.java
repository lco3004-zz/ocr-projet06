package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.entities.DbCommentaire;
import fr.ocr.model.entities.DbSpot;
import fr.ocr.view.utile.GestionCookies;
import fr.ocr.view.utile.MsgExcpStd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static fr.ocr.view.utile.ConstantesSvt.*;

@WebServlet(name = "Svt_AdminSpot",
        urlPatterns = {"/AdminSpot","/AdminSelectionSpot","/AdminSupprimerCmt",
                "/AdminSelectModereCommentaire","/AdminModereCommentaire",
                "/AdminTaggerCeSpot"})
public class Svt_AdminSpot extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Logger logger;
    private CtrlMetierSpot ctrlMetierSpot;
    private GestionCookies gestionCookies;
    private List<DbSpot> dbSpots = null;


    public Svt_AdminSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
        gestionCookies = new GestionCookies();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;
        String natureRequete = req.getServletPath();

        if (natureRequete.equals("/AdminSpot")) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(IDDUSPOT);
            strings.add(IDDUSECTEUR);
            strings.add(IDDELAVOIE);
            strings.add(IDDUCOMMENTAIRE);

            gestionCookies.supprimeCookies(req,resp,strings);
            gestionCookies.createCookies(resp,strings);
        }
        try {
            dbSpots = ctrlMetierSpot.listerTousSpots();
            req.setAttribute("dbSpots", dbSpots);

        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,req,resp);
        }

        super.service(req, resp);
    }
    private Cookie cookieSelectCommentaire( HttpServletRequest request, HttpServletResponse response) throws Exception{
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUCOMMENTAIRE, IDSELECTIONCOMMENTAIRE);

        if (cookie != null) {
            int idSelectionCommentaire = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONCOMMENTAIRE, idSelectionCommentaire);
            DbCommentaire dbCommentaire= ctrlMetierSpot.consulterCeCommentaire(idSelectionCommentaire);

            if (dbCommentaire != null ) {
                request.setAttribute("dbCommentaire",dbCommentaire);
            }

        } else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Commentaire est NULL" + request.getServletPath());
        }
        return cookie;

    }

    private  Cookie cookieSpotCommentaire(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSPOT, IDSELECTIONSPOT);
        if (cookie != null) {
            int idSelectionSpot = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONSPOT, idSelectionSpot);
            DbSpot dbSpot = ctrlMetierSpot.consulterCeSpot(idSelectionSpot);

            Collection<DbCommentaire> dbCommentaires =  ctrlMetierSpot.listerCommentairesDuSpot(dbSpot);
            if (dbCommentaires != null && dbCommentaires.size()>0) {
                request.setAttribute("dbCommentaires",dbCommentaires );
            }

        } else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Spot est NULL" + request.getServletPath());
        }
        return cookie;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            Cookie cookie;

            switch (natureRequete) {
                case "/AdminTaggerCeSpot" :
                    Integer idSpot = gestionCookies.getValParamReqFromCookie(request, IDDUSPOT);
                    if (idSpot != null && idSpot>=0) {
                        ctrlMetierSpot.taggerCeSpot(idSpot);
                        dbSpots = ctrlMetierSpot.listerTousSpots();
                        request.setAttribute("dbSpots", dbSpots);
                    }
                    break;
                case "/AdminSupprimerCmt":
                    request.setAttribute("voirSaisieCommentaire",false);
                    String sidValCmt = request.getParameter("idValCmt");
                    if(sidValCmt != null && ! sidValCmt.equals("")) {
                        ctrlMetierSpot.supprimeCeCommentaire(Integer.valueOf(sidValCmt));
                    }
                    break;
                case "/AdminModereCommentaire" :
                    Integer idCommentaire = gestionCookies.getValParamReqFromCookie(request, IDDUCOMMENTAIRE);
                    if (idCommentaire != null && idCommentaire >=0) {
                        String commentaireSpot = request.getParameter("inputcommentaire");
                        if (commentaireSpot != null && ! commentaireSpot.equals("")) {
                            ctrlMetierSpot.modereCeCommentaire(idCommentaire,commentaireSpot);
                        }
                    }
                    break;

                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AdminSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            Cookie cookie;
            request.setAttribute("voirSaisieCommentaire",false);
            switch (natureRequete) {
                case "/AdminSpot":
                    break;
                case "/AdminSelectionSpot":
                    cookie = cookieSpotCommentaire(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }
                    break;

                case "/AdminSelectModereCommentaire" :
                    request.setAttribute("voirSaisieCommentaire",true);
                    cookie = cookieSelectCommentaire(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AdminSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }
}
