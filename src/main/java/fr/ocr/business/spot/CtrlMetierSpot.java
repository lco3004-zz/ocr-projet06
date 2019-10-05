/*
 * **********************************************************
 * Projet 06
 * Contrôleur : méthode en lien avec les Spots
 *
 * Le nom de chaque méthode suffit à comprendre sa fonction
 * ************************************************************
 */

package fr.ocr.business.spot;

import fr.ocr.model.controllers.JpaCtrlSpot;
import fr.ocr.model.entities.*;
import fr.ocr.model.utile.JpaCtrlRecherche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static fr.ocr.model.constantes.SpotClassification.OFFICIEL;

/*
 ***********************************************************************************
 */
public interface CtrlMetierSpot {

    CtrlMetierSpot CTRL_METIER_SPOT = new CtrlMetierSpot_impl();

    List<DbSpot> listerTousSpots() throws Exception;

    void taggerCeSpot(Integer  idSpot) throws Exception;
    void ajouterCeSpot(Integer idUser, DbSpot dbSpot) throws Exception ;

    DbSpot consulterCeSpot(Integer idSpot) throws  Exception;

    DbSecteur consulterCeSecteur(int idSelectionSecteur) throws Exception;

    DbVoie consulterCetteVoie(int idVoie) throws Exception;

    List<DbSpot> chercherCeSpot(JpaCtrlRecherche recherche) throws Exception;

    void ajouterCommentaireCeSpot(String commentaireSpot, String titre , Integer idSpot) throws Exception;

    Collection<DbSecteur> listerSecteurDuSpot( DbSpot dbSpot) throws Exception;

    Collection<DbCommentaire> listerCommentairesDuSpot(DbSpot dbSpot) throws Exception;

    Collection<DbVoie> listerVoiesDuSecteur(DbSecteur dbSecteur);

    Collection<DbLongueur> listerLongueursDeCetteVoie(DbVoie dbVoie);

    void supprimeCeCommentaire(Integer idCommentaire) throws Exception;

    void modereCeCommentaire(Integer idCommentaire, String txtCommentaire) throws Exception;

    DbCommentaire consulterCeCommentaire(int idSelectionCommentaire) throws Exception;

}

/*
 ***********************************************************************************
 */
class CtrlMetierSpot_impl implements CtrlMetierSpot {

    private JpaCtrlSpot jpaCtrlSpot;

    CtrlMetierSpot_impl() { this.jpaCtrlSpot = jpaCtrlSpot.JPA_CTRL_SPOT; }

    /**
     *
     * @return List DbSpot
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbSpot> listerTousSpots() throws Exception {
        return  jpaCtrlSpot.findListeSpots(null);
    }

    /**
     *
     * @param idSpot Integer, id du Spot
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void taggerCeSpot(Integer idSpot) throws Exception {
        jpaCtrlSpot.updateSpot(idSpot, OFFICIEL.name());
    }

    /**
     *
     * @param idUser Integer, id du grimpeur qui crée le spot
     * @param oldDbSpot DbSpot qui contient les infos saisies par le grimpeur
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void ajouterCeSpot(Integer idUser, DbSpot oldDbSpot) throws Exception {
        jpaCtrlSpot.createSpot(idUser, oldDbSpot);
    }

    /**
     *
     * @param idSpot  Integer, id du Spot concerné
     * @return DbSpot , objet renseigné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public DbSpot consulterCeSpot(Integer idSpot) throws Exception {
        return jpaCtrlSpot.readSpot(idSpot);
    }

    /**
     *
     * @param idSecteur Integer, id du Secteur  concerné
     * @return DbSecteur , objet renseigné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public DbSecteur consulterCeSecteur(int idSecteur) throws Exception {
        return jpaCtrlSpot.readSecteur(idSecteur);
    }

    /**
     *
     * @param idVoie Integer, id de la voie  concernée
     * @return DbVoie , objet renseigné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public DbVoie consulterCetteVoie(int idVoie) throws Exception {
        return jpaCtrlSpot.readVoie(idVoie);
    }

    /**
     * recherche par le nom si renseigné
     * puis par la classification du spot si renseignée
     * puis par le nombre de spit ou la cotation si renseignés
     * sinon renvoie tous les spots
     *
     * @param recherche JpaCtrlRecherche , objet contenant les critères de recherche
     *
     * @return List DbSpot des objets répondant aux critères de recherche,
     * null si aucun spot ne correspond au critère de recherche choisi.
     * Si tous les critères sont null, liste de tous les spots.
     *
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbSpot> chercherCeSpot(JpaCtrlRecherche recherche) throws Exception {
        List<DbSpot> dbSpots = new ArrayList<>();

        if (recherche.getNomSpot() != null && ! recherche.getNomSpot().equals("")) {
             DbSpot dbSpot   = jpaCtrlSpot.findSpotByName(recherche.getNomSpot());
             if (dbSpot != null) {
                 dbSpots.add(dbSpot);
             }
        } else if (recherche.getSpotClassification() != null) {
            dbSpots = jpaCtrlSpot.findListSpotByClassification(recherche.getSpotClassification().name());
        } else if (recherche.getNombreSpitsLongeur() != null ||
                recherche.getCotationLongueur() != null ) {
            dbSpots = jpaCtrlSpot.findListeByLongeur(recherche);

        // si la liste des critères est vide, renvoie tous les spots
        } else {
            dbSpots =  jpaCtrlSpot.findListeSpots(null);
        }
        // si rien trouvé renvoie tout
        if (dbSpots == null || dbSpots.isEmpty()) {
            dbSpots =  jpaCtrlSpot.findListeSpots(null);
        }
        return dbSpots;
    }

    /**
     *
      * @param commentaireSpot String texte du commentaire
     * @param titre String titre du Commentaire
     * @param idSpot Integer , id du Spot
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void ajouterCommentaireCeSpot(String commentaireSpot, String titre , Integer idSpot) throws Exception {
        jpaCtrlSpot.addCommentaireSpot(idSpot, titre, commentaireSpot);
    }

    /**
     *
      * @param dbSpot   Integer, id du Spot concerné
     * @return Collection DbSecteur, liste des secteurs du spot
    */
    @Override
    public Collection<DbSecteur> listerSecteurDuSpot( DbSpot dbSpot)  {
        return  dbSpot.getSecteursByIdspot();
    }

    /**
     *
     * @param dbSpot   Integer, id du Spot concerné
     * @return Collection DbCommentaire, liste des commentaires du spot
     */
    @Override
    public Collection<DbCommentaire> listerCommentairesDuSpot( DbSpot dbSpot)  {
        Collection <DbCommentaire> commentaires = new ArrayList<>();

        for ( DbCommentaire dbCommentaire  :dbSpot.getCommentairesByIdspot()) {
            if(dbCommentaire.getEstVisible()) {
                commentaires.add(dbCommentaire);
            }
        }
        return commentaires;
    }

    /**
     *
      * @param dbSecteur   Integer, id du Secteur concerné
     * @return Collection DbVoie , liste des voies du secteur
     */
    @Override
    public Collection<DbVoie> listerVoiesDuSecteur(DbSecteur dbSecteur) {
        return  dbSecteur.getVoiesByIdsecteur();
    }

    /**
     *
     * @param dbVoie Integer, id de la Voie concernée
     * @return Collection DbLongueur , liste des Longueurs de la voie
     */
    @Override
    public Collection<DbLongueur> listerLongueursDeCetteVoie(DbVoie dbVoie) {
        return dbVoie.getLongueursByIdvoie();
    }

    /**
     * Suppression logique
     *
     * @param idCommentaire Integer, id commentaire concerné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void supprimeCeCommentaire(Integer idCommentaire) throws Exception {
        jpaCtrlSpot.updateCommentaire(idCommentaire, false, null);
    }

    /**
     *
     * @param idCommentaire Integer, id commentaire concerné
     * @param txtCommentaire String , texte du commentaire (update)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void modereCeCommentaire(Integer idCommentaire, String txtCommentaire) throws Exception {
        jpaCtrlSpot.updateCommentaire(idCommentaire,null, txtCommentaire);
    }

    /**
     *
      * @param idSelectionCommentaire int , id commentaire concerné
     * @return DbCommentaire , objet renseigné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public DbCommentaire consulterCeCommentaire(int idSelectionCommentaire) throws Exception {
        return jpaCtrlSpot.readCommentaire(idSelectionCommentaire);
    }
}
