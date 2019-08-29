package fr.ocr.prj06.controllers;



import javax.persistence.EntityManager;


public class JpaEntityManager implements AutoCloseable {
    private EntityManager em = null;

    public JpaEntityManager() {

    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     *
     * @return em - objet EntityManager
     */
    public synchronized EntityManager getEm() throws ExceptionInInitializerError {
        try {
            JpaEntityManagerFactory jpa = JpaEntityManagerFactory.getJpaEMFUtility();
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
