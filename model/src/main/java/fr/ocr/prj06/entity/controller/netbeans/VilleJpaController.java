/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.netbeans;

import com.eni.jpa.controller.exceptions.NonexistentEntityException;
import com.eni.jpa.entity.Adresse;
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
public class VilleJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public VilleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ville ville) {
        if (ville.getAdresseList() == null) {
            ville.setAdresseList(new ArrayList<Adresse>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pays pays = ville.getPays();
            if (pays != null) {
                pays = em.getReference(pays.getClass(), pays.getId());
                ville.setPays(pays);
            }
            List<Adresse> attachedAdresseList = new ArrayList<Adresse>();
            for (Adresse adresseListAdresseToAttach : ville.getAdresseList()) {
                adresseListAdresseToAttach = em.getReference(adresseListAdresseToAttach.getClass(), adresseListAdresseToAttach.getId());
                attachedAdresseList.add(adresseListAdresseToAttach);
            }
            ville.setAdresseList(attachedAdresseList);
            em.persist(ville);
            if (pays != null) {
                pays.getVilleList().add(ville);
                pays = em.merge(pays);
            }
            for (Adresse adresseListAdresse : ville.getAdresseList()) {
                Ville oldIdVilleOfAdresseListAdresse = adresseListAdresse.getIdVille();
                adresseListAdresse.setIdVille(ville);
                adresseListAdresse = em.merge(adresseListAdresse);
                if (oldIdVilleOfAdresseListAdresse != null) {
                    oldIdVilleOfAdresseListAdresse.getAdresseList().remove(adresseListAdresse);
                    oldIdVilleOfAdresseListAdresse = em.merge(oldIdVilleOfAdresseListAdresse);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ville ville) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ville persistentVille = em.find(Ville.class, ville.getId());
            Pays paysOld = persistentVille.getPays();
            Pays paysNew = ville.getPays();
            List<Adresse> adresseListOld = persistentVille.getAdresseList();
            List<Adresse> adresseListNew = ville.getAdresseList();
            if (paysNew != null) {
                paysNew = em.getReference(paysNew.getClass(), paysNew.getId());
                ville.setPays(paysNew);
            }
            List<Adresse> attachedAdresseListNew = new ArrayList<Adresse>();
            for (Adresse adresseListNewAdresseToAttach : adresseListNew) {
                adresseListNewAdresseToAttach = em.getReference(adresseListNewAdresseToAttach.getClass(), adresseListNewAdresseToAttach.getId());
                attachedAdresseListNew.add(adresseListNewAdresseToAttach);
            }
            adresseListNew = attachedAdresseListNew;
            ville.setAdresseList(adresseListNew);
            ville = em.merge(ville);
            if (paysOld != null && !paysOld.equals(paysNew)) {
                paysOld.getVilleList().remove(ville);
                paysOld = em.merge(paysOld);
            }
            if (paysNew != null && !paysNew.equals(paysOld)) {
                paysNew.getVilleList().add(ville);
                paysNew = em.merge(paysNew);
            }
            for (Adresse adresseListOldAdresse : adresseListOld) {
                if (!adresseListNew.contains(adresseListOldAdresse)) {
                    adresseListOldAdresse.setIdVille(null);
                    adresseListOldAdresse = em.merge(adresseListOldAdresse);
                }
            }
            for (Adresse adresseListNewAdresse : adresseListNew) {
                if (!adresseListOld.contains(adresseListNewAdresse)) {
                    Ville oldIdVilleOfAdresseListNewAdresse = adresseListNewAdresse.getIdVille();
                    adresseListNewAdresse.setIdVille(ville);
                    adresseListNewAdresse = em.merge(adresseListNewAdresse);
                    if (oldIdVilleOfAdresseListNewAdresse != null && !oldIdVilleOfAdresseListNewAdresse.equals(ville)) {
                        oldIdVilleOfAdresseListNewAdresse.getAdresseList().remove(adresseListNewAdresse);
                        oldIdVilleOfAdresseListNewAdresse = em.merge(oldIdVilleOfAdresseListNewAdresse);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ville.getId();
                if (findVille(id) == null) {
                    throw new NonexistentEntityException("The ville with id " + id + " no longer exists.");
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
            Ville ville;
            try {
                ville = em.getReference(Ville.class, id);
                ville.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ville with id " + id + " no longer exists.", enfe);
            }
            Pays pays = ville.getPays();
            if (pays != null) {
                pays.getVilleList().remove(ville);
                pays = em.merge(pays);
            }
            List<Adresse> adresseList = ville.getAdresseList();
            for (Adresse adresseListAdresse : adresseList) {
                adresseListAdresse.setIdVille(null);
                adresseListAdresse = em.merge(adresseListAdresse);
            }
            em.remove(ville);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ville> findVilleEntities() {
        return findVilleEntities(true, -1, -1);
    }

    public List<Ville> findVilleEntities(int maxResults, int firstResult) {
        return findVilleEntities(false, maxResults, firstResult);
    }

    private List<Ville> findVilleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ville.class));
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

    public Ville findVille(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ville.class, id);
        } finally {
            em.close();
        }
    }

    public int getVilleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ville> rt = cq.from(Ville.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
