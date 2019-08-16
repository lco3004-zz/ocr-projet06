package fr.ocr.prj06.controller;

import fr.ocr.prj06.entity.common.JpaEmUtility;
import fr.ocr.prj06.entity.stub.*;
import org.apache.logging.log4j.Level;

import java.util.List;

import static fr.ocr.prj06.utility.constante.Messages.ErreurMessages.*;
import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class JpaCtrl extends JpaUtilityCtrl {

    /**
     * @throws Exception
     */
    public JpaCtrl() throws Exception {
        super(DbCommentaire.class);
        setLogs();
    }


    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }

    public DbCommentaire createCommentaire(Integer idSpot, String txtComment, Boolean isVisible) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            DbCommentaire dbCommentaire = new DbCommentaire();
            try {
                jpa.getEm().getTransaction().begin();

                dbCommentaire.setEstVisible(isVisible);
                dbCommentaire.setTexte(txtComment);
                dbCommentaire.setSpotBySpotIdspot(jpa.getEm().find(DbSpot.class, idSpot));

                jpa.getEm().persist(dbCommentaire);

                jpa.getEm().getTransaction().commit();

                return dbCommentaire;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    public DbCommentaire readCommentaire(Integer idCommentaire) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();

                DbCommentaire dbCommentaire = jpa.getEm().find(DbCommentaire.class, idCommentaire);

                jpa.getEm().getTransaction().commit();

                return dbCommentaire;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READ_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    public DbCommentaire updateCommentaire(Integer idCommentaire, String txtComment, Boolean isVisible) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();

                DbCommentaire dbCommentaire = jpa.getEm().find(DbCommentaire.class, idCommentaire);
                dbCommentaire.setTexte(txtComment);
                dbCommentaire.setEstVisible(isVisible);

                jpa.getEm().getTransaction().commit();

                return dbCommentaire;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_UPDATE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    public void deleteCommentaire(Integer idCommentaire) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();
                DbCommentaire dbCommentaire = jpa.getEm().find(DbCommentaire.class, idCommentaire);
                jpa.getEm().remove(dbCommentaire);
                jpa.getEm().getTransaction().commit();
            } catch (Exception hex2) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(hex2);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_DELETE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    public List findListeCommentaires(Integer idSpot) throws Exception {
        try {
            return findDbEntities();
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READLISTE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**************************************************************************************************************
     *
     * ************************************************************************************************************
     */
    public DbLongueur createLongueur(Integer idVoie, String nom, String cotation, Integer nbSpits) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            DbLongueur dbLongueur = new DbLongueur();
            try {
                jpa.getEm().getTransaction().begin();
                dbLongueur.setCotation(cotation);
                dbLongueur.setNom(nom);
                dbLongueur.setNombreDeSpits(nbSpits);
                dbLongueur.setVoieByVoieIdvoie(jpa.getEm().find(DbVoie.class, idVoie));

                jpa.getEm().persist(dbLongueur);

                jpa.getEm().getTransaction().commit();

                return dbLongueur;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_LONGUEUR.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**************************************************************************************************************
     *
     * ************************************************************************************************************
     */
    public DbVoie createVoie(Integer idSecteur, String nom) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            DbVoie dbVoie = new DbVoie();
            try {
                jpa.getEm().getTransaction().begin();
                dbVoie.setNom(nom);
                dbVoie.setSecteurBySecteurIdsecteur(jpa.getEm().find(DbSecteur.class, idSecteur));

                jpa.getEm().persist(dbVoie);

                jpa.getEm().getTransaction().commit();

                return dbVoie;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_VOIE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**************************************************************************************************************
     *
     * ************************************************************************************************************
     */
    public DbSecteur createSecteur(Integer idSpot, String nom) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            DbSecteur dbSecteur = new DbSecteur();
            try {
                jpa.getEm().getTransaction().begin();
                dbSecteur.setNom(nom);
                dbSecteur.setSpotBySpotIdspot(jpa.getEm().find(DbSpot.class, idSpot));

                jpa.getEm().persist(dbSecteur);

                jpa.getEm().getTransaction().commit();

                return dbSecteur;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_VOIE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**************************************************************************************************************
     *
     * ************************************************************************************************************
     */
    public DbSpot createSpot(Integer idUser, DbSpot dbSpot) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();

                dbSpot.setUserByUserIduser(jpa.getEm().find(DbUser.class, idUser));

                jpa.getEm().persist(dbSpot);

                jpa.getEm().getTransaction().commit();

                return dbSpot;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_SPOT.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }
}
