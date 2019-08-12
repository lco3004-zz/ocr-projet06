/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.netbeans;

import com.eni.jpa.controller.exceptions.NonexistentEntityException;
import com.eni.jpa.controller.exceptions.PreexistingEntityException;
import com.eni.jpa.entity.Adresse;
import com.eni.jpa.entity.Personne;
import com.eni.jpa.entity.PersonneAdresse;
import com.eni.jpa.entity.PersonneAdressePK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author martial
 */
public class PersonneAdresseJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public PersonneAdresseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonneAdresse personneAdresse) throws Exception {
        if (personneAdresse.getPersonneAdressePK() == null) {
            personneAdresse.setPersonneAdressePK(new PersonneAdressePK());
        }
        personneAdresse.getPersonneAdressePK().setIdPersonne(personneAdresse.getPersonne().getId());
        personneAdresse.getPersonneAdressePK().setIdAdresse(personneAdresse.getAdresse().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adresse adresse = personneAdresse.getAdresse();
            if (adresse != null) {
                adresse = em.getReference(adresse.getClass(), adresse.getId());
                personneAdresse.setAdresse(adresse);
            }
            Personne personne = personneAdresse.getPersonne();
            if (personne != null) {
                personne = em.getReference(personne.getClass(), personne.getId());
                personneAdresse.setPersonne(personne);
            }
            em.persist(personneAdresse);
            if (adresse != null) {
                adresse.getPersonneAdresseList().add(personneAdresse);
                adresse = em.merge(adresse);
            }
            if (personne != null) {
                personne.getPersonneAdresseList().add(personneAdresse);
                personne = em.merge(personne);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonneAdresse(personneAdresse.getPersonneAdressePK()) != null) {
                throw new PreexistingEntityException("PersonneAdresse " + personneAdresse + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonneAdresse personneAdresse) throws Exception {
        personneAdresse.getPersonneAdressePK().setIdPersonne(personneAdresse.getPersonne().getId());
        personneAdresse.getPersonneAdressePK().setIdAdresse(personneAdresse.getAdresse().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersonneAdresse persistentPersonneAdresse = em.find(PersonneAdresse.class, personneAdresse.getPersonneAdressePK());
            Adresse adresseOld = persistentPersonneAdresse.getAdresse();
            Adresse adresseNew = personneAdresse.getAdresse();
            Personne personneOld = persistentPersonneAdresse.getPersonne();
            Personne personneNew = personneAdresse.getPersonne();
            if (adresseNew != null) {
                adresseNew = em.getReference(adresseNew.getClass(), adresseNew.getId());
                personneAdresse.setAdresse(adresseNew);
            }
            if (personneNew != null) {
                personneNew = em.getReference(personneNew.getClass(), personneNew.getId());
                personneAdresse.setPersonne(personneNew);
            }
            personneAdresse = em.merge(personneAdresse);
            if (adresseOld != null && !adresseOld.equals(adresseNew)) {
                adresseOld.getPersonneAdresseList().remove(personneAdresse);
                adresseOld = em.merge(adresseOld);
            }
            if (adresseNew != null && !adresseNew.equals(adresseOld)) {
                adresseNew.getPersonneAdresseList().add(personneAdresse);
                adresseNew = em.merge(adresseNew);
            }
            if (personneOld != null && !personneOld.equals(personneNew)) {
                personneOld.getPersonneAdresseList().remove(personneAdresse);
                personneOld = em.merge(personneOld);
            }
            if (personneNew != null && !personneNew.equals(personneOld)) {
                personneNew.getPersonneAdresseList().add(personneAdresse);
                personneNew = em.merge(personneNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PersonneAdressePK id = personneAdresse.getPersonneAdressePK();
                if (findPersonneAdresse(id) == null) {
                    throw new NonexistentEntityException("The personneAdresse with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PersonneAdressePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersonneAdresse personneAdresse;
            try {
                personneAdresse = em.getReference(PersonneAdresse.class, id);
                personneAdresse.getPersonneAdressePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personneAdresse with id " + id + " no longer exists.", enfe);
            }
            Adresse adresse = personneAdresse.getAdresse();
            if (adresse != null) {
                adresse.getPersonneAdresseList().remove(personneAdresse);
                adresse = em.merge(adresse);
            }
            Personne personne = personneAdresse.getPersonne();
            if (personne != null) {
                personne.getPersonneAdresseList().remove(personneAdresse);
                personne = em.merge(personne);
            }
            em.remove(personneAdresse);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonneAdresse> findPersonneAdresseEntities() {
        return findPersonneAdresseEntities(true, -1, -1);
    }

    public List<PersonneAdresse> findPersonneAdresseEntities(int maxResults, int firstResult) {
        return findPersonneAdresseEntities(false, maxResults, firstResult);
    }

    private List<PersonneAdresse> findPersonneAdresseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonneAdresse.class));
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

    public PersonneAdresse findPersonneAdresse(PersonneAdressePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonneAdresse.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonneAdresseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonneAdresse> rt = cq.from(PersonneAdresse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
