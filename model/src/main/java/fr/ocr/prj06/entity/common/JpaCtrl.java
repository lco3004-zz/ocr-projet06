package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.entity.stub.dbTopoEntity;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static fr.ocr.prj06.utility.constante.Messages.ErreurMessages.*;


public abstract class JpaCtrl<EntityMetamodel> {

    protected LogsProjet logs;
    protected Class<EntityMetamodel> t;
    private EntityTransaction et = null;

    /**
     * @param t Objet Entity
     * @throws Exception
     */
    protected JpaCtrl(Class<EntityMetamodel> t) throws Exception {
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
     * Renvoie (ou crée et renvoi) une référence à un objet transaction
     * @return et - objet EntityTransaction
     */
    /*
    protected synchronized EntityTransaction getEt() throws ExceptionInInitializerError {
        try {
            if (et == null) {
                logs.maTrace(Level.DEBUG, "Creation EntityTransaction ");
                et = getEm().getTransaction();
                logs.maTrace(Level.DEBUG, "EntityTransaction a été créée ");
            }
        } catch (Throwable ex) {
            logs.maTrace(Level.FATAL, "Impossible de créer EntityTransaction :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return et;
    }
    */

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return
     */
    protected EntityMetamodel findDbEntity(Integer id) throws HibernateException {
        try (JpaEmUtility jpaEmUtility = new JpaEmUtility()) {
            return (EntityMetamodel) jpaEmUtility.getEm().find(t.getClass(), id);
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_FIND_ENTITY.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }

    /**
     * @return Liste d'entité de type T
     * @throws HibernateException
     */
    public List<EntityMetamodel> findDbEntities() throws HibernateException {
        return findDbEntities(true, -1, -1);
    }

    /**
     * @param all         : vrai -> renvoie la liste des entitées
     * @param maxResults  : taille max de la liste renvoyée
     * @param firstResult : indice de début de la liste renvoyée
     * @return Liste d'entité de type T
     * @throws HibernateException
     */
    public List<EntityMetamodel> findDbEntities(boolean all, int maxResults, int firstResult) throws HibernateException {
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
     * nombre de dbTopoEntity en base
     *
     * @return int - nombre de tupple
     * @throws HibernateException
     */
    public int getdbEntityCount() throws HibernateException {
        try (JpaEmUtility jpaEmUtility = new JpaEmUtility()) {
            CriteriaQuery criteriaQuery = jpaEmUtility.getEm().getCriteriaBuilder().createQuery();
            Root<dbTopoEntity> root = criteriaQuery.from(t.getClass());
            criteriaQuery.select(jpaEmUtility.getEm().getCriteriaBuilder().count(root));
            Query q = jpaEmUtility.getEm().createQuery(criteriaQuery);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_ENTITY_COUNT.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }
}
