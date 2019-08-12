/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.netbeans;

import com.eni.jpa.controller.exceptions.NonexistentEntityException;
import com.eni.jpa.entity.Langue;
import com.eni.jpa.entity.Pays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author martial
 */
public class LangueJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public LangueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Langue langue) {
        if (langue.getPaysList() == null) {
            langue.setPaysList(new ArrayList<Pays>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pays> attachedPaysList = new ArrayList<Pays>();
            for (Pays paysListPaysToAttach : langue.getPaysList()) {
                paysListPaysToAttach = em.getReference(paysListPaysToAttach.getClass(), paysListPaysToAttach.getId());
                attachedPaysList.add(paysListPaysToAttach);
            }
            langue.setPaysList(attachedPaysList);
            em.persist(langue);
            for (Pays paysListPays : langue.getPaysList()) {
                paysListPays.getLangueList().add(langue);
                paysListPays = em.merge(paysListPays);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Langue langue) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Langue persistentLangue = em.find(Langue.class, langue.getId());
            List<Pays> paysListOld = persistentLangue.getPaysList();
            List<Pays> paysListNew = langue.getPaysList();
            List<Pays> attachedPaysListNew = new ArrayList<Pays>();
            for (Pays paysListNewPaysToAttach : paysListNew) {
                paysListNewPaysToAttach = em.getReference(paysListNewPaysToAttach.getClass(), paysListNewPaysToAttach.getId());
                attachedPaysListNew.add(paysListNewPaysToAttach);
            }
            paysListNew = attachedPaysListNew;
            langue.setPaysList(paysListNew);
            langue = em.merge(langue);
            for (Pays paysListOldPays : paysListOld) {
                if (!paysListNew.contains(paysListOldPays)) {
                    paysListOldPays.getLangueList().remove(langue);
                    paysListOldPays = em.merge(paysListOldPays);
                }
            }
            for (Pays paysListNewPays : paysListNew) {
                if (!paysListOld.contains(paysListNewPays)) {
                    paysListNewPays.getLangueList().add(langue);
                    paysListNewPays = em.merge(paysListNewPays);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = langue.getId();
                if (findLangue(id) == null) {
                    throw new NonexistentEntityException("The langue with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Langue langue;
            try {
                langue = em.getReference(Langue.class, id);
                langue.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The langue with id " + id + " no longer exists.", enfe);
            }
            List<Pays> paysList = langue.getPaysList();
            for (Pays paysListPays : paysList) {
                paysListPays.getLangueList().remove(langue);
                paysListPays = em.merge(paysListPays);
            }
            em.remove(langue);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Langue> findLangueEntities() {
        return findLangueEntities(true, -1, -1);
    }

    public List<Langue> findLangueEntities(int maxResults, int firstResult) {
        return findLangueEntities(false, maxResults, firstResult);
    }

    private List<Langue> findLangueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Langue.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Langue findLangue(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Langue.class, id);
        } finally {
            em.close();
        }
    }

    public int getLangueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Langue> rt = cq.from(Langue.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
