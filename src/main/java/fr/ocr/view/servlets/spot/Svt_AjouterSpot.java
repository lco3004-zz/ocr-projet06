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

    private Integer getValParamReqFromCookie(HttpServletRequest request, String nomCookie, String nomParamRequest) throws RuntimeException {

        Integer selectionRadioButton = null;
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nomCookie)) {
                    selectionRadioButton = Integer.valueOf(cookie.getValue());
                    break;
                }
            }

            if (selectionRadioButton == null) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " selectionRadio est vide ");
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);

        }

        return  selectionRadioButton;
    }

    private Cookie setValParamReqIntoCookie(HttpServletRequest request, String nomCookie, String nomParamRequest) throws RuntimeException {
        String selectionRadioButton;
        Cookie valRet=null;
        try {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(nomCookie)) {
                    selectionRadioButton = request.getParameter(nomParamRequest);
                    if (selectionRadioButton != null) {
                        cookie.setValue(selectionRadioButton);
                        valRet = cookie;
                    }
                }
            }

            if (valRet == null) {
                logger.error("Erreur : " + this.getClass().getSimpleName() + " aucun choix de Voie -- idVoie est vide ");
            }

        } catch (Exception ex) {
            logger.error("ERROR" + this.getClass().getSimpleName() + "  " + ex.getLocalizedMessage() + "  " + Arrays.toString(ex.getStackTrace()));
            throw new RuntimeException(ex);

        }

        return valRet;
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
            DbSecteur dbSecteur;
            DbVoie dbVoie;
            DbLongueur dbLongueur;
            Integer idDuSecteur;
            Integer idDeLaVoie;

            request.setAttribute("afficheFormeSpot",false);

            switch (natureRequete) {
                case  "/Valider":
                    try {
                        if (pourDataSession.idGrimpeur != -1 ) {
                            ctrlMetierSpot.ajouterCeSpot(pourDataSession.idGrimpeur, pourDataSession.dbSpot);
                        } else {
                            logger.error("Hello from :" + this.getClass().getSimpleName() + " dbGrimpeur est vide " + natureRequete);
                        }
                    } catch ( Exception e){
                        request.getSession().removeAttribute("dataSession");
                        logger.error("ERROR" + this.getClass().getSimpleName() + "  " + e.getLocalizedMessage() + "  " + Arrays.toString(e.getStackTrace()));
                        throw new RuntimeException(e);
                    }
                    finally {
                        logger.debug("Hello from :" + this.getClass().getSimpleName() + " suppresion session pourDataSession " + natureRequete);
                        request.getSession().removeAttribute("dataSession");
                    }
                    break;

                case "/AjouterSpot":
                    try {
                        pourDataSession.dbSpot.setLocalisation(request.getParameter("localisationSpot"));
                        pourDataSession.dbSpot.setNom(request.getParameter("nomSpot"));
                        pourDataSession.dbSpot.setClassification(STANDARD.name());

                        request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    } catch ( Exception e){
                        logger.error("ERROR" + this.getClass().getSimpleName() + "  " + e.getLocalizedMessage() + "  " + Arrays.toString(e.getStackTrace()));
                        request.getSession().removeAttribute("dataSession");
                        throw new RuntimeException(e);
                    }
                    break;

                case "/AjouterSecteur":
                    try {
                        dbSecteur = new DbSecteur();

                        dbSecteur.setNom(request.getParameter("nomSecteur"));
                        dbSecteur.setIdsecteur(pourDataSession.indexSecteur++);

                        pourDataSession.dbSpot.getSecteursByIdspot().add(dbSecteur);
                        request.setAttribute("dbSpot", pourDataSession.dbSpot);

                    }catch ( Exception e){
                        logger.error("ERROR" + this.getClass().getSimpleName() + "  " + e.getLocalizedMessage() + "  " + Arrays.toString(e.getStackTrace()));
                        request.getSession().removeAttribute("dataSession");
                        throw new RuntimeException(e);
                    }
                    break;

                case "/AjouterVoie":
                    try {
                        idDuSecteur = getValParamReqFromCookie(request,IDSECTEUR, IDSELECTIONSECTEUR );
                        if (idDuSecteur != null && idDuSecteur >= 0) {
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
                    }catch ( Exception e){
                        logger.error("ERROR" + this.getClass().getSimpleName() + "  " + e.getLocalizedMessage() + "  " + Arrays.toString(e.getStackTrace()));
                        request.getSession().removeAttribute("dataSession");
                        throw new RuntimeException(e);
                    }
                    break;

                case "/AjouterLongueur":
                    try {
                        idDuSecteur = getValParamReqFromCookie(request,IDSECTEUR, IDSELECTIONSECTEUR );
                        idDeLaVoie = getValParamReqFromCookie(request,IDVOIE, IDSELECTIONVOIE );

                        if (idDeLaVoie != null && idDeLaVoie >= 0 && idDuSecteur != null && idDuSecteur >= 0) {
                            dbLongueur = new DbLongueur();

                            dbLongueur.setNom(request.getParameter("nomLongueur"));

                            String s = request.getParameter("cotationLongueur");
                            JpaConvEnumCotationLgToString jpaConv = new JpaConvEnumCotationLgToString();
                            CotationLongueur cotationLongueur = jpaConv.convertToEntityAttribute(s);
                            dbLongueur.setCotation(cotationLongueur);

                            dbLongueur.setNombreDeSpits(Integer.valueOf(request.getParameter("nbreSpitsLongueur")));

                            dbSecteur =  ((ArrayList<DbSecteur>)(pourDataSession.dbSpot.getSecteursByIdspot())).get(idDuSecteur);

                            dbVoie = ((ArrayList<DbVoie>) dbSecteur.getVoiesByIdsecteur()).get(idDeLaVoie);

                            dbVoie.getLongueursByIdvoie().add(dbLongueur);

                        } else {
                            logger.warn("WARN : " + this.getClass().getSimpleName() + " aucune selection de Voie " + natureRequete);
                        }
                        request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    }catch ( Exception e){
                        logger.warn("WARN : " + this.getClass().getSimpleName() + " aucune selection de Secteur " + natureRequete);
                        request.getSession().removeAttribute("dataSession");
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }

            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("messageErreur", " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));

            request.getSession().removeAttribute(DATASESSION);
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String natureRequete = request.getServletPath();
            Cookie cookie;
            DbSecteur dbSecteur;
            Integer idDuSecteur;
            request.setAttribute("afficheFormeSpot", false);
            switch (natureRequete) {
                case "/CreerSpot":
                    request.setAttribute("afficheFormeSpot",true);
                    break;

                case "/SelectionSecteur" :
                    cookie =  setValParamReqIntoCookie(request,IDSECTEUR, IDSELECTIONSECTEUR );
                    if (cookie != null) {
                        request.setAttribute("idValSecteur", cookie.getValue());
                        response.addCookie(cookie);
                    }

                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;

                case "/SelectionVoie" :
                    cookie =  setValParamReqIntoCookie(request,IDVOIE, IDSELECTIONVOIE );
                    if (cookie != null) {
                        request.setAttribute("idValVoie", cookie.getValue());
                        response.addCookie(cookie);
                    }

                    request.setAttribute("dbSpot", pourDataSession.dbSpot);
                    break;
                default:
                    logger.error("Erreur : " + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
            }
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            request.getSession().removeAttribute(DATASESSION);
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
