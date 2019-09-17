package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.entities.DbLongueur;
import fr.ocr.model.entities.DbSecteur;
import fr.ocr.model.entities.DbSpot;
import fr.ocr.model.entities.DbVoie;
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

@WebServlet(name = "Svt_AcceuilSpot", urlPatterns = {"/AcceuilSpot",
        "/AcceuilSpot/SelectionSecteur",
        "/AcceuilSpot/SelectionSpot",
        "/AcceuilSpot/SelectionVoie"})

public class Svt_AcceuilSpot extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Logger logger;
    private GestionCookies gestionCookies;
    private  List<DbSpot> dbSpots = null;


    public Svt_AcceuilSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
        gestionCookies = new GestionCookies();
    }

    private CtrlMetierSpot ctrlMetierSpot;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        String natureRequete = req.getServletPath();

        if (natureRequete.equals("/AcceuilSpot")) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(IDDUSPOT);
            strings.add(IDDUSECTEUR);
            strings.add(IDDELAVOIE);

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
    private  Cookie cookieSpotArticleSecteurs(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSPOT, IDSELECTIONSPOT);
        if (cookie != null) {

            int idSelectionSpot = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONSPOT, idSelectionSpot);
            DbSpot dbSpot = ctrlMetierSpot.consulterCeSpot(idSelectionSpot);
            Collection<DbSecteur> dbSecteurs = dbSpot.getSecteursByIdspot();
            request.setAttribute("dbSecteurs",dbSecteurs );

        } else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Spot est NULL" + request.getServletPath());
        }
        return cookie;
    }
    private  Cookie cookieSecteurArticleVoies(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSECTEUR, IDSELECTIONSECTEUR);
        if (cookie != null) {
            int idSelectionSecteur = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONSECTEUR, idSelectionSecteur);

            if (cookieSpotArticleSecteurs(request,response) != null) {
                DbSecteur dbSecteur  = ctrlMetierSpot.consulterCeSecteur(idSelectionSecteur);
                Collection<DbVoie> dbVoies = dbSecteur.getVoiesByIdsecteur();
                request.setAttribute("dbVoies", dbVoies);
            }

        }else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Secteur est tNULL" +  request.getServletPath());
        }
        return cookie;
    }

    private  Cookie cookieVoieArticleLongueur(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDELAVOIE, IDSELECTIONVOIE);
        if (cookie != null) {
            int idSelectionVoie = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONVOIE, idSelectionVoie);
            response.addCookie(cookie);

            if (cookieSecteurArticleVoies(request,response) != null) {
                DbVoie dbVoie = ctrlMetierSpot.consulterCetteVoie(idSelectionVoie);
                Collection<DbLongueur> dbLongueurs = dbVoie.getLongueursByIdvoie();
                request.setAttribute("dbLongueurs", dbLongueurs);
            }
        }else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Voie est tNULL" +  request.getServletPath());
        }
        return cookie;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_GestionSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            Cookie cookie;
            switch (natureRequete) {
                case "/AcceuilSpot":
                    break;

                case "/AcceuilSpot/SelectionSpot":
                    gestionCookies.resetThisCookie(request, IDDUSECTEUR);
                    gestionCookies.resetThisCookie(request, IDDELAVOIE);

                   cookie = cookieSpotArticleSecteurs(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }

                    break;

                case "/AcceuilSpot/SelectionSecteur":
                    gestionCookies.resetThisCookie(request, IDDELAVOIE);

                    cookie =  cookieSecteurArticleVoies(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }

                   break;

                case "/AcceuilSpot/SelectionVoie":

                    cookie =  cookieVoieArticleLongueur(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }
                    break;

                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AcceuilSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }
}
