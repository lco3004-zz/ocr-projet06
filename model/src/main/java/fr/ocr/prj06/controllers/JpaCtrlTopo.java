package fr.ocr.prj06.controllers;

import fr.ocr.prj06.entities.DbGrimpeur;
import fr.ocr.prj06.entities.DbTopo;
import fr.ocr.prj06.entity.DbTopo_;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/*

 */
public interface JpaCtrlTopo {
     JpaCtrlTopo getInstance = new JpaCtrlTopo_impl();
     List<DbTopo> findListeTopos(Integer idUser) throws  Exception;
     DbTopo createTopo(Integer idUser, DbTopo dbTopo) throws Exception;
     DbTopo readTopo(Integer idTopo) throws Exception;
     DbTopo updateTopo(DbTopo dbTopo) throws Exception;
}
/*

 */
class JpaCtrlTopo_impl implements JpaCtrlTopo {

    @Override
    public List<DbTopo> findListeTopos(Integer idUser) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbTopo> criteriaQuery = criteriaBuilder.createQuery(DbTopo.class);

            Root<DbTopo> root = criteriaQuery.from(DbTopo.class);
            criteriaQuery.select(root);

            if (idUser != null) {
                DbGrimpeur userByUserIduser = jpa.getEm().find(DbGrimpeur.class, idUser);
                Predicate predicate = criteriaBuilder.equal(root.get(DbTopo_.USER_BY_USER_IDUSER),userByUserIduser );
                criteriaQuery.where(predicate);
            }

            Query query = jpa.getEm().createQuery(criteriaQuery);

            List<DbTopo> ret = (List<DbTopo>) query.getResultList();

            jpa.getEm().getTransaction().commit();
            return ret;

        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    @Override
    public DbTopo createTopo(Integer idUser, DbTopo dbTopo) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {

            try {
                jpa.getEm().getTransaction().begin();

                dbTopo.setUserByUserIduser(jpa.getEm().find(DbGrimpeur.class, idUser));

                jpa.getEm().persist(dbTopo);

                jpa.getEm().getTransaction().commit();

                return dbTopo;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    @Override
    public DbTopo readTopo(Integer idTopo) throws Exception {
        DbTopo dbTopo =null;
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            dbTopo = jpa.getEm().find(DbTopo.class,idTopo);
        }
        return dbTopo;
    }

    @Override
    public DbTopo updateTopo(DbTopo dbTopo) throws Exception {
        DbTopo dbTopoUpdate  = null;
        try (JpaEntityManager jpa = new JpaEntityManager()) {

        }

        return dbTopoUpdate;
    }
}
