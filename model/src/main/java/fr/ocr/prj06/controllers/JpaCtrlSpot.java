package fr.ocr.prj06.controllers;

import fr.ocr.prj06.entities.DbGrimpeur;
import fr.ocr.prj06.entities.DbSpot;
import fr.ocr.prj06.entities.DbSpot_;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface JpaCtrlSpot {

    JpaCtrlSpot JPA_CTRL_SPOT = new JpaCtrlSpot_impl();

    DbSpot createSpot(Integer idUser, DbSpot dbSpot) throws Exception;
    List<DbSpot> findListeSpots(Integer idUser) throws  Exception;
    DbSpot readSpot(Integer idSpot) throws Exception;
    DbSpot updateSpot(DbSpot dbSpot) throws Exception;

}
class  JpaCtrlSpot_impl implements JpaCtrlSpot {

    @Override
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

    @Override
    public List<DbSpot> findListeSpots(Integer idUser) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbSpot> criteriaQuery = criteriaBuilder.createQuery(DbSpot.class);

            Root<DbSpot> root = criteriaQuery.from(DbSpot.class);
            criteriaQuery.select(root);

            if (idUser != null) {
                DbGrimpeur userByUserIduser = jpa.getEm().find(DbGrimpeur.class, idUser);
                Predicate predicate = criteriaBuilder.equal(root.get(DbSpot_.GRIMPEUR_BY_GRIMPEUR_IDGRIMPEUR),userByUserIduser );
                criteriaQuery.where(predicate);
            }

            Query query = jpa.getEm().createQuery(criteriaQuery);

            List<DbSpot> dbTopos = (List<DbSpot>) query.getResultList();

            jpa.getEm().getTransaction().commit();
            return dbTopos;

        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    @Override
    public DbSpot readSpot(Integer idSpot) throws Exception {
        DbSpot dbSpot =null;
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            dbSpot = jpa.getEm().find(DbSpot.class,idSpot);
        }
        return dbSpot;
    }

    @Override
    public DbSpot updateSpot(DbSpot dbSpot) throws Exception {
        return null;
    }

}