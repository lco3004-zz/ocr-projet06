package fr.ocr.business.spot;


import fr.ocr.model.controllers.JpaCtrlSpot;
import fr.ocr.model.entities.*;
import fr.ocr.model.utile.JpaCtrlRecherche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static fr.ocr.model.constantes.SpotClassification.OFFICIEL;



public interface CtrlMetierSpot {
    CtrlMetierSpot CTRL_METIER_SPOT = new CtrlMetierSpot_impl();

    List<DbSpot> listerMesSpots(Integer idGrimpeur) throws Exception;
    List<DbSpot> listerTousSpots() throws Exception;
    DbSpot taggerCeSpot(Integer  idSpot) throws Exception;
    DbSpot ajouterCeSpot(Integer idUser,DbSpot dbSpot) throws Exception ;
    DbSpot consulterCeSpot(Integer idSpot) throws  Exception;
    DbSecteur consulterCeSecteur(int idSelectionSecteur) throws Exception;
    DbVoie consulterCetteVoie(int idVoie) throws Exception;
    List<DbSpot> chercherCeSpot(JpaCtrlRecherche recherche) throws Exception;
    DbSpot ajouterCommentaireCeSpot(String commentaireSpot, String titre ,Integer idSpot) throws Exception;

    Collection<DbSecteur> listerSecteurDuSpot( DbSpot dbSpot) throws Exception;

    Collection<DbCommentaire> listerCommentairesDuSpot(DbSpot dbSpot) throws Exception;

    Collection<DbVoie> listerVoiesDuSecteur(DbSecteur dbSecteur);

    Collection<DbLongueur> listerLongueursDeCetteVoie(DbVoie dbVoie);

    void supprimeCeCommentaire(Integer idCommentaire) throws Exception;

    void modereCeCommentaire(Integer idCommentaire, String txtCommentaire) throws Exception;

    DbCommentaire consulterCeCommentaire(int idSelectionCommentaire) throws Exception;

}

class CtrlMetierSpot_impl implements CtrlMetierSpot {

    private JpaCtrlSpot jpaCtrlSpot;

    CtrlMetierSpot_impl() {

        this.jpaCtrlSpot = jpaCtrlSpot.JPA_CTRL_SPOT;
    }

    @Override
    public List<DbSpot> listerMesSpots(Integer idGrimpeur) throws Exception {
        return  jpaCtrlSpot.findListeSpots(idGrimpeur);
    }

    @Override
    public List<DbSpot> listerTousSpots() throws Exception {
        return  listerMesSpots(null);
    }

    @Override
    public DbSpot taggerCeSpot(Integer idSpot) throws Exception {
        DbSpot dbSpot = jpaCtrlSpot.readSpot(idSpot);
        dbSpot.setClassification(OFFICIEL.name());
        return jpaCtrlSpot.updateSpot(idSpot, OFFICIEL.name());
    }

    @Override
    public DbSpot ajouterCeSpot(Integer idUser,DbSpot oldDbSpot) throws Exception {
/*
        DbSpot dbSpot = new DbSpot();

        dbSpot.setLocalisation(oldDbSpot.getLocalisation());
        dbSpot.setNom(oldDbSpot.getNom());
        dbSpot.setClassification(oldDbSpot.getClassification());

        for (DbSecteur oldSecteur : oldDbSpot.getSecteursByIdspot()) {
            DbSecteur dbSecteur = new DbSecteur();
            dbSecteur.setNom(oldSecteur.getNom());
            for (DbVoie oldVoie: oldSecteur.getVoiesByIdsecteur()) {
                DbVoie dbVoie = new DbVoie();
                dbVoie.setNom(oldVoie.getNom());
                for (DbLongueur oldLongeur :oldVoie.getLongueursByIdvoie()) {
                    DbLongueur dbLongueur = new DbLongueur();
                    dbLongueur.setCotation(oldLongeur.getCotation());
                    dbLongueur.setNom(oldLongeur.getNom());
                    dbLongueur.setNombreDeSpits(oldLongeur.getNombreDeSpits());
                    dbVoie.getLongueursByIdvoie().add(dbLongueur);
                }
                dbSecteur.getVoiesByIdsecteur().add(dbVoie);
            }
            dbSpot.getSecteursByIdspot().add(dbSecteur);
        }
        return jpaCtrlSpot.createSpot(idUser, dbSpot);

 */
        return jpaCtrlSpot.createSpot(idUser, oldDbSpot);
    }

    @Override
    public DbSpot consulterCeSpot(Integer idSpot) throws Exception {
        return jpaCtrlSpot.readSpot(idSpot);
    }

    @Override
    public DbSecteur consulterCeSecteur(int idSecteur) throws Exception {
        return jpaCtrlSpot.readSecteur(idSecteur);
    }

    @Override
    public DbVoie consulterCetteVoie(int idVoie) throws Exception {
        return jpaCtrlSpot.readVoie(idVoie);
    }

    @Override
    public List<DbSpot> chercherCeSpot(JpaCtrlRecherche recherche) throws Exception {
        List<DbSpot> dbSpots = new ArrayList<>();

        if (recherche.getNomSpot() != null && recherche.getNomSpot() != "") {
             DbSpot dbSpot   = jpaCtrlSpot.findSpotByName(recherche.getNomSpot());
             if (dbSpot != null) {
                 dbSpots.add(dbSpot);
             }
             else {
                 dbSpots = null;
             }
        } else if (recherche.getSpotClassification() != null) {
            dbSpots = jpaCtrlSpot.findListSpotByClassification(recherche.getSpotClassification().name());
        } else if (recherche.getNombreSpitsLongeur() != null ||
                recherche.getCotationLongueur() != null ) {
            dbSpots = jpaCtrlSpot.findListeByLongeur(recherche);
        } else {
            dbSpots = listerMesSpots(null);
        }
        return dbSpots;
    }

    @Override
    public DbSpot ajouterCommentaireCeSpot(String commentaireSpot,String titre ,Integer idSpot) throws Exception {
        return jpaCtrlSpot.addCommentaireSpot(idSpot,titre,commentaireSpot);
    }

    @Override
    public Collection<DbSecteur> listerSecteurDuSpot( DbSpot dbSpot) throws Exception {
        return  dbSpot.getSecteursByIdspot();
    }

    @Override
    public Collection<DbCommentaire> listerCommentairesDuSpot( DbSpot dbSpot) throws Exception {
        Collection <DbCommentaire> commentaires = new ArrayList<>();

        for ( DbCommentaire dbCommentaire  :dbSpot.getCommentairesByIdspot()) {
            if(dbCommentaire.getEstVisible()) {
                commentaires.add(dbCommentaire);
            }
        }
        return commentaires;
    }

    @Override
    public Collection<DbVoie> listerVoiesDuSecteur(DbSecteur dbSecteur) {
        return  dbSecteur.getVoiesByIdsecteur();
    }

    @Override
    public Collection<DbLongueur> listerLongueursDeCetteVoie(DbVoie dbVoie) {
        return dbVoie.getLongueursByIdvoie();
    }

    @Override
    public void supprimeCeCommentaire(Integer idCommentaire) throws Exception {
        jpaCtrlSpot.updateCommentaire(idCommentaire, false, null);
    }
    @Override
    public void modereCeCommentaire(Integer idCommentaire, String txtCommentaire) throws Exception {
        jpaCtrlSpot.updateCommentaire(idCommentaire,null, txtCommentaire);
    }

    @Override
    public DbCommentaire consulterCeCommentaire(int idSelectionCommentaire) throws Exception {
        return jpaCtrlSpot.readCommentaire(idSelectionCommentaire);
    }
}
