package fr.ocr.prj06.business.spot;


import fr.ocr.prj06.constantes.SpotClassification;
import fr.ocr.prj06.controllers.JpaCtrlSpot;
import fr.ocr.prj06.entities.DbLongueur;
import fr.ocr.prj06.entities.DbSecteur;
import fr.ocr.prj06.entities.DbSpot;
import fr.ocr.prj06.entities.DbVoie;
import org.hibernate.HibernateException;

import javax.persistence.PersistenceException;
import java.util.List;

import static fr.ocr.prj06.constantes.SpotClassification.STANDARD;

public interface CtrlMetierSpot {
    CtrlMetierSpot CTRL_METIER_SPOT = new CtrlMetierSpot_impl();

    List<DbSpot> listerMesSpots(Integer idGrimpeur) throws Exception;
    List<DbSpot> listerTousSpots() throws Exception;
    DbSpot taggerCeSpot(Integer  idSpot) throws Exception;
    DbSpot ajouterCeSpot(Integer idUser,DbSpot dbSpot) throws Exception ;
    DbSpot consulterCeSpot(Integer idSpot) throws  Exception;

}

class CtrlMetierSpot_impl implements CtrlMetierSpot {

    private JpaCtrlSpot jpaCtrlSpot;

    CtrlMetierSpot_impl() {

        this.jpaCtrlSpot = jpaCtrlSpot.JPA_CTRL_SPOT;
    }


    @Override
    public List<DbSpot> listerMesSpots(Integer idGrimpeur) throws Exception {
        List<DbSpot> dbSpots =  jpaCtrlSpot.findListeSpots(idGrimpeur);
        /*
        controle coherence : si pas ok , leve une exception
         */

        try {
            for (DbSpot x: dbSpots) {
                DbSpot dbSpot = consulterCeSpot(x.getIdspot());
                for (DbSecteur y : dbSpot.getSecteursByIdspot()) {
                    DbSecteur dbSecteur  = jpaCtrlSpot.readSecteur(y.getIdsecteur());
                    for (DbVoie z : dbSecteur.getVoiesByIdsecteur()) {
                        DbVoie dbVoie = jpaCtrlSpot.readVoie(z.getIdvoie());
                        for (DbLongueur w  :dbVoie.getLongueursByIdvoie() ) {
                            DbLongueur dbLongueur = jpaCtrlSpot.readLongueur(w.getIdlongueur());
                        }
                    }
                }
            }
        } catch (HibernateException ez) {
            HibernateException hibernateException = new HibernateException(ez);
            //TODO: ajouter logs
            throw  hibernateException;

        } catch (PersistenceException ex) {
            PersistenceException persistenceException = new PersistenceException(ex);
            //TODO: ajouter logs
            throw persistenceException;
        }
        catch (NullPointerException ey) {
            NullPointerException nullPointerException = new NullPointerException("Erreur unite spot");
            //TODO: ajouter logs
            throw  nullPointerException;
        }
        return  dbSpots;
    }

    @Override
    public List<DbSpot> listerTousSpots() throws Exception {
        return  listerMesSpots(null);
    }

    @Override
    public DbSpot taggerCeSpot(Integer idSpot) throws Exception {
        DbSpot dbSpot = jpaCtrlSpot.readSpot(idSpot);
        dbSpot.setClassification(SpotClassification.OFFICIEL.name());
        return jpaCtrlSpot.updateSpot(dbSpot);
    }

    @Override
    public DbSpot ajouterCeSpot(Integer idUser,DbSpot dbSpot) throws Exception {

        dbSpot.setClassification(STANDARD.name());

        return jpaCtrlSpot.createSpot(idUser, dbSpot);
    }

    @Override
    public DbSpot consulterCeSpot(Integer idSpot) throws Exception {
        return jpaCtrlSpot.readSpot(idSpot);
    }
}