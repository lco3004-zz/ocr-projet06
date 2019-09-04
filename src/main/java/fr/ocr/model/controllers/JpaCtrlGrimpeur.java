package fr.ocr.model.controllers;


import fr.ocr.model.constantes.UserProfile;
import fr.ocr.prj06.entities.DbGrimpeur;
import fr.ocr.prj06.entities.DbGrimpeur_;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface JpaCtrlGrimpeur {
    JpaCtrlGrimpeur JPA_CTRL_GRIMPEUR = new JpaCtrlGrimpeur_impl();
    DbGrimpeur readGrimpeur(int idGrimpeur) throws Exception ;
    List<DbGrimpeur> findListGrimpeurs(UserProfile userProfile) throws Exception;
    DbGrimpeur createGrimpeur(DbGrimpeur dbGrimpeur) throws Exception;
    DbGrimpeur updateGrimpeur(Integer idUser, UserProfile userProfil) throws Exception ;
}

class JpaCtrlGrimpeur_impl implements JpaCtrlGrimpeur {


    @Override
    public DbGrimpeur readGrimpeur(int idGrimpeur) throws Exception {
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

    @Override
    public List<DbGrimpeur> findListGrimpeurs(UserProfile userProfile) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbGrimpeur> criteriaQuery = criteriaBuilder.createQuery(DbGrimpeur.class);

            Root<DbGrimpeur> root = criteriaQuery.from(DbGrimpeur.class);

            criteriaQuery.select(root);

            if (userProfile != null) {

                Predicate predicate = criteriaBuilder.equal(root.get(DbGrimpeur_.ROLE_NAME),userProfile );
                criteriaQuery.where(predicate);
            }

            Query query = jpa.getEm().createQuery(criteriaQuery);

            List<DbGrimpeur> dbGrimpeurs = (List<DbGrimpeur>) query.getResultList();

            jpa.getEm().getTransaction().commit();

            return dbGrimpeurs;

        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    @Override
    public DbGrimpeur createGrimpeur(DbGrimpeur dbGrimpeur) throws Exception {
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

    @Override
    public DbGrimpeur updateGrimpeur(Integer idUser, UserProfile userProfil) throws Exception {
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
}
