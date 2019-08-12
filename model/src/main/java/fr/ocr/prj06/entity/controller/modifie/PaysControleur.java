/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.modifie;

import com.eni.jpa.controller.netbeans.*;
import com.eni.jpa.entity.Pays;
import com.eni.jpa.entity.Pays_;
import com.eni.jpa.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author martial
 */
public class PaysControleur implements Serializable {

    public PaysControleur() {
    }

    public EntityManager getEntityManager() {
        return JpaUtil.getEmf().createEntityManager();
    }


    public List<Pays> findPaysEntities() {
        return findPaysEntities(true, -1, -1);
    }

    public List<Pays> findPaysEntities(int maxResults, int firstResult) {
        return findPaysEntities(false, maxResults, firstResult);
    }

    public List<Pays> findPaysEntitiesAvecLangue() {
        return findPaysEntitiesAvecLangue(true, -1, -1);
    }

    private List<Pays> findPaysEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pays.class));
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

    private List<Pays> findPaysEntitiesAvecLangue(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pays> pays = cq.from(Pays.class);
            pays.fetch(Pays_.langueList);
            cq.select(pays);
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

    public Pays findPays(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pays.class, id);
        } finally {
            em.close();
        }
    }

    public Pays findPaysAvecLangue(Integer id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pays> q = em.createQuery("SELECT p FROM Pays p JOIN FETCH p.langueList where p.id = " + id, Pays.class);
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Pays findPaysAvecLangue2(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Pays p = em.find(Pays.class, id);
            if (p != null) {
                p.getLangueList().size();
            }
            return p;
        } finally {
            em.close();
        }
    }

    public int getPaysCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pays> rt = cq.from(Pays.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
