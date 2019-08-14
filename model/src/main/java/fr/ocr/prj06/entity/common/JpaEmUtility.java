package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManager;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class JpaEmUtility implements AutoCloseable {
    protected LogsProjet logs;
    private EntityManager em = null;

    public JpaEmUtility() {
        logs = getLogsInstance();
    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     *
     * @return em - objet EntityManager
     */
    protected synchronized EntityManager getEm() throws ExceptionInInitializerError {
        try {
            JpaEMFUtility jpa = JpaEMFUtility.getInstanceEMF();
            if (em == null) {
                em = jpa.getEmf().createEntityManager();
                logs.maTrace(Level.DEBUG, String.format("%s %s ", "Creation EntityManager : ", em.toString()));
            }
        } catch (Throwable ex) {
            logs.maTrace(Level.FATAL, "Impossible de créer EntityManager :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }

    /**
     * ne ferme que l'entity manager
     *
     * @throws ExceptionInInitializerError
     */
    @Override
    public synchronized void close() throws Exception {
        try {
            if (em != null) {
                logs.maTrace(Level.DEBUG, String.format("%s %s ", "Fermeture EntityManager : ", em.toString()));
                em.close();
                em = null;
            }
        } catch (Throwable ex) {
            logs.maTrace(Level.FATAL, "Impossible fermer EntityManager et/ou E.M.Factory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}
