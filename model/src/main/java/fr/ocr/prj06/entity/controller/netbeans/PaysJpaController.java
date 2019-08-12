/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.netbeans;

import com.eni.jpa.controller.exceptions.IllegalOrphanException;
import com.eni.jpa.controller.exceptions.NonexistentEntityException;
import com.eni.jpa.entity.Langue;
import com.eni.jpa.entity.Pays;
import com.eni.jpa.entity.Ville;

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
public class PaysJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public PaysJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pays pays) {
        if (pays.getLangueList() == null) {
            pays.setLangueList(new ArrayList<Langue>());
        }
        if (pays.getVilleList() == null) {
            pays.setVilleList(new ArrayList<Ville>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Langue> attachedLangueList = new ArrayList<Langue>();
            for (Langue langueListLangueToAttach : pays.getLangueList()) {
                langueListLangueToAttach = em.getReference(langueListLangueToAttach.getClass(), langueListLangueToAttach.getId());
                attachedLangueList.add(langueListLangueToAttach);
            }
            pays.setLangueList(attachedLangueList);
            List<Ville> attachedVilleList = new ArrayList<Ville>();
            for (Ville villeListVilleToAttach : pays.getVilleList()) {
                villeListVilleToAttach = em.getReference(villeListVilleToAttach.getClass(), villeListVilleToAttach.getId());
                attachedVilleList.add(villeListVilleToAttach);
            }
            pays.setVilleList(attachedVilleList);
            em.persist(pays);
            for (Langue langueListLangue : pays.getLangueList()) {
                langueListLangue.getPaysList().add(pays);
                langueListLangue = em.merge(langueListLangue);
            }
            for (Ville villeListVille : pays.getVilleList()) {
                Pays oldPaysOfVilleListVille = villeListVille.getPays();
                villeListVille.setPays(pays);
                villeListVille = em.merge(villeListVille);
                if (oldPaysOfVilleListVille != null) {
                    oldPaysOfVilleListVille.getVilleList().remove(villeListVille);
                    oldPaysOfVilleListVille = em.merge(oldPaysOfVilleListVille);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pays pays) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pays persistentPays = em.find(Pays.class, pays.getId());
            List<Langue> langueListOld = persistentPays.getLangueList();
            List<Langue> langueListNew = pays.getLangueList();
            List<Ville> villeListOld = persistentPays.getVilleList();
            List<Ville> villeListNew = pays.getVilleList();
            List<String> illegalOrphanMessages = null;
            for (Ville villeListOldVille : villeListOld) {
                if (!villeListNew.contains(villeListOldVille)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ville " + villeListOldVille + " since its pays field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Langue> attachedLangueListNew = new ArrayList<Langue>();
            for (Langue langueListNewLangueToAttach : langueListNew) {
                langueListNewLangueToAttach = em.getReference(langueListNewLangueToAttach.getClass(), langueListNewLangueToAttach.getId());
                attachedLangueListNew.add(langueListNewLangueToAttach);
            }
            langueListNew = attachedLangueListNew;
            pays.setLangueList(langueListNew);
            List<Ville> attachedVilleListNew = new ArrayList<Ville>();
            for (Ville villeListNewVilleToAttach : villeListNew) {
                villeListNewVilleToAttach = em.getReference(villeListNewVilleToAttach.getClass(), villeListNewVilleToAttach.getId());
                attachedVilleListNew.add(villeListNewVilleToAttach);
            }
            villeListNew = attachedVilleListNew;
            pays.setVilleList(villeListNew);
            pays = em.merge(pays);
            for (Langue langueListOldLangue : langueListOld) {
                if (!langueListNew.contains(langueListOldLangue)) {
                    langueListOldLangue.getPaysList().remove(pays);
                    langueListOldLangue = em.merge(langueListOldLangue);
                }
            }
            for (Langue langueListNewLangue : langueListNew) {
                if (!langueListOld.contains(langueListNewLangue)) {
                    langueListNewLangue.getPaysList().add(pays);
                    langueListNewLangue = em.merge(langueListNewLangue);
                }
            }
            for (Ville villeListNewVille : villeListNew) {
                if (!villeListOld.contains(villeListNewVille)) {
                    Pays oldPaysOfVilleListNewVille = villeListNewVille.getPays();
                    villeListNewVille.setPays(pays);
                    villeListNewVille = em.merge(villeListNewVille);
                    if (oldPaysOfVilleListNewVille != null && !oldPaysOfVilleListNewVille.equals(pays)) {
                        oldPaysOfVilleListNewVille.getVilleList().remove(villeListNewVille);
                        oldPaysOfVilleListNewVille = em.merge(oldPaysOfVilleListNewVille);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pays.getId();
                if (findPays(id) == null) {
                    throw new NonexistentEntityException("The pays with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pays pays;
            try {
                pays = em.getReference(Pays.class, id);
                pays.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pays with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ville> villeListOrphanCheck = pays.getVilleList();
            for (Ville villeListOrphanCheckVille : villeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pays (" + pays + ") cannot be destroyed since the Ville " + villeListOrphanCheckVille + " in its villeList field has a non-nullable pays field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Langue> langueList = pays.getLangueList();
            for (Langue langueListLangue : langueList) {
                langueListLangue.getPaysList().remove(pays);
                langueListLangue = em.merge(langueListLangue);
            }
            em.remove(pays);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pays> findPaysEntities() {
        return findPaysEntities(true, -1, -1);
    }

    public List<Pays> findPaysEntities(int maxResults, int firstResult) {
        return findPaysEntities(false, maxResults, firstResult);
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

    public Pays findPays(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pays.class, id);
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
