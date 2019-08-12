package fr.ocr.prj06.entity.controller;

import fr.ocr.prj06.entity.common.JpaUtility;
import fr.ocr.prj06.entity.stub.dbUserEntity;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static fr.ocr.prj06.utility.constante.Messages.ErreurMessages.*;
import static fr.ocr.prj06.utility.logs.LogsProjet.geLogsInstance;

public class UserJpaController implements CommonsJpaController, Serializable {

    private LogsProjet logs;

    public UserJpaController() throws Exception {
        try {
            logs = geLogsInstance(UserJpaController.class);
        } catch (Exception ex) {
            System.out.println(CONTROLLER_JPA_USER_CONSTRUCTEUR.getMessageErreur());
            throw new Exception(ex);
        }
    }

    /**
     * retourne la liste compléte des User en base de données
     *
     * @return List '<'dbUserEntity'>'  - liste compléte de entity dbUserEntity
     */
    public List<dbUserEntity> findDbUserEntities() throws HibernateException {
        return findDbUserEntities(true, -1, -1);
    }

    /**
     * @param all         : true (tous) , false ( liste contenant Maxresults, commencant en position firstResult
     * @param maxResults  int - taille max de la liste
     * @param firstResult int - indice du premier tupple à renvoyer dans la liste de 0..MaxResults
     * @return
     */
    private List<dbUserEntity> findDbUserEntities(boolean all, int maxResults, int firstResult) throws HibernateException {
        try (JpaUtility jpa = getJpaUtility()) {

            CriteriaQuery criteriaQuery = jpa.getEm().getCriteriaBuilder().createQuery();
            criteriaQuery.select(criteriaQuery.from(dbUserEntity.class));
            Query query = jpa.getEm().createQuery(criteriaQuery);
            if (!all) {
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_FIND_USER.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }

    /**
     * @param id - valeur de la clef primaire de dbUserEntity
     * @return un dbUserEntity ou Null si cet id n'existe pas
     */
    public dbUserEntity findDbUserEntity(Integer id) throws HibernateException {
        try (JpaUtility jpa = getJpaUtility()) {
            return jpa.getEm().find(dbUserEntity.class, id);
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_FIND_USER.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }

    /**
     * nombre de dbUserEntity en base
     *
     * @return int - nombre de tupple
     * @throws HibernateException
     */
    public int getdbUserEntityCount() throws HibernateException {
        try (JpaUtility jpa = getJpaUtility()) {
            CriteriaQuery criteriaQuery = jpa.getEm().getCriteriaBuilder().createQuery();
            Root<dbUserEntity> root = criteriaQuery.from(dbUserEntity.class);
            criteriaQuery.select(jpa.getEm().getCriteriaBuilder().count(root));
            Query q = jpa.getEm().createQuery(criteriaQuery);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ecp1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_USER_COUNT.getMessageErreur() + ecp1.getLocalizedMessage());
            throw new HibernateException(ecp1);
        }
    }
}
