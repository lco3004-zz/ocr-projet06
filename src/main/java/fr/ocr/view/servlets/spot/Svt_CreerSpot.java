package fr.ocr.view.servlets.spot;

import fr.ocr.business.spot.CtrlMetierSpot;
import fr.ocr.constantes.MessageDeBase;
import fr.ocr.model.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static fr.ocr.constantes.MessageDeBase.*;
import static fr.ocr.model.constantes.CotationLongueur.SIX_APLUS;
import static fr.ocr.model.constantes.SpotClassification.STANDARD;


@WebServlet(name = "Svt_CreerSpot", urlPatterns = {"/CreerSpot"})

public class Svt_CreerSpot extends HttpServlet {
    private static final long serialVersionUID =1L;
    private final Logger logger;

    public Svt_CreerSpot() {
        super();
        logger = LogManager.getLogger(this.getClass());
        logger.debug("Hello from :" + this.getClass().getSimpleName());
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        try (PrintWriter out = response.getWriter()) {
            try {
                ajouterSpot(2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            response.setContentType(MessageDeBase.CONTENT_TYPE.getValeur());
            out.print(HTML_DEBUT.getValeur());
            out.print("<h3> Les amis de l'escalade : Les Spots </h3>");
            out.print(BR.getValeur());
            out.print(PDEBUT.getValeur());
            out.print("Hello from servlet : " +this.getServletName());
            out.print(PFIN.getValeur());
            out.print(BR.getValeur());

            out.print(HTML_FIN.getValeur());
            out.flush();
        }
    }
}
