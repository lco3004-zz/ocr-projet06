package fr.ocr.prj06.controller;

import fr.ocr.prj06.entity.common.JpaEmUtility;
import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.*;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static fr.ocr.prj06.utility.constante.Messages.ErreurMessages.*;
import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;


public class JpaCtrl  {

    LogsProjet logs;
    /**
     * @throws Exception
     */
    public JpaCtrl() throws Exception {
        logs = getLogsInstance();
    }


    /*
     *************************************************************************************************************
     * COMMENTAIRE
     * ************************************************************************************************************
     */

    /**
     * @param idSpot
     * @param txtComment
     * @param isVisible
     * @return
     * @throws Exception
     */
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

    /**
     *
     * @param idCommentaire
     * @return
     * @throws Exception
     */
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

    /**
     * @param idCommentaire
     */
    public void archiveCommentaire(Integer idCommentaire) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();

                DbCommentaire dbCommentaire = jpa.getEm().find(DbCommentaire.class, idCommentaire);
                dbCommentaire.setTexte(dbCommentaire.getTexte());
                dbCommentaire.setEstVisible(false);

                jpa.getEm().getTransaction().commit();

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_UPDATE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**
     *
     * @param idCommentaire
     * @param txtComment
     * @param isVisible
     * @return
     * @throws Exception
     */
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

    /**
     *
     * @param idCommentaire
     * @throws Exception
     */
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

    /**
     *
     * @param idSpot
     * @return
     * @throws Exception
     */
    public List findListeCommentaires(Integer idSpot, Boolean isVisible) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder builder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbCommentaire> criteriaQuery = builder.createQuery(DbCommentaire.class);

            Root<DbCommentaire> root = criteriaQuery.from(DbCommentaire.class);

            DbSpot spotBySpotIdspot = jpa.getEm().find(DbSpot.class, idSpot);

            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(DbCommentaire_.spotBySpotIdspot), spotBySpotIdspot));

            Query query = jpa.getEm().createQuery(criteriaQuery);

            ArrayList<DbCommentaire> ret = (ArrayList<DbCommentaire>) query.getResultList();

            jpa.getEm().getTransaction().commit();
            return ret;

        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READLISTE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }
    /*
     *************************************************************************************************************
     * USER
     * ************************************************************************************************************
     */
    public DbUser readUser(Integer idUser) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();

                DbUser dbUser = jpa.getEm().find(DbUser.class, idUser);

                jpa.getEm().getTransaction().commit();

                return dbUser;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READ_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**
     * @param dbUser
     * @return
     * @throws Exception
     */
    public DbUser createUser(DbUser dbUser) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {

                jpa.getEm().getTransaction().begin();
                jpa.getEm().persist(dbUser);
                jpa.getEm().getTransaction().commit();

                return dbUser;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }

        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_USER.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**
     * @return
     * @throws Exception
     */
    public DbUser updateUser(Integer idUser, UserProfile userProfil) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {

                jpa.getEm().getTransaction().begin();
                DbUser dbUser = jpa.getEm().find(DbUser.class, idUser);
                dbUser.setProfil(userProfil);
                jpa.getEm().getTransaction().commit();

                return dbUser;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }

        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_USER.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }


    /*
     *************************************************************************************************************
     * SPOT
     * ************************************************************************************************************
     */

    /**
     * @param idUser
     * @param dbSpot
     * @return
     * @throws Exception
     */
    public DbSpot createSpot(Integer idUser, DbSpot dbSpot) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {

                jpa.getEm().getTransaction().begin();

                dbSpot.setUserByUserIduser(jpa.getEm().find(DbUser.class, idUser));

                jpa.getEm().persist(dbSpot);

                for (DbCommentaire dbCommentaire : dbSpot.getCommentairesByIdspot()) {
                    dbCommentaire.setSpotBySpotIdspot(jpa.getEm().find(DbSpot.class, dbSpot.getIdspot()));
                    jpa.getEm().persist(dbCommentaire);
                }

                for (DbSecteur dbSecteur : dbSpot.getSecteursByIdspot()) {
                    dbSecteur.setSpotBySpotIdspot(jpa.getEm().find(DbSpot.class, dbSpot.getIdspot()));
                    jpa.getEm().persist(dbSecteur);
                    for (DbVoie dbVoie : dbSecteur.getVoiesByIdsecteur()) {
                        dbVoie.setSecteurBySecteurIdsecteur(jpa.getEm().find(DbSecteur.class, dbSecteur.getIdsecteur()));
                        jpa.getEm().persist(dbVoie);
                        for (DbLongueur dbLongueur : dbVoie.getLongueursByIdvoie()) {
                            dbLongueur.setVoieByVoieIdvoie(jpa.getEm().find(DbVoie.class, dbVoie.getIdvoie()));
                            jpa.getEm().persist(dbLongueur);
                        }
                    }
                }

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
    /*
     *************************************************************************************************************
     * LONGUEUR
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

    /*
     *************************************************************************************************************
     * VOIE
     * ************************************************************************************************************
     */
    @Deprecated(since = "2019-08-20", forRemoval = true)
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

    /*
     *************************************************************************************************************
     * SECTEUR
     * ************************************************************************************************************
     */
    @Deprecated(since = "2019-08-20", forRemoval = true)
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


    /*
     *************************************************************************************************************
     * TOPO
     * ************************************************************************************************************
     */
    public DbTopo createTopo(Integer idUser, DbTopo dbTopo) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {

            try {
                jpa.getEm().getTransaction().begin();

                dbTopo.setUserByUserIduser(jpa.getEm().find(DbUser.class, idUser));

                jpa.getEm().persist(dbTopo);

                jpa.getEm().getTransaction().commit();

                return dbTopo;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_TOPO.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }
    public List findListeTopos(Integer idUser) throws  Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder builder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbTopo> criteriaQuery = builder.createQuery(DbTopo.class);

            Root<DbTopo> root = criteriaQuery.from(DbTopo.class);

            DbUser dbTopo = jpa.getEm().find(DbUser.class, idUser);

            criteriaQuery.select(root);
//            criteriaQuery.where(builder.equal(root.get(), spotBySpotIdspot))

            Query query = jpa.getEm().createQuery(criteriaQuery);

            ArrayList<DbCommentaire> ret = (ArrayList<DbCommentaire>) query.getResultList();

            jpa.getEm().getTransaction().commit();
            return ret;

        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READLISTE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }


    }
}
