package fr.ocr.model.controllers;

import fr.ocr.model.entities.*;

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
    DbSecteur readSecteur(Integer idSecteur) throws Exception;
    DbVoie readVoie (Integer idVoie) throws Exception;
    DbLongueur readLongueur (Integer idLongueur) throws Exception;

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

            List<DbSpot> dbSpots = (List<DbSpot>) query.getResultList();

            jpa.getEm().getTransaction().commit();
            return dbSpots;

        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    @Override
    public DbSpot readSpot(Integer idSpot) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {

            DbSpot dbSpot ;

            jpa.getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbSpot> criteriaQuery = criteriaBuilder.createQuery(DbSpot.class);

            Root<DbSpot> root = criteriaQuery.from(DbSpot.class);
            criteriaQuery.select(root);

            Predicate predicate = criteriaBuilder.equal(root.get(DbSpot_.idspot),idSpot);
            criteriaQuery.where(predicate);

            Query query = jpa.getEm().createQuery(criteriaQuery);

            dbSpot = (DbSpot) query.getSingleResult();

            for (DbSecteur dbSecteur: dbSpot.getSecteursByIdspot()) {
                for (DbVoie dbVoie:dbSecteur.getVoiesByIdsecteur()) {
                    for (DbLongueur dbLongueur : dbVoie.getLongueursByIdvoie()) {
                     int j = dbSpot.getIdspot();
                     int i = dbSecteur.getIdsecteur();
                     int k = dbVoie.getIdvoie();
                     int l = dbLongueur.getIdlongueur();
                    }
                }
            }

            jpa.getEm().getTransaction().commit();

            return dbSpot;

    } catch (Exception hex1) {
        throw new Exception(hex1);
    }
}

    @Override
    public DbSpot updateSpot(DbSpot dbSpot)  {
        return null;
    }

    @Override
    public DbSecteur readSecteur(Integer idSecteur) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            DbSecteur dbSecteur ;

            jpa.getEm().getTransaction().begin();
            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();
            CriteriaQuery<DbSecteur> criteriaQuery = criteriaBuilder.createQuery(DbSecteur.class);

            Root<DbSecteur> root = criteriaQuery.from(DbSecteur.class);
            criteriaQuery.select(root);

            Predicate predicate = criteriaBuilder.equal(root.get(DbSecteur_.idsecteur),idSecteur);
            criteriaQuery.where(predicate);

            Query query = jpa.getEm().createQuery(criteriaQuery);
            dbSecteur = (DbSecteur) query.getSingleResult();

            jpa.getEm().getTransaction().commit();

            for (DbVoie dbVoie:dbSecteur.getVoiesByIdsecteur()) {
                for (DbLongueur dbLongueur : dbVoie.getLongueursByIdvoie()) {
                    int i = dbSecteur.getIdsecteur();
                    int k = dbVoie.getIdvoie();
                    int l = dbLongueur.getIdlongueur();
                }
            }
            return dbSecteur;
         } catch (Exception hex1) {
           throw new Exception(hex1);
        }
    }

    @Override
    public DbVoie readVoie(Integer idVoie) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            DbVoie dbVoie ;

            jpa.getEm().getTransaction().begin();
            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();
            CriteriaQuery<DbVoie> criteriaQuery = criteriaBuilder.createQuery(DbVoie.class);

            Root<DbVoie> root = criteriaQuery.from(DbVoie.class);
            criteriaQuery.select(root);

            Predicate predicate = criteriaBuilder.equal(root.get(DbVoie_.idvoie),idVoie);
            criteriaQuery.where(predicate);

            Query query = jpa.getEm().createQuery(criteriaQuery);
            dbVoie = (DbVoie) query.getSingleResult();

            jpa.getEm().getTransaction().commit();

                for (DbLongueur dbLongueur : dbVoie.getLongueursByIdvoie()) {
                    int k = dbVoie.getIdvoie();
                    int l = dbLongueur.getIdlongueur();
                }
            return dbVoie;
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    @Override
    public DbLongueur readLongueur(Integer idLongueur) throws Exception {
        DbLongueur dbLongueur ;
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            dbLongueur = jpa.getEm().find(DbLongueur.class,idLongueur);
        }
        return dbLongueur;
    }

}