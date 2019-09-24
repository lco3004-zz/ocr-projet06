/*
 * **********************************************************
 * Projet 06
 * Vue : "Servlet"
 * gère l'ajout d'un spot après avoir construit un arbre spot/secteurs/voies/longeurs en mémoire
 * Gestion index/id des tables  secteurs[], voies[] :
 *  utilise la "session"  pour objet dbSpot et index des tables
 *   et
 * "les cookies" pour les id sélectionnés dans la Jsp
 *
 * fonctionne avec un switch sur URL (++ URL pour la même servlet)
 * ************************************************************
 */

package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.constantes.CotationLongueur;
import fr.ocr.model.converters.JpaConvEnumCotationLgToString;
import fr.ocr.model.entities.*;
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

import static fr.ocr.constantes.ConstantesSvt.*;
import static fr.ocr.model.constantes.SpotClassification.STANDARD;


@WebServlet(name = "Svt_AjouterSpot",
        urlPatterns = {
                "/CreerSpot",
                "/AjouterSpot",
                "/AjouterSecteur",
                "/AjouterSelectionSecteur",
                "/AjouterVoie",
                "/AjouterSelectionVoie",
                "/AjouterLongueur",
                "/Valider"})

public class Svt_AjouterSpot extends HttpServlet {

    private final Logger logger;

    private CtrlMetierSpot ctrlMetierSpot;

    private DataSession pourDataSession;

    private GestionCookies gestionCookies;


    /**
     * instance de DataSession utilisé lors du partage d'infos durant  la Session
     */
    private class DataSession {

        /**
         * nécessaire à la création en mémoire de l'arborescence Spot/secteurS/VoieS/LongeurS
         */
        private int indexSecteur;
        private int indexVoie;
        private int idGrimpeur;
        private DbSpot dbSpot;

        DataSession() {
            indexSecteur = indexVoie = 0;
            idGrimpeur = -1;
            dbSpot = new DbSpot();
            dbSpot.setNom("");
            dbSpot.setLocalisation("");
            dbSpot.setIdspot(-1);
        }
    }

    /**
     * initialise les cookies
     */
    public Svt_AjouterSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
        gestionCookies = new GestionCookies();
    }

    /**
     *
     * centralise le code lors la levée d'exception
     *
     * @param e Exception
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void tr_ExceptionGenerique(Exception e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(DATASESSION);
        (new MsgExcpStd()).execute(this,e,logger,request,response);
    }

    /**
     * renseigne l'attribut ctrlMetierSpot à chaque appel (GET ou POST ,...)
     *
     * enregistre une instance de DataSession en Session si l'URL est "/CreerSpot" : point d'entrée de la suite de fonctions
     *  nécessaires à la création d'un spot (hors commentaire)
     *
     *  sinon, récupère l'objet dBSpot , membre de l'instance de DataSession  depuis l'objet Session
     *
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        String natureRequete = req.getServletPath();
        Object objDtaSess = req.getSession().getAttribute(DATASESSION);

        // début de la séquence de création d'un Spot : reset cookie et session
        if (natureRequete.equals("/CreerSpot")) {

            //reset session
            if (objDtaSess != null) {
                req.getSession().removeAttribute(DATASESSION);
                objDtaSess=null;
            }

            // création Session
            pourDataSession = new DataSession();
            Object objGrimp = req.getSession().getAttribute("dbGrimpeur");
            DbGrimpeur dbGrimpeur = (objGrimp instanceof DbGrimpeur) ? (DbGrimpeur) objGrimp : null;
            if (dbGrimpeur != null) {
                pourDataSession.idGrimpeur = dbGrimpeur.getIdgrimpeur();
                req.getSession().setAttribute(DATASESSION, pourDataSession);
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " création session pourDataSession " + natureRequete);
            } else {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " DbGrimpeur est NULL" + natureRequete);
            }

            // reset / création Cookies
            ArrayList<String> strings = new ArrayList<>();
            strings.add(IDDUSECTEUR);
            strings.add(IDDELAVOIE);
            gestionCookies.supprimeCookies(req,resp,strings);
            gestionCookies.createCookies(resp,strings);

        } else {
            // sinon récupère l'instance de DataSession
            if (objDtaSess != null) {
                pourDataSession = (objDtaSess instanceof DataSession) ? (DataSession) objDtaSess : null;
            } else {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " Session DataSession est NULL" + natureRequete);
            }
        }
        super.service(req, resp);
    }

    /**
     * traite la validation de la saisie - appel méthode business ajout spot (forward "Svt_AcceuilSpot")
     * Recupére l'id du Secteur ou de la Voie selectionné dans la JSP
     *
     * gère les flags d'affichage et valorise les cookies suite saisie user, sur appel URL :
     *   "/CreerSpot"
     *   "/AjouterSelectionSecteur"
     *   "/AjouterSelectionVoie"
     *   "/Valider" : le lien n'est actif que si un spot contient au moins un secteur qui contient au moins une voie
     *   qui contient au moins une longueur
     *
     * Forward "Jsp_AjouterUnSpot" ou "Svt_AcceuilSpot" sur choix URL "/Valider"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String natureRequete = request.getServletPath();
            Cookie cookie;

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");

            request.setAttribute("afficheFormeSpot", false);

            switch (natureRequete) {
                case "/Valider":
                    if (pourDataSession.idGrimpeur != -1) {
                        if (pourDataSession.dbSpot.getIdspot() >= 0) {
                            ctrlMetierSpot.ajouterCeSpot(pourDataSession.idGrimpeur, pourDataSession.dbSpot);
                            requestDispatcher = this.getServletContext().getNamedDispatcher("Svt_AcceuilSpot");
                        } else {
                            logger.warn("Hello from :" + this.getClass().getSimpleName() + " dbGrimpeur est vide " + natureRequete);
                        }
                    } else {
                        logger.warn("Hello from :" + this.getClass().getSimpleName() + " dbGrimpeur est vide " + natureRequete);
                    }
                    break;
                case "/CreerSpot":
                    request.setAttribute("afficheFormeSpot", true);
                    request.setAttribute("activerValider", false);
                    break;

                case "/AjouterSelectionSecteur":
                    cookie = gestionCookies.setValParamReqIntoCookie(request, IDDUSECTEUR, IDSELECTIONSECTEUR);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONSECTEUR, cookie.getValue());
                        response.addCookie(cookie);
                    }
                    gestionCookies.resetThisCookie(request, IDDELAVOIE);
                    request.setAttribute("activerValider", false);
                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;

                case "/AjouterSelectionVoie":
                    cookie = gestionCookies.getCookieByName(request, IDDUSECTEUR);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONSECTEUR, cookie.getValue());
                    }
                    cookie = gestionCookies.setValParamReqIntoCookie(request, IDDELAVOIE, IDSELECTIONVOIE);
                    if (cookie != null) {
                        request.setAttribute(IDSELECTIONVOIE, cookie.getValue());
                        response.addCookie(cookie);
                    }
                    request.setAttribute("activerValider", false);
                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }
            requestDispatcher.forward(request, response);
        } catch (Exception ex) {
            tr_ExceptionGenerique(ex, request, response);
        }
    }

    /**
     *Traite les appels :
     *   "/AjouterSpot", par défaut le Spot est classifier "Standard", les infos saisies sont mise dans l'instance
     *   de DataSession.dbSpot
     *   "/AjouterSecteur" : les infos saisies sont mises dans DataSession.dbSpot."Collection de Secteurs"
     *   "/AjouterVoie" : récupére l'id du secteur qui va contenir cette voie dans le cookie "index secteur"
     *   "/AjouterLongueur" : récupére l'id de la voie qui va contenir  cette longueur dans le cookie "index voie"
     *
     * L'ensemble contruit l'arbre de racine DbSpot et de feuille DbLongeur
     *
     * Forward "Jsp_AjouterUnSpot"
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException levée sur erreur Servlet
     * @throws IOException  levée sur erreur logger
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            DbSecteur dbSecteur;
            DbVoie dbVoie;
            DbLongueur dbLongueur;
            Integer idDuSecteur;
            Integer idDeLaVoie;

            request.setAttribute("afficheFormeSpot",false);

            switch (natureRequete) {
                case "/AjouterSpot":
                        pourDataSession.dbSpot.setLocalisation(request.getParameter("localisationSpot"));
                        pourDataSession.dbSpot.setNom(request.getParameter("nomSpot"));
                        pourDataSession.dbSpot.setClassification(STANDARD.name());
                        pourDataSession.dbSpot.setIdspot(0);
                        request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;

                case "/AjouterSecteur":
                        dbSecteur = new DbSecteur();

                        dbSecteur.setNom(request.getParameter("nomSecteur"));
                        dbSecteur.setIdsecteur(pourDataSession.indexSecteur++);

                        pourDataSession.dbSpot.getSecteursByIdspot().add(dbSecteur);
                        request.setAttribute("dbSpot", pourDataSession.dbSpot);

                    break;

                case "/AjouterVoie":
                    idDuSecteur = gestionCookies.getValParamReqFromCookie(request, IDDUSECTEUR);

                        if (idDuSecteur != null && idDuSecteur >= 0) {

                            request.setAttribute("idValSecteur", idDuSecteur);
                            dbVoie = new DbVoie();

                            dbVoie.setNom(request.getParameter("nomVoie"));

                            dbVoie.setIdvoie(pourDataSession.indexVoie++);

                            dbSecteur = ((ArrayList<DbSecteur>)(pourDataSession.dbSpot.getSecteursByIdspot())).get(idDuSecteur);

                            dbSecteur.getVoiesByIdsecteur().add(dbVoie);

                            request.setAttribute("saisieLongueurOk", true);
                            request.setAttribute("dbVoie", dbVoie);

                        } else {
                            logger.warn("WARN : " + this.getClass().getSimpleName() + " aucune selection de Secteur " + natureRequete);
                        }
                        request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;

                case "/AjouterLongueur":
                    idDuSecteur = gestionCookies.getValParamReqFromCookie(request, IDDUSECTEUR);
                    idDeLaVoie = gestionCookies.getValParamReqFromCookie(request, IDDELAVOIE);

                    if (idDuSecteur != null && idDuSecteur >= 0) {
                        if (idDeLaVoie != null && idDeLaVoie >= 0) {
                            request.setAttribute("idValSecteur", idDuSecteur);
                            request.setAttribute("idValVoie", idDeLaVoie);

                            dbLongueur = new DbLongueur();

                            dbLongueur.setNom(request.getParameter("nomLongueur"));

                            String s = request.getParameter("cotationLongueur");
                            JpaConvEnumCotationLgToString jpaConv = new JpaConvEnumCotationLgToString();
                            CotationLongueur cotationLongueur = jpaConv.convertToEntityAttribute(s);
                            dbLongueur.setCotation(cotationLongueur);

                            dbLongueur.setNombreDeSpits(Integer.valueOf(request.getParameter("nbreSpitsLongueur")));

                            dbSecteur = ((ArrayList<DbSecteur>) (pourDataSession.dbSpot.getSecteursByIdspot())).get(idDuSecteur);
                            //
                            // STREAM . What ELSE ?
                            //
                            if (dbSecteur.getVoiesByIdsecteur().stream().filter(w-> w.getIdvoie() == idDeLaVoie).count() == 1) {
                                dbSecteur.getVoiesByIdsecteur().stream().filter(w-> w.getIdvoie() == idDeLaVoie).forEach((z) -> z.getLongueursByIdvoie().add(dbLongueur));
                                request.setAttribute("activerValider", true);
                            }
                            else {
                                logger.warn("WARN : " + this.getClass().getSimpleName() + " aucune selection de Voie " + natureRequete);
                            }

                        } else {
                            logger.warn("WARN : " + this.getClass().getSimpleName() + " aucune selection de Voie " + natureRequete);
                        }

                    } else {
                        logger.warn("WARN : " + this.getClass().getSimpleName() + " aucune selection de Secteur " + natureRequete);
                    }
                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
            requestDispatcher.forward(request, response);

        } catch (Exception ex) {
            tr_ExceptionGenerique(ex, request, response);
        }
    }
}
