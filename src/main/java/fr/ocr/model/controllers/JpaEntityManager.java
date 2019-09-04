package fr.ocr.model.controllers;


import javax.persistence.EntityManager;

import static fr.ocr.model.controllers.JpaEntityManagerFactory.getJpaEntityManagerFactory;

public class JpaEntityManager implements AutoCloseable {
    private EntityManager em = null;

    public JpaEntityManager() {
    }

    /**
     * creation d'un EntityManagerou si existe déja retourne le membre "emf
     *
     * @return em - objet EntityManager
     */
    public synchronized EntityManager getEm() throws ExceptionInInitializerError {
        try {
            JpaEntityManagerFactory jpa = getJpaEntityManagerFactory();
            if (em == null) {
                em = jpa.getEmf().createEntityManager();
            }
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }

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
