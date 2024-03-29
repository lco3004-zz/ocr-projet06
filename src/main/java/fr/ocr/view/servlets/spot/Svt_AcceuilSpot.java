/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * utilise la "session" et "les cookies"
 * ************************************************************
 */


package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.constantes.CotationLongueur;
import fr.ocr.model.constantes.SpotClassification;
import fr.ocr.model.converters.JpaConvEnumClassificationSpToString;
import fr.ocr.model.converters.JpaConvEnumCotationLgToString;
import fr.ocr.model.entities.*;
import fr.ocr.model.utile.JpaCtrlRecherche;
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
import java.util.Date;
import java.util.List;

import static fr.ocr.constantes.ConstantesSvt.*;

@WebServlet(name = "Svt_AcceuilSpot", urlPatterns = {"/AcceuilSpot",
        "/AcceuilSelectionSecteur",
        "/AcceuilSelectionSpot",
        "/AcceuilSelectionVoie",
        "/AcceuilRechercheSpot",
        "/AcceuilCommenterSpot"})

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


    /**
     * renseigne l'attribut ctrlMetierSpot � chaque appel (GET ou POST ,...)
     *
     * r�cup�re tous les spots pour affichage
     *
     * Sur appel URL  "/AcceuilSpot" (point d'entr�e) , reset/cr�ation cookies
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        String natureRequete = req.getServletPath();

        if (natureRequete.equals("/AcceuilSpot") || natureRequete.equals("/AcceuilRechercheSpot")) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(IDDUSPOT);
            strings.add(IDDUSECTEUR);
            strings.add(IDDELAVOIE);

            gestionCookies.supprimeCookies(req, resp, strings);
            gestionCookies.createCookies(resp, strings);
        }

        if ( ! natureRequete.equals("/AcceuilRechercheSpot")) {
            try {
                dbSpots = ctrlMetierSpot.listerTousSpots();
                req.setAttribute("dbSpots", dbSpots);
            } catch (Exception e) {
                (new MsgExcpStd()).execute(this,e,logger,req,resp);
            }
        }

        super.service(req, resp);
    }

    /**
     * � partir id Spot s�lectionn�, r�cup�re la liste des secteurs de ce spot
     *
     * @return Cookie valoris� avec id du secteur s�lectionn�
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception  lev�e sur erreur appel business
     */
    private  Cookie cookieSpotArticleSecteurs(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSPOT, IDSELECTIONSPOT);
        if (cookie != null) {

            int idSelectionSpot = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONSPOT, idSelectionSpot);
            DbSpot dbSpot = ctrlMetierSpot.consulterCeSpot(idSelectionSpot);

            Collection<DbSecteur> dbSecteurs =  ctrlMetierSpot.listerSecteurDuSpot(dbSpot);
            request.setAttribute("dbSecteurs",dbSecteurs );

            Collection<DbCommentaire> dbCommentaires =  ctrlMetierSpot.listerCommentairesDuSpot(dbSpot);
            if (dbCommentaires != null && dbCommentaires.size()>0) {

                request.setAttribute("dbCommentaires",dbCommentaires );
            }

        } else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Spot est NULL" + request.getServletPath());
        }
        return cookie;
    }

    /**
     * � partir id Secteur s�lectionn�, r�cup�re la liste des voies de ce secteur
     *
     * @return Cookie valoris� avec id du secteur s�lectionn�
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception  lev�e sur erreur appel business
     */
    private  Cookie cookieSecteurArticleVoies(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSECTEUR, IDSELECTIONSECTEUR);
        if (cookie != null) {
            int idSelectionSecteur = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONSECTEUR, idSelectionSecteur);

            if (cookieSpotArticleSecteurs(request,response) != null) {
                DbSecteur dbSecteur  = ctrlMetierSpot.consulterCeSecteur(idSelectionSecteur);
                Collection<DbVoie> dbVoies =  ctrlMetierSpot.listerVoiesDuSecteur(dbSecteur);
                request.setAttribute("dbVoies", dbVoies);
            }

        }else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Secteur est tNULL" +  request.getServletPath());
        }
        return cookie;
    }

    /**
     * � partir id Voie s�lectionn�e, r�cup�re la liste des longeurs  de cette voie
     *
     * @return Cookie valoris� avec id de la voie s�lectionn�e
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception  lev�e sur erreur appel business
     */
    private  Cookie cookieVoieArticleLongueurs(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = gestionCookies.setValParamReqIntoCookie(request, IDDELAVOIE, IDSELECTIONVOIE);
        if (cookie != null) {
            int idSelectionVoie = Integer.parseInt(cookie.getValue());
            request.setAttribute(IDSELECTIONVOIE, idSelectionVoie);
            response.addCookie(cookie);

            if (cookieSecteurArticleVoies(request,response) != null) {
                DbVoie dbVoie = ctrlMetierSpot.consulterCetteVoie(idSelectionVoie);

                Collection<DbLongueur> dbLongueurs =   ctrlMetierSpot.listerLongueursDeCetteVoie(dbVoie) ;
                request.setAttribute("dbLongueurs", dbLongueurs);
            }
        }else {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookie Voie est tNULL" +  request.getServletPath());
        }
        return cookie;
    }

    /**
     *
     * Forward "Jsp_GestionSpot"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_GestionSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            (new MsgExcpStd()).execute(this,e,logger,request,response);
        }
    }

    /**
     *G�re URL :
     *   "/AcceuilRechercheSpot",
     *          * charge l'instance de JpaCtrlRecherche avec les �l�ments de recherche saisis
     *          * appel m�thode business "chercherCeSpot"
     *
     *   "/AcceuilSpot" , inhibe l'affichage de la forme de saisie de commentaire
     *
     *   "/AcceuilSelectionSpot" ,
     *          * reset des cookies de selection (id) voies , secteur
     *          * valorise le cookie avec id du spot s�lectionn�
     *          * autorise l'affichage de la forme de saisie de commentaire
     *
     *   "/AcceuilSelectionSecteur"
     *          * reset du cookie de selection (id) voies
     *          * valorise le cookie avec id du secteur s�lectionn�
     *          * autorise l'affichage de la forme de saisie de commentaire
     *
     *    "/AcceuilSelectionVoie"
     *          * valorise le cookie avec id de la voie s�lectionn�e
     *          * autorise l'affichage de la forme de saisie de commentaire
     *
     *    "/AcceuilCommenterSpot"
     *          * r�cup�re l'id du Spot dans cookie "spot"
     *          * ajoute un titre au commentaire "nom du grimpeur + date courante"
     *          * enregistre le commentaire saisie - appel business  ajouterCommentaireCeSpot
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException lev�e sur erreur Servlet
     * @throws IOException  lev�e sur erreur logger
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            Cookie cookie;
            switch (natureRequete) {
                case "/AcceuilRechercheSpot" :
                    JpaCtrlRecherche recherche = JpaCtrlRecherche.JPA_CTRL_RECHERCHE;
                    recherche.clearRecherche();

                    recherche.setNomSpot(request.getParameter("inputNomSpot"));

                    String inputNbreDeSpits = request.getParameter("inputNbreDeSpits");
                    if (inputNbreDeSpits != null && ! inputNbreDeSpits.equals(""))
                        recherche.setNombreSpitsLongeur(Integer.valueOf(inputNbreDeSpits));

                    JpaConvEnumClassificationSpToString jpaConvEnumClassificationSpToString = new JpaConvEnumClassificationSpToString();
                    SpotClassification spotClassification = jpaConvEnumClassificationSpToString.convertToEntityAttribute(request.getParameter("inputClassification"));
                    recherche.setSpotClassification(spotClassification);

                    JpaConvEnumCotationLgToString jpaConv = new JpaConvEnumCotationLgToString();
                    CotationLongueur cotationLongueur = jpaConv.convertToEntityAttribute(request.getParameter("inputCotation"));
                    recherche.setCotationLongueur(cotationLongueur);

                    List<DbSpot> dbSpots = ctrlMetierSpot.chercherCeSpot(recherche);

                    request.setAttribute("dbSpots", dbSpots);
                    break;

                case "/AcceuilSpot":
                    request.setAttribute("voirSaisieCommentaire",false);
                    break;

                case "/AcceuilSelectionSpot":
                    gestionCookies.resetThisCookie(request, IDDUSECTEUR);
                    gestionCookies.resetThisCookie(request, IDDELAVOIE);
                    request.setAttribute("voirSaisieCommentaire",true);
                    cookie = cookieSpotArticleSecteurs(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }
                    break;

                case "/AcceuilSelectionSecteur":
                    gestionCookies.resetThisCookie(request, IDDELAVOIE);
                    request.setAttribute("voirSaisieCommentaire",true);

                    cookie =  cookieSecteurArticleVoies(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }
                   break;

                case "/AcceuilSelectionVoie":
                    request.setAttribute("voirSaisieCommentaire",true);

                    cookie =  cookieVoieArticleLongueurs(request,response);
                    if (cookie != null) {
                        response.addCookie(cookie);
                    }
                    break;

                case "/AcceuilCommenterSpot" :
                    Object objGrimp = request.getSession().getAttribute("dbGrimpeur");
                    DbGrimpeur dbGrimpeur = (objGrimp instanceof DbGrimpeur) ? (DbGrimpeur) objGrimp : null;
                    if (dbGrimpeur != null) {
                        Integer idSpot = gestionCookies.getValParamReqFromCookie(request, IDDUSPOT);
                        if (idSpot != null && idSpot >=0) {
                            String commentaireSpot = request.getParameter("inputcommentaire");
                            String commentaireTitre = dbGrimpeur.getUserName() + ": " + new Date();

                            if (commentaireSpot != null && ! commentaireSpot.equals("")) {
                                ctrlMetierSpot.ajouterCommentaireCeSpot(commentaireSpot,commentaireTitre,idSpot);
                            }
                        }
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
