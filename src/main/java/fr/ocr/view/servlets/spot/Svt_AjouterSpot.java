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
                "/CreerSecteur","/AjouterSecteur",
                "/CreerVoie","/AjouterVoie",
                "/CreerLongeur","/AjouterLongueur",
                "/Valider"})

public class Svt_AjouterSpot extends HttpServlet {
    private static final long serialVersionUID =1L;
    private final Logger logger;

    private CtrlMetierSpot ctrlMetierSpot;

    private DbSpot dbSpot;
    int indexSecteur;
    int indexVoie;

    public Svt_AjouterSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
    }
    private void initNouveauSpot(){
        dbSpot=new DbSpot();
        indexSecteur=indexVoie=0;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ctrlMetierSpot = CtrlMetierSpot.CTRL_METIER_SPOT;
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


            switch (natureRequete) {
                case "/Valider" :
                    Object o =  request.getSession().getAttribute("dbGrimpeur");
                    DbGrimpeur dbGrimpeur = (o instanceof DbGrimpeur) ? (DbGrimpeur) o : null;
                    if (dbGrimpeur != null)
                        ctrlMetierSpot.ajouterCeSpot(dbGrimpeur.getIdgrimpeur(),dbSpot);
                    else {
                        logger.debug("Hello from :" + this.getClass().getSimpleName() + " dbGrimpeur est null " + natureRequete);
                        throw new ServletException("dbGrimpeur est null");
                    }
                break;
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
            if (natureRequete.equals("/CreerSpot")) {
                initNouveauSpot();
                request.setAttribute("afficheFormeSpot",true);
                RequestDispatcher requestDispatcher = this.getServletContext().getNamedDispatcher("Jsp_AjouterUnSpot");
                requestDispatcher.forward(request, response);
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
