package fr.ocr.prj06.entity.common;



import javax.persistence.EntityManager;


public class JpaEmUtility implements AutoCloseable {
    private EntityManager em = null;

    public JpaEmUtility() {

    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     *
     * @return em - objet EntityManager
     */
    public synchronized EntityManager getEm() throws ExceptionInInitializerError {
        try {
            JpaEmfUtility jpa = JpaEmfUtility.getInstanceEMF();
            if (em == null) {
                em = jpa.getEmf().createEntityManager();
            }
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }

    /**
     * ne ferme que l'entity manager
     *
     * @throws Exception
     */
    @Override
    public synchronized void close() throws Exception {
        try {
            if (em != null) {
                em.close();
                em = null;
            }
        } catch (Throwable ex) {
            throw new Exception(ex);
        }
    }
}
