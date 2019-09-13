package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.model.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
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
            Integer i = 0;

            if (dbSpot != null) {
                HashMap<String, String> hashMap = new HashMap<>();
                for (Cookie cookie : request.getCookies()) {
                    hashMap.put(cookie.getName(), cookie.getValue());
                }
                if (hashMap.size() > 0) {
                    switch (natureRequete) {
                        case "AjouterSpot":
                            dbSpot.setClassification(STANDARD.name());
                            dbSpot.setLocalisation(request.getParameter("localisationSpot"));
                            dbSpot.setNom(request.getParameter("nomSpot"));
                            //activer bouton Secteur
                            break;
                        case "AjouterSecteur":
                            dbSecteur = new DbSecteur();
                            dbSecteur.setNom(request.getParameter("nomSecteur"));
                            i = Integer.valueOf(hashMap.get("indexSecteur"));
                            dbSecteur.setIdsecteur(i);
                            dbSpot.getSecteursByIdspot().add(dbSecteur);
                            //activer bouton Voie
                            break;
                        case "AjouterVoie":
                            dbVoie = new DbVoie();
                            //activer bouton longeur
                            break;
                        case "AjouterLongeur":
                            //activer bouton valider
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
            //request.removeAttribute("dbTopo");
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
                    response.addCookie(new Cookie("indexSecteur", String.valueOf(0)));
                    response.addCookie(new Cookie("indexVoie", String.valueOf(0)));

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
