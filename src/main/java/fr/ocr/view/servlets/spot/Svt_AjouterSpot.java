package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.constantes.CotationLongueur;
import fr.ocr.model.converters.JpaConvEnumCotationLgToString;
import fr.ocr.model.entities.*;
import fr.ocr.view.utile.GestionCookies;
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
import java.util.Arrays;

import static fr.ocr.model.constantes.SpotClassification.STANDARD;
import static fr.ocr.view.utile.ConstantesSvt.*;


@WebServlet(name = "Svt_AjouterSpot",
        urlPatterns = {"/CreerSpot",
                "/AjouterSpot",
                "/AjouterSecteur",
               "/SelectionSecteur","/AjouterVoie",
                "/SelectionVoie","/AjouterLongueur",
                "/Valider"})

public class Svt_AjouterSpot extends HttpServlet {

    private final Logger logger;

    private CtrlMetierSpot ctrlMetierSpot;

    private DataSession pourDataSession;

    private GestionCookies gestionCookies;




    private class DataSession {

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

    public Svt_AjouterSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
        gestionCookies = new GestionCookies();
    }


    private void tr_ExceptionGenerique(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
        request.setAttribute("messageErreur", " " + ex.getLocalizedMessage() + " " + Arrays.toString(ex.getStackTrace()));
        request.getSession().removeAttribute(DATASESSION);
        RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
        requestDispatcher.forward(request, response);
    }

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

                case "/SelectionSecteur":
                    cookie = gestionCookies.setValParamReqIntoCookieSpot(request, IDDUSECTEUR, IDSELECTIONSECTEUR);
                    if (cookie != null) {
                        request.setAttribute("idValSecteur", cookie.getValue());
                        response.addCookie(cookie);
                    }
                    cookie = gestionCookies.resetThisCookie(request, IDDELAVOIE);
                    /*
                    if (cookie != null) {
                        request.setAttribute("idValVoie", cookie.getValue());
                        response.addCookie(cookie);
                    }
                    */
                    request.setAttribute("activerValider", false);
                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;

                case "/SelectionVoie":
                    cookie = gestionCookies.getCookieByName(request, IDDUSECTEUR);
                    if (cookie != null) {
                        request.setAttribute("idValSecteur", cookie.getValue());
                    }
                    cookie = gestionCookies.setValParamReqIntoCookieSpot(request, IDDELAVOIE, IDSELECTIONVOIE);
                    if (cookie != null) {
                        request.setAttribute("idValVoie", cookie.getValue());
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
                    idDuSecteur = gestionCookies.getValParamReqFromCookieSpot(request, IDDUSECTEUR, IDSELECTIONSECTEUR);

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
                    idDuSecteur = gestionCookies.getValParamReqFromCookieSpot(request, IDDUSECTEUR, IDSELECTIONSECTEUR);
                    idDeLaVoie = gestionCookies.getValParamReqFromCookieSpot(request, IDDELAVOIE, IDSELECTIONVOIE);

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
                                dbSecteur.getVoiesByIdsecteur().stream().filter(w-> w.getIdvoie() == idDeLaVoie).forEach((z) -> {z.getLongueursByIdvoie().add(dbLongueur);});
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        String natureRequete = req.getServletPath();
        Object o = req.getSession().getAttribute(DATASESSION);

        if (natureRequete.equals("/CreerSpot")) {

            if (o == null) {
                pourDataSession = new DataSession();
                o = req.getSession().getAttribute("dbGrimpeur");
                DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;
                if (dbGrimpeur != null) {
                    pourDataSession.idGrimpeur = dbGrimpeur.getIdgrimpeur();

                    req.getSession().setAttribute(DATASESSION, pourDataSession);

                    logger.debug("Hello from :" + this.getClass().getSimpleName() + " création session pourDataSession " + natureRequete);
                } else {
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " DbGrimpeur est NULL" + natureRequete);
                }
            }

            gestionCookies.createCookiesSpot(req, resp);
        } else {
            if (o != null) {
                pourDataSession = (o instanceof DataSession) ? (DataSession) o : null;
            } else {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " Session DataSession est NULL" + natureRequete);
            }
        }
        super.service(req, resp);
    }

}
