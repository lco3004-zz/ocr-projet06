/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.netbeans;

import com.eni.jpa.controller.exceptions.IllegalOrphanException;
import com.eni.jpa.controller.exceptions.NonexistentEntityException;
import com.eni.jpa.entity.Adresse;
import com.eni.jpa.entity.PersonneAdresse;
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
public class AdresseJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public AdresseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Adresse adresse) {
        if (adresse.getPersonneAdresseList() == null) {
            adresse.setPersonneAdresseList(new ArrayList<PersonneAdresse>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ville idVille = adresse.getIdVille();
            if (idVille != null) {
                idVille = em.getReference(idVille.getClass(), idVille.getId());
                adresse.setIdVille(idVille);
            }
            List<PersonneAdresse> attachedPersonneAdresseList = new ArrayList<PersonneAdresse>();
            for (PersonneAdresse personneAdresseListPersonneAdresseToAttach : adresse.getPersonneAdresseList()) {
                personneAdresseListPersonneAdresseToAttach = em.getReference(personneAdresseListPersonneAdresseToAttach.getClass(), personneAdresseListPersonneAdresseToAttach.getPersonneAdressePK());
                attachedPersonneAdresseList.add(personneAdresseListPersonneAdresseToAttach);
            }
            adresse.setPersonneAdresseList(attachedPersonneAdresseList);
            em.persist(adresse);
            if (idVille != null) {
                idVille.getAdresseList().add(adresse);
                idVille = em.merge(idVille);
            }
            for (PersonneAdresse personneAdresseListPersonneAdresse : adresse.getPersonneAdresseList()) {
                Adresse oldAdresseOfPersonneAdresseListPersonneAdresse = personneAdresseListPersonneAdresse.getAdresse();
                personneAdresseListPersonneAdresse.setAdresse(adresse);
                personneAdresseListPersonneAdresse = em.merge(personneAdresseListPersonneAdresse);
                if (oldAdresseOfPersonneAdresseListPersonneAdresse != null) {
                    oldAdresseOfPersonneAdresseListPersonneAdresse.getPersonneAdresseList().remove(personneAdresseListPersonneAdresse);
                    oldAdresseOfPersonneAdresseListPersonneAdresse = em.merge(oldAdresseOfPersonneAdresseListPersonneAdresse);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Adresse adresse) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adresse persistentAdresse = em.find(Adresse.class, adresse.getId());
            Ville idVilleOld = persistentAdresse.getIdVille();
            Ville idVilleNew = adresse.getIdVille();
            List<PersonneAdresse> personneAdresseListOld = persistentAdresse.getPersonneAdresseList();
            List<PersonneAdresse> personneAdresseListNew = adresse.getPersonneAdresseList();
            List<String> illegalOrphanMessages = null;
            for (PersonneAdresse personneAdresseListOldPersonneAdresse : personneAdresseListOld) {
                if (!personneAdresseListNew.contains(personneAdresseListOldPersonneAdresse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonneAdresse " + personneAdresseListOldPersonneAdresse + " since its adresse field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idVilleNew != null) {
                idVilleNew = em.getReference(idVilleNew.getClass(), idVilleNew.getId());
                adresse.setIdVille(idVilleNew);
            }
            List<PersonneAdresse> attachedPersonneAdresseListNew = new ArrayList<PersonneAdresse>();
            for (PersonneAdresse personneAdresseListNewPersonneAdresseToAttach : personneAdresseListNew) {
                personneAdresseListNewPersonneAdresseToAttach = em.getReference(personneAdresseListNewPersonneAdresseToAttach.getClass(), personneAdresseListNewPersonneAdresseToAttach.getPersonneAdressePK());
                attachedPersonneAdresseListNew.add(personneAdresseListNewPersonneAdresseToAttach);
            }
            personneAdresseListNew = attachedPersonneAdresseListNew;
            adresse.setPersonneAdresseList(personneAdresseListNew);
            adresse = em.merge(adresse);
            if (idVilleOld != null && !idVilleOld.equals(idVilleNew)) {
                idVilleOld.getAdresseList().remove(adresse);
                idVilleOld = em.merge(idVilleOld);
            }
            if (idVilleNew != null && !idVilleNew.equals(idVilleOld)) {
                idVilleNew.getAdresseList().add(adresse);
                idVilleNew = em.merge(idVilleNew);
            }
            for (PersonneAdresse personneAdresseListNewPersonneAdresse : personneAdresseListNew) {
                if (!personneAdresseListOld.contains(personneAdresseListNewPersonneAdresse)) {
                    Adresse oldAdresseOfPersonneAdresseListNewPersonneAdresse = personneAdresseListNewPersonneAdresse.getAdresse();
                    personneAdresseListNewPersonneAdresse.setAdresse(adresse);
                    personneAdresseListNewPersonneAdresse = em.merge(personneAdresseListNewPersonneAdresse);
                    if (oldAdresseOfPersonneAdresseListNewPersonneAdresse != null && !oldAdresseOfPersonneAdresseListNewPersonneAdresse.equals(adresse)) {
                        oldAdresseOfPersonneAdresseListNewPersonneAdresse.getPersonneAdresseList().remove(personneAdresseListNewPersonneAdresse);
                        oldAdresseOfPersonneAdresseListNewPersonneAdresse = em.merge(oldAdresseOfPersonneAdresseListNewPersonneAdresse);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = adresse.getId();
                if (findAdresse(id) == null) {
                    throw new NonexistentEntityException("The adresse with id " + id + " no longer exists.");
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
            Adresse adresse;
            try {
                adresse = em.getReference(Adresse.class, id);
                adresse.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The adresse with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PersonneAdresse> personneAdresseListOrphanCheck = adresse.getPersonneAdresseList();
            for (PersonneAdresse personneAdresseListOrphanCheckPersonneAdresse : personneAdresseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Adresse (" + adresse + ") cannot be destroyed since the PersonneAdresse " + personneAdresseListOrphanCheckPersonneAdresse + " in its personneAdresseList field has a non-nullable adresse field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ville idVille = adresse.getIdVille();
            if (idVille != null) {
                idVille.getAdresseList().remove(adresse);
                idVille = em.merge(idVille);
            }
            em.remove(adresse);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Adresse> findAdresseEntities() {
        return findAdresseEntities(true, -1, -1);
    }

    public List<Adresse> findAdresseEntities(int maxResults, int firstResult) {
        return findAdresseEntities(false, maxResults, firstResult);
    }

    private List<Adresse> findAdresseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Adresse.class));
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

    public Adresse findAdresse(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Adresse.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdresseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Adresse> rt = cq.from(Adresse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
