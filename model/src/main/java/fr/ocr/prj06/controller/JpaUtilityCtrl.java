package fr.ocr.prj06.controller;

import fr.ocr.prj06.entity.common.JpaEmUtility;
import fr.ocr.prj06.entity.stub.DbTopo;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static fr.ocr.prj06.utility.constante.Messages.ErreurMessages.*;

public abstract class JpaUtilityCtrl<EntityMetamodel> {

    LogsProjet logs;
    private Class<EntityMetamodel> t;

    /**
     * @param t Objet Entity
     * @throws Exception
     */
    protected JpaUtilityCtrl(Class<EntityMetamodel> t) throws Exception {
        try {
            this.t = t;
        } catch (Exception ex) {
            String msg = CONTROLLER_JPA_ENTITY_CONSTRUCTEUR.getMessageErreur() + " " + t.getClass().getSimpleName();
            logs.maTrace(Level.ERROR, msg);
            throw new Exception(ex);
        }
    }

    protected abstract void setLogs();

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return
     */
    protected EntityMetamodel findDbEntity(Integer id) throws HibernateException {
        try (JpaEmUtility jpaEmUtility = new JpaEmUtility()) {
            jpaEmUtility.getEm().getTransaction().begin();
            EntityMetamodel etm = jpaEmUtility.getEm().find(t, id);
            jpaEmUtility.getEm().getTransaction().commit();
            return etm;
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_FIND_ENTITY.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }

    /**
     * @return Liste d'entité de type T
     * @throws HibernateException
     */
    protected List findDbEntities() throws HibernateException {
        return findDbEntities(true, -1, -1);
    }

    /**
     * @param all         : vrai -> renvoie la liste des entitées
     * @param maxResults  : taille max de la liste renvoyée
     * @param firstResult : indice de début de la liste renvoyée
     * @return Liste d'entité de type T
     * @throws HibernateException
     */
    private List findDbEntities(boolean all, int maxResults, int firstResult) throws HibernateException {
        try (JpaEmUtility jpaEmUtility = new JpaEmUtility()) {
            CriteriaQuery criteriaQuery = jpaEmUtility.getEm().getCriteriaBuilder().createQuery();
            criteriaQuery.select(criteriaQuery.from(t));
            Query query = jpaEmUtility.getEm().createQuery(criteriaQuery);
            if (!all) {
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_FIND_ENTITY.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }

    /**
     * nombre de DbTopo en base
     *
     * @return int - nombre de tupple
     * @throws HibernateException
     */
    public int getdbEntityCount() throws HibernateException {
        try (JpaEmUtility jpaEmUtility = new JpaEmUtility()) {
            CriteriaQuery criteriaQuery = jpaEmUtility.getEm().getCriteriaBuilder().createQuery();
            Root<DbTopo> root = criteriaQuery.from(t.getClass());
            criteriaQuery.select(jpaEmUtility.getEm().getCriteriaBuilder().count(root));
            Query q = jpaEmUtility.getEm().createQuery(criteriaQuery);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_ENTITY_COUNT.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }
}
