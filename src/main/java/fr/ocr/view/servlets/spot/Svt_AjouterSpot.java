package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.constantes.CotationLongueur;
import fr.ocr.model.converters.JpaConvEnumCotationLgToString;
import fr.ocr.model.entities.*;
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


@WebServlet(name = "Svt_AjouterSpot",
        urlPatterns = {"/CreerSpot",
                "/AjouterSpot",
                "/AjouterSecteur",
               "/SelectionSecteur","/AjouterVoie",
                "/SelectionVoie","/AjouterLongueur",
                "/Valider"})

public class Svt_AjouterSpot extends HttpServlet {

    private static final long serialVersionUID =1L;
    private static final String IDSECTEUR = "idSecteur";
    private static final String IDVOIE = "idVoie"  ;
    private static final String IDSELECTIONSECTEUR = "idValSecteur";


    private final Logger logger;

    private CtrlMetierSpot ctrlMetierSpot;

    private DataSession pourDataSession;

    private final  String DATASESSION = "dataSession";

    private class  DataSession extends DbSpot {
        private  int indexSecteur;
        private  int indexVoie;
        private  int idGrimpeur;

        DataSession() {
            super();
            indexSecteur =indexVoie=0;
            idGrimpeur = -1;
        }

        public int getIdGrimpeur() { return idGrimpeur; }

        public void setIdGrimpeur(int idGrimpeur) { this.idGrimpeur = idGrimpeur; }

        public int getIndexSecteur() { return indexSecteur; }

        public void setIndexSecteur(int indexSecteur) { this.indexSecteur = indexSecteur; }

        public int getIndexVoie() { return indexVoie; }

        public void setIndexVoie(int indexVoie) { this.indexVoie = indexVoie; }
    }


    public Svt_AjouterSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }


/*
    if (request.getCookies() == null) {
        return null;
    }
    for (int i = 0; i < request.getCookies().length; i++) {
        if (request.getCookies()[i].getName().equals(name)) {
            return request.getCookies()[i];
        }
    }
*/
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        String sIdSecteur ="-1";
        String sIdVoie = "-1";
        boolean cookieTrouve = false;

        String natureRequete = req.getServletPath();
        if (natureRequete.equals("/CreerSpot")) {

            Object o = req.getSession().getAttribute(DATASESSION);
            if (o == null) {
                pourDataSession = new DataSession();
                o =  req.getSession().getAttribute("dbGrimpeur");
                DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;
                if (dbGrimpeur != null) {
                    pourDataSession.idGrimpeur = dbGrimpeur.getIdgrimpeur();

                    req.getSession().setAttribute(DATASESSION, pourDataSession);

                    logger.debug("Hello from :" + this.getClass().getSimpleName() + " création session pourDataSession " + natureRequete);
                }
                else  {
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " DbGrimpeur est NULL" + natureRequete);
                }
            }

            Cookie[] cookies = req.getCookies();
            if (req.getCookies() != null) {
                for (Cookie cookie :cookies) {
                    if (cookie.getName() == IDSECTEUR  || cookie.getName() == IDVOIE) {
                        cookie.setValue(String.valueOf(-1));
                        cookieTrouve =true;
                        logger.debug("Hello from :" + this.getClass().getSimpleName() +"Cookie trouvé = "+cookie.getName());
                    }
                }
            }

            if (! cookieTrouve) {
                resp.addCookie(new Cookie(IDSECTEUR, sIdSecteur));
                resp.addCookie(new Cookie(IDVOIE, sIdVoie));
                logger.debug("Hello from :" + this.getClass().getSimpleName() +" Création Cookies  ");
            }
        }
        super.service(req, resp);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            DbSecteur dbSecteur = null;
            DbVoie dbVoie = null;
            DbLongueur dbLongueur = null;
            int i, idDuSecteur, idDeLaVoie;
            ArrayList<DbSecteur> dbSecteurs;
            ArrayList<DbVoie> dbVoies;

            request.setAttribute("afficheFormeSpot",false);

            //pour compiler
            DbSpot dbSpot = new DbSpot();
            int indexSecteur =0, indexVoie =0;

            switch (natureRequete) {
                case "/Valider" :
                    if (pourDataSession.idGrimpeur != -1) {
                        ctrlMetierSpot.ajouterCeSpot(pourDataSession.idGrimpeur,dbSpot);
                        logger.debug("Hello from :" + this.getClass().getSimpleName() + " suppresion session pourDataSession " + natureRequete);
                        request.getSession().removeAttribute("dataSession");
                    }
                    else {
                        logger.error("Hello from :" + this.getClass().getSimpleName() + " dbGrimpeur est vide " + natureRequete);
                    }
                break;
                default: {
                    switch (natureRequete) {
                        case "/AjouterSpot":

                            dbSpot.setLocalisation(request.getParameter("localisationSpot"));
                            dbSpot.setNom(request.getParameter("nomSpot"));
                            dbSpot.setClassification(STANDARD.name());

                            request.setAttribute("saisieSecteurOk",true);
                            request.setAttribute("dbSpot",dbSpot);
                            break;

                        case "/AjouterSecteur":
                            dbSecteur = new DbSecteur();

                            dbSecteur.setNom(request.getParameter("nomSecteur"));
                            dbSecteur.setIdsecteur(indexSecteur++);

                            dbSpot.getSecteursByIdspot().add(dbSecteur);

                            request.setAttribute("dbSpot",dbSpot);
                            request.setAttribute("saisieSecteurOk",true);
                            request.setAttribute("saisieVoieOk",true);
                            break;

                        case "/AjouterVoie":
                            dbVoie = new DbVoie();

                            idDuSecteur = Integer.parseInt(request.getParameter("idValSecteur"));

                            dbVoie.setNom(request.getParameter("nomVoie"));

                            dbVoie.setIdvoie(indexVoie++);

                            dbSecteurs = (ArrayList<DbSecteur>) dbSpot.getSecteursByIdspot();

                            dbSecteur = dbSecteurs.get(idDuSecteur);
                            dbSecteur.getVoiesByIdsecteur().add(dbVoie);


                            request.setAttribute("dbSecteur",dbSecteur);
                            request.setAttribute("dbSpot",dbSpot);
                            request.setAttribute("saisieSecteurOk",true);
                            request.setAttribute("saisieVoieOk",true);
                            request.setAttribute("saisieLongueurOk",true);
                            break;

                        case "/AjouterLongeur":
                            dbLongueur = new DbLongueur();

                            dbLongueur.setNom(request.getParameter("nomLongueur"));

                            String s  = request.getParameter("cotationLongueur");
                            JpaConvEnumCotationLgToString jpaConv = new JpaConvEnumCotationLgToString();
                            CotationLongueur cotationLongueur = jpaConv.convertToEntityAttribute(s);
                            dbLongueur.setCotation(cotationLongueur);

                            dbLongueur.setNombreDeSpits(Integer.valueOf(request.getParameter("nbreSpitsLongueur")));

                            idDeLaVoie = Integer.parseInt(request.getParameter("idValVoie"));
                            idDuSecteur = Integer.parseInt(request.getParameter("idValSecteur"));

                            dbSecteurs = (ArrayList<DbSecteur>) dbSpot.getSecteursByIdspot();
                            dbSecteur = dbSecteurs.get(idDuSecteur);

                            dbVoies = (ArrayList<DbVoie>) dbSecteur.getVoiesByIdsecteur();

                            dbVoie = dbVoies.get(idDeLaVoie);

                            dbVoie.getLongueursByIdvoie().add(dbLongueur);

                            request.setAttribute("dbSpot",dbSpot);
                            request.setAttribute("saisieSecteurOk",true);
                            request.setAttribute("saisieVoieOk",true);
                            request.setAttribute("saisieLongueurOk",true);
                            request.setAttribute("boutonValiderOk",true);
                            break;
                        default:
                            logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
                    }
                }
            }
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.getSession().removeAttribute("dbSpot");
            request.setAttribute("messageErreur", e.getCause() + " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {

            String natureRequete = request.getServletPath();
            switch (natureRequete) {
                case "/CreerSpot":

                    request.setAttribute("afficheFormeSpot",true);

                    RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
                    requestDispatcher.forward(request, response);
                    break;

                case "/SelectionSecteur" :
                    if (request.getCookies() != null) {
                        boolean isTrouvecookie =false;
                        boolean isSetIdSecteur =false;
                        for (int i = 0; i < request.getCookies().length; i++) {
                            if (request.getCookies()[i].getName().equals(IDSECTEUR)) {
                                isTrouvecookie = true;
                                String selectionSecteur = request.getParameter(IDSELECTIONSECTEUR);
                                if (selectionSecteur != null) {
                                    isSetIdSecteur=true;
                                    request.getCookies()[i].setValue(selectionSecteur);
                                }
                            }
                        }
                        if (!isTrouvecookie) {
                            logger.error("Erreur : " + this.getClass().getSimpleName() + " cookie Secteur non trouvé " + natureRequete);
                        }
                        if(!isSetIdSecteur ) {
                            logger.error("Erreur : " + this.getClass().getSimpleName() + " aucun choix de secteur -- idSecteur est vide " + natureRequete);
                        }
                    }
                    else  {
                        logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookies Vide ! " + natureRequete);
                    }
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }
        } catch (Exception e) {
            request.setAttribute("messageErreur", "erreur dans  "+this.getClass().getSimpleName() +" "+ e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
