package fr.ocr.prj06.controllers;

import fr.ocr.prj06.constantes.UserProfile;
import fr.ocr.prj06.entities.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class JpaCtrl  {


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
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
            throw new Exception(hex1);
        }
    }

    /**
     * @param idCommentaire
     */
    public void archiveCommentaire(Integer idCommentaire) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
            throw new Exception(hex1);
        }
    }

    /**
     *
     * @param idCommentaire
     * @throws Exception
     */
    public void deleteCommentaire(Integer idCommentaire) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
            throw new Exception(hex1);
        }
    }

    /**
     *
     * @param idSpot
     * @return
     * @throws Exception
     */
    public List<? extends DbCommentaire> findListeCommentaires(Integer idSpot, Boolean isFiltrageActif, Boolean flagFiltrage) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();
            DbSpot spotBySpotIdspot = jpa.getEm().find(DbSpot.class, idSpot);

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbCommentaire> criteriaQuery = criteriaBuilder.createQuery(DbCommentaire.class);

            Root<DbCommentaire> root = criteriaQuery.from(DbCommentaire.class);


            criteriaQuery.select(root);

            if (isFiltrageActif) {
                Predicate [] predicates = new Predicate[2];
                predicates[0] = criteriaBuilder.equal(root.get(DbCommentaire_.spotBySpotIdspot), spotBySpotIdspot);
                predicates[1] = criteriaBuilder.equal(root.get(DbCommentaire_.estVisible),flagFiltrage);
                criteriaQuery.where(predicates);
            }else {
                Predicate predicate = criteriaBuilder.equal(root.get(DbCommentaire_.spotBySpotIdspot), spotBySpotIdspot);
                criteriaQuery.where(predicate);
            }

            Query query = jpa.getEm().createQuery(criteriaQuery);

            ArrayList<DbCommentaire> ret = (ArrayList<DbCommentaire>) query.getResultList();

            jpa.getEm().getTransaction().commit();
            return ret;

        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }
    /*
     *************************************************************************************************************
     * USER
     * ************************************************************************************************************
     */
    public DbGrimpeur readGrimpeur(Integer idGrimpeur) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            try {
                jpa.getEm().getTransaction().begin();

                DbGrimpeur dbGrimpeur = jpa.getEm().find(DbGrimpeur.class, idGrimpeur);

                jpa.getEm().getTransaction().commit();

                return dbGrimpeur;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    /**
     * @param dbGrimpeur
     * @return
     * @throws Exception
     */
    public DbGrimpeur createUser(DbGrimpeur dbGrimpeur) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            try {

                jpa.getEm().getTransaction().begin();
                jpa.getEm().persist(dbGrimpeur);
                jpa.getEm().getTransaction().commit();

                return dbGrimpeur;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }

        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    /**
     * @return
     * @throws Exception
     */
    public DbGrimpeur updateUser(Integer idUser, UserProfile userProfil) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            try {

                jpa.getEm().getTransaction().begin();
                DbGrimpeur dbGrimpeur = jpa.getEm().find(DbGrimpeur.class, idUser);
                dbGrimpeur.setRoleName(userProfil);
                jpa.getEm().getTransaction().commit();

                return dbGrimpeur;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }

        } catch (Exception hex1) {
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
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            try {

                jpa.getEm().getTransaction().begin();

                dbSpot.setGrimpeurByGrimpeurIdgrimpeur(jpa.getEm().find(DbGrimpeur.class, idUser));

                jpa.getEm().persist(dbSpot);

                jpa.getEm().getTransaction().commit();

                return dbSpot;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }
    /*
     *************************************************************************************************************
     * LONGUEUR
     * ************************************************************************************************************
     */
    public DbLongueur createLongueur(Integer idVoie, String nom, String cotation, Integer nbSpits) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
            throw new Exception(hex1);
        }
    }

    /*
     *************************************************************************************************************
     * VOIE
     * ************************************************************************************************************
     */
    public DbVoie createVoie(Integer idSecteur, String nom) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
            throw new Exception(hex1);
        }
    }

    /*
     *************************************************************************************************************
     * SECTEUR
     * ************************************************************************************************************
     */
    public DbSecteur createSecteur(Integer idSpot, String nom) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
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
            throw new Exception(hex1);
        }
    }
}
