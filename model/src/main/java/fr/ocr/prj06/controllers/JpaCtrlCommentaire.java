package fr.ocr.prj06.controllers;

import fr.ocr.prj06.entities.DbCommentaire;
import fr.ocr.prj06.entities.DbCommentaire_;
import fr.ocr.prj06.entities.DbSpot;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public interface JpaCtrlCommentaire {

    JpaCtrlCommentaire JPA_CTRL_COMMENTAIRE = new JpaCtrlCommentaire_impl();
    DbCommentaire createCommentaire(Integer idSpot, String txtComment, Boolean isVisible) throws Exception;
    DbCommentaire readCommentaire(Integer idCommentaire) throws Exception;
    void archiveCommentaire(Integer idCommentaire) throws Exception;
    DbCommentaire updateCommentaire(Integer idCommentaire, String txtComment, Boolean isVisible) throws Exception;
    void deleteCommentaire(Integer idCommentaire) throws Exception;
    List<? extends DbCommentaire> findListeCommentaires(Integer idSpot, Boolean isFiltrageActif, Boolean flagFiltrage) throws Exception;

}

class  JpaCtrlCommentaire_impl implements JpaCtrlCommentaire {

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<? extends DbCommentaire> findListeCommentaires(Integer idSpot, Boolean isFiltrageActif, Boolean flagFiltrage) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();
            DbSpot spotBySpotIdspot = jpa.getEm().find(DbSpot.class, idSpot);

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbCommentaire> criteriaQuery = criteriaBuilder.createQuery(DbCommentaire.class);

            Root<DbCommentaire> root = criteriaQuery.from(DbCommentaire.class);


            criteriaQuery.select(root);

            if (isFiltrageActif) {
                Predicate[] predicates = new Predicate[2];
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

}
