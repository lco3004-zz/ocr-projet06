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
    private static final String IDSELECTIONVOIE = "idValVoie";
    private final  String DATASESSION = "dataSession";

    private final Logger logger;

    private CtrlMetierSpot ctrlMetierSpot;

    private DataSession pourDataSession;


    private class  DataSession   {
        private  int indexSecteur;
        private  int indexVoie;
        private  int idGrimpeur;
        private  DbSpot dbSpot;

        DataSession() {
            indexSecteur =indexVoie=0;
            idGrimpeur = -1;
            dbSpot = new DbSpot();
        }

        public DbSpot getDbSpot() { return dbSpot; }

        public void setDbSpot(DbSpot dbSpot) { this.dbSpot = dbSpot; }

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

    private String enregistrerCookie(HttpServletRequest request,Cookie [] cookies, String nomCookie, String nomParamRequest, String pathRequete) {
        String selectionSecteur ="";

        if (cookies != null) {
            boolean isTrouvecookie =false;
            boolean isSetIdSecteur =false;

            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(nomCookie)) {
                    isTrouvecookie = true;
                     selectionSecteur = request.getParameter(nomParamRequest);
                    if (selectionSecteur != null) {
                        isSetIdSecteur=true;
                        cookies[i].setValue(selectionSecteur);
                    }
                }
            }
            if (!isTrouvecookie) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " cookie Voie non trouvé " + pathRequete);
            }
            if(!isSetIdSecteur ) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " aucun choix de Voie -- idVoie est vide " + pathRequete);
            }
        }
        else  {
            logger.error("Erreur : " + this.getClass().getSimpleName() + " Cookies Vide ! " + pathRequete);
        }
        return selectionSecteur;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        String sIdSecteur ="-1";
        String sIdVoie = "-1";
        boolean cookieTrouve = false;

        String natureRequete = req.getServletPath();
        Object o = req.getSession().getAttribute(DATASESSION);

        if (natureRequete.equals("/CreerSpot")) {

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
        else {
            if (o != null) {
                pourDataSession = (o instanceof DataSession) ? (DataSession) o : null;
            }
            else {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " Session DataSession est NULL" + natureRequete);
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


            if ("/Valider".equals(natureRequete)) {
                if (pourDataSession.idGrimpeur != -1) {
                    ctrlMetierSpot.ajouterCeSpot(pourDataSession.idGrimpeur, pourDataSession.dbSpot);
                    logger.debug("Hello from :" + this.getClass().getSimpleName() + " suppresion session pourDataSession " + natureRequete);
                    request.getSession().removeAttribute("dataSession");
                } else {
                    logger.error("Hello from :" + this.getClass().getSimpleName() + " dbGrimpeur est vide " + natureRequete);
                }
            } else {
                switch (natureRequete) {
                    case "/AjouterSpot":

                        pourDataSession.dbSpot.setLocalisation(request.getParameter("localisationSpot"));
                        pourDataSession.dbSpot.setNom(request.getParameter("nomSpot"));
                        pourDataSession.dbSpot.setClassification(STANDARD.name());

                        request.setAttribute("saisieSecteurOk", true);
                        request.setAttribute("dbSpot", pourDataSession.dbSpot);
                        break;

                    case "/AjouterSecteur":
                        dbSecteur = new DbSecteur();

                        dbSecteur.setNom(request.getParameter("nomSecteur"));
                        dbSecteur.setIdsecteur(pourDataSession.indexSecteur++);

                        pourDataSession.dbSpot.getSecteursByIdspot().add(dbSecteur);

                        request.setAttribute("dbSpot", pourDataSession.dbSpot);
                        request.setAttribute("saisieSecteurOk", true);
                        request.setAttribute("saisieVoieOk", true);
                        break;

                    case "/AjouterVoie":
                        String sIdValSecteur =  request.getParameter("idValSecteur");
                        if (sIdValSecteur != null) {
                            dbVoie = new DbVoie();

                            idDuSecteur = Integer.parseInt(sIdValSecteur);

                            dbVoie.setNom(request.getParameter("nomVoie"));

                            dbVoie.setIdvoie(pourDataSession.indexVoie++);

                            dbSecteurs = (ArrayList<DbSecteur>) pourDataSession.dbSpot.getSecteursByIdspot();

                            dbSecteur = dbSecteurs.get(idDuSecteur);
                            dbSecteur.getVoiesByIdsecteur().add(dbVoie);


                            request.setAttribute("dbSecteur", dbSecteur);
                            request.setAttribute("dbSpot", pourDataSession.dbSpot);
                            request.setAttribute("saisieSecteurOk", true);
                            request.setAttribute("saisieVoieOk", true);
                            request.setAttribute("saisieLongueurOk", true);

                        } else {
                            logger.warn("WArn : " + this.getClass().getSimpleName() + " aucune selection de Secteur " + natureRequete);
                        }
                        break;

                    case "/AjouterLongeur":
                        dbLongueur = new DbLongueur();

                        dbLongueur.setNom(request.getParameter("nomLongueur"));

                        String s = request.getParameter("cotationLongueur");
                        JpaConvEnumCotationLgToString jpaConv = new JpaConvEnumCotationLgToString();
                        CotationLongueur cotationLongueur = jpaConv.convertToEntityAttribute(s);
                        dbLongueur.setCotation(cotationLongueur);

                        dbLongueur.setNombreDeSpits(Integer.valueOf(request.getParameter("nbreSpitsLongueur")));

                        idDeLaVoie = Integer.parseInt(request.getParameter("idValVoie"));
                        idDuSecteur = Integer.parseInt(request.getParameter("idValSecteur"));

                        dbSecteurs = (ArrayList<DbSecteur>) pourDataSession.dbSpot.getSecteursByIdspot();
                        dbSecteur = dbSecteurs.get(idDuSecteur);

                        dbVoies = (ArrayList<DbVoie>) dbSecteur.getVoiesByIdsecteur();

                        dbVoie = dbVoies.get(idDeLaVoie);

                        dbVoie.getLongueursByIdvoie().add(dbLongueur);

                        request.setAttribute("dbSpot", pourDataSession.dbSpot);
                        request.setAttribute("saisieSecteurOk", true);
                        request.setAttribute("saisieVoieOk", true);
                        request.setAttribute("saisieLongueurOk", true);
                        request.setAttribute("boutonValiderOk", true);
                        break;
                    default:
                        logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
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
                    break;

                case "/SelectionSecteur" :
                    String sIdSecteur = enregistrerCookie(request,request.getCookies(), IDSECTEUR, IDSELECTIONSECTEUR, "/SelectionSecteur" );
                    request.setAttribute("idValSecteur",sIdSecteur);

                    request.setAttribute("afficheFormeSpot",false);
                    request.setAttribute("saisieSecteurOk", true);
                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;

                case "/SelectionVoie" :
                    String sIdVoie =enregistrerCookie(request,request.getCookies(), IDVOIE, IDSELECTIONVOIE, "/SelectionSecteur" );
                    request.setAttribute("idValVoie",sIdVoie);

                    request.setAttribute("afficheFormeSpot",false);
                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    request.setAttribute("saisieSecteurOk", true);
                    request.setAttribute("saisieVoieOk", true);
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("messageErreur", "erreur dans  "+this.getClass().getSimpleName() +" "+ e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
