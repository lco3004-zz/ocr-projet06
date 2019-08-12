/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.modifie;

import com.eni.jpa.entity.Langue;
import com.eni.jpa.entity.Pays;
import com.eni.jpa.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author martial
 */
public class LangueControleur implements Serializable {

    public LangueControleur() {
    }

    public EntityManager getEntityManager() {
        return JpaUtil.getEmf().createEntityManager();
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

    public List<Langue> findLangueByIdPays(Pays pays) {
        EntityManager em = getEntityManager();
        try {
            Query tq = em.createQuery("SELECT l FROM Langue l WHERE :pa MEMBER OF l.paysList ", Langue.class);
            return tq.setParameter("pa", pays).getResultList();
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
