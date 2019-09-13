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
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static fr.ocr.model.constantes.CotationLongueur.SIX_APLUS;
import static fr.ocr.model.constantes.SpotClassification.STANDARD;


@WebServlet(name = "Svt_AjouterSpot",
        urlPatterns = {"/CreerSpot", "/AjouterSpot", "/AjouterSecteur", "/AjouterVoie", "/AjouterLongueur", "/Valider"})

public class Svt_AjouterSpot extends HttpServlet {
    private static final long serialVersionUID =1L;
    private final Logger logger;

    private CtrlMetierSpot ctrlMetierSpot;

    public Svt_AjouterSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;
        super.service(req, resp);
    }

    public DbSpot ajouterSpot(Integer idUser) throws Exception {


        CtrlMetierSpot ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;

        DbSpot dbSpot = new DbSpot();
        DbSecteur dbSecteur = new DbSecteur();
        DbVoie dbVoie = new DbVoie();
        DbLongueur dbLongueur = new DbLongueur();
        DbCommentaire dbCommentaire = new DbCommentaire();

        dbCommentaire.setEstVisible(true);
        dbCommentaire.setTexte("commentaire_par_pgm");
        dbCommentaire.setNom("pas beau le spot");

        dbLongueur.setNom("longueur_par_pgm");
        dbLongueur.setCotation(SIX_APLUS);
        dbLongueur.setNombreDeSpits(12);

        dbVoie.setNom("voie_par_pgm");

        dbSecteur.setNom("secteur_par_pgm");

        dbSpot.setClassification(STANDARD.name());
        dbSpot.setLocalisation("localisation_par_pgm");
        dbSpot.setNom("spot_par_pgm");

        dbSpot.getSecteursByIdspot().add(dbSecteur);

        dbSecteur.getVoiesByIdsecteur().add(dbVoie);

        dbVoie.getLongueursByIdvoie().add(dbLongueur);

        dbSpot.getCommentairesByIdspot().add(dbCommentaire);

        DbSpot dsp = ctrlMetierSpot.ajouterCeSpot(idUser, dbSpot) ;

        return dsp;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String natureRequete = request.getServletPath();
            HttpSession httpSession = request.getSession();
            Object o = request.getSession().getAttribute("dbSpot");
            DbSpot dbSpot = (o instanceof DbSpot) ? (DbSpot) o : null;
            DbSecteur dbSecteur = null;
            DbVoie dbVoie = null;
            DbLongueur dbLongueur = null;
            Integer i;
            Integer idDuSecteur;
            Integer idDeLaVoie;
            ArrayList<DbSecteur> dbSecteurs;
            ArrayList<DbVoie> dbVoies;

            if (dbSpot != null) {
                HashMap<String, Cookie> hashMap = new HashMap<>();
                for ( Cookie cookie : request.getCookies()) {
                    hashMap.put(cookie.getName(), cookie);
                }
                if (hashMap.size() > 0) {
                    Cookie cookie;
                    switch (natureRequete) {
                        case "AjouterSpot":

                            dbSpot.setLocalisation(request.getParameter("localisationSpot"));
                            dbSpot.setNom(request.getParameter("nomSpot"));

                            request.setAttribute("saisieSecteurOk",true);
                            break;
                        case "AjouterSecteur":
                            dbSecteur = new DbSecteur();

                            dbSecteur.setNom(request.getParameter("nomSecteur"));
                            i = Integer.valueOf(hashMap.get("indexSecteur").getValue());
                            dbSecteur.setIdsecteur(i);

                            cookie  = hashMap.get("indexSecteur");
                            cookie.setValue(String.valueOf(i++));

                            dbSpot.getSecteursByIdspot().add(dbSecteur);

                            response.addCookie(cookie);
                            request.setAttribute("saisieVoieOk",true);
                            break;
                        case "AjouterVoie":
                            dbVoie = new DbVoie();
                            idDuSecteur = Integer.valueOf(request.getParameter("idValSecteur"));

                            dbVoie.setNom(request.getParameter("nomVoie"));

                            i = Integer.valueOf(hashMap.get("indexVoie").getValue());
                            dbVoie.setIdvoie(i);

                            dbSecteurs = (ArrayList<DbSecteur>) dbSpot.getSecteursByIdspot();

                            dbSecteur = dbSecteurs.get(idDuSecteur);
                            dbSecteur.getVoiesByIdsecteur().add(dbVoie);

                            cookie  = hashMap.get("indexVoie");
                            cookie.setValue(String.valueOf(i++));


                            request.setAttribute("saisieLongueurOk",true);
                            break;
                        case "AjouterLongeur":
                            dbLongueur = new DbLongueur();

                            dbLongueur.setNom(request.getParameter("nomLongueur"));

                            String s  = request.getParameter("cotationLongueur");
                            JpaConvEnumCotationLgToString jpaConv = new JpaConvEnumCotationLgToString();
                            CotationLongueur cotationLongueur = jpaConv.convertToEntityAttribute(s);
                            dbLongueur.setCotation(cotationLongueur);

                            dbLongueur.setNombreDeSpits(Integer.valueOf(request.getParameter("nbreSpitsLongueur")));


                            idDeLaVoie = Integer.valueOf(request.getParameter("idValVoie"));
                            idDuSecteur = Integer.valueOf(request.getParameter("idValSecteur"));

                            dbSecteurs = (ArrayList<DbSecteur>) dbSpot.getSecteursByIdspot();
                            dbSecteur = dbSecteurs.get(idDuSecteur);

                            dbVoies = (ArrayList<DbVoie>) dbSecteur.getVoiesByIdsecteur();

                            dbVoie = dbVoies.get(idDeLaVoie);

                            dbVoie.getLongueursByIdvoie().add(dbLongueur);

                            request.setAttribute("boutonValiderOk",true);
                            break;
                        case "Valider" :
                             o =  request.getSession().getAttribute("dbGrimpeur");

                            DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;
                            if (dbGrimpeur !=null) {
                                ctrlMetierSpot.ajouterCeSpot(dbGrimpeur.getIdgrimpeur(),dbSpot);
                                request.getSession().removeAttribute("dbSpot");
                            }else {
                                logger.debug("Hello from :" + this.getClass().getSimpleName()+" Dbgrimpeur = NULL");
                                throw  new ServletException("dbGrimpeur est null");
                            }
                    }
                } else {
                    logger.debug("Hello from :" + this.getClass().getSimpleName() + " hasMap Cookie esy vide " + natureRequete);
                    throw new ServletException("dbGrimpeur est null");
                }
            } else {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " dbSpot est null " + natureRequete);
                throw new ServletException("dbGrimpeur est null");
            }

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
            HttpSession httpSession = request.getSession();
            if (natureRequete == "CreerSpot") {
                Object o = request.getSession().getAttribute("dbSpot");
                if (o == null) {
                    DbSpot dbSpot = new DbSpot();
                    httpSession.setAttribute("dbSpot", dbSpot);
                    Cookie [] cookies = request.getCookies();
                    if (cookies.length == 0) {
                        response.addCookie(new Cookie("indexSecteur", String.valueOf(0)));
                        response.addCookie(new Cookie("indexVoie", String.valueOf(0)));
                    }else {
                        for (Cookie cookie :cookies) {
                            if (cookie.getName() == "indexSecteur"  || cookie.getName() == "indexVoie" )
                                cookie.setValue(String.valueOf(0));
                        }
                    }
                    RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
                    requestDispatcher.forward(request, response);
                } else {
                    logger.debug("Hello from :" + this.getClass().getSimpleName() + " Objet Session.dbSpot non null " + natureRequete);
                    throw new ServletException("dbSpot n'est pas null");
                }
            } else {
                logger.debug("Hello from :" + this.getClass().getSimpleName() + " Path incorrect " + natureRequete);
                throw new ServletException("path incorrect");
            }
        } catch (Exception e) {
            request.setAttribute("messageErreur", " " + e.getLocalizedMessage() + " " + Arrays.toString(e.getStackTrace()));
            RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_ErrInterne");
            requestDispatcher.forward(request, response);
        }
    }
}
