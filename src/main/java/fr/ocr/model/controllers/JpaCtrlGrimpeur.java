package fr.ocr.model.controllers;


import fr.ocr.model.constantes.UserProfile;
import fr.ocr.model.entities.DbGrimpeur;
import fr.ocr.model.entities.DbGrimpeur_;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public interface JpaCtrlGrimpeur {
    JpaCtrlGrimpeur JPA_CTRL_GRIMPEUR = new JpaCtrlGrimpeur_impl();
    DbGrimpeur readGrimpeur(int idGrimpeur) throws Exception ;

    List<DbGrimpeur> findGrimpeurByProfile(ArrayList<UserProfile> userProfiles) throws Exception;

    List<DbGrimpeur> findGrimpeurByName(String userName) throws Exception;
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
    public List<DbGrimpeur> findGrimpeurByProfile(ArrayList<UserProfile> userProfiles) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbGrimpeur> criteriaQuery = criteriaBuilder.createQuery(DbGrimpeur.class);

            Root<DbGrimpeur> root = criteriaQuery.from(DbGrimpeur.class);
            Order order = criteriaBuilder.asc(root.get(DbGrimpeur_.ROLE_NAME));

            criteriaQuery.select(root);

            if (userProfiles != null) {
                Predicate[] predicates = new Predicate[userProfiles.size()];
                int i = 0;
                for (UserProfile userProfile : userProfiles) {
                    predicates[i] = criteriaBuilder.equal(root.get(DbGrimpeur_.ROLE_NAME), userProfile);
                }
                criteriaQuery.where(predicates).orderBy(order);
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
    public List<DbGrimpeur> findGrimpeurByName(String userName) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbGrimpeur> criteriaQuery = criteriaBuilder.createQuery(DbGrimpeur.class);

            Root<DbGrimpeur> root = criteriaQuery.from(DbGrimpeur.class);

            criteriaQuery.select(root);

            if (userName != null) {
                Predicate predicate = criteriaBuilder.equal(root.get(DbGrimpeur_.USER_NAME),userName);
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
