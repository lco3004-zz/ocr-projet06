/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.modifie;

import com.eni.jpa.controller.exceptions.IllegalOrphanException;
import com.eni.jpa.controller.exceptions.NonexistentEntityException;
import com.eni.jpa.entity.Personne;
import com.eni.jpa.entity.PersonneAdresse;
import com.eni.jpa.entity.PersonneDetail;
import com.eni.jpa.entity.Telephone;
import com.eni.jpa.util.JpaUtil;

import javax.persistence.EntityManager;
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
public class PersonneControleur implements Serializable {

    public PersonneControleur() {
    }

    public EntityManager getEntityManager() {
        return JpaUtil.getEmf().createEntityManager();
    }

    public void create(Personne personne) {
        if (personne.getTelephoneList() == null) {
            personne.setTelephoneList(new ArrayList<Telephone>());
        }
        if (personne.getPersonneAdresseList() == null) {
            personne.setPersonneAdresseList(new ArrayList<PersonneAdresse>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Telephone> attachedTelephoneList = new ArrayList<Telephone>();
            for (Telephone telephoneListTelephoneToAttach : personne.getTelephoneList()) {
                telephoneListTelephoneToAttach = em.getReference(telephoneListTelephoneToAttach.getClass(), telephoneListTelephoneToAttach.getId());
                attachedTelephoneList.add(telephoneListTelephoneToAttach);
            }
            personne.setTelephoneList(attachedTelephoneList);
            List<PersonneAdresse> attachedPersonneAdresseList = new ArrayList<PersonneAdresse>();
            for (PersonneAdresse personneAdresseListPersonneAdresseToAttach : personne.getPersonneAdresseList()) {
                personneAdresseListPersonneAdresseToAttach = em.getReference(personneAdresseListPersonneAdresseToAttach.getClass(), personneAdresseListPersonneAdresseToAttach.getPersonneAdressePK());
                attachedPersonneAdresseList.add(personneAdresseListPersonneAdresseToAttach);
            }
            personne.setPersonneAdresseList(attachedPersonneAdresseList);
            em.persist(personne);

            for (Telephone telephoneListTelephone : personne.getTelephoneList()) {
                Personne oldIdPersonneOfTelephoneListTelephone = telephoneListTelephone.getIdPersonne();
                telephoneListTelephone.setIdPersonne(personne);
                telephoneListTelephone = em.merge(telephoneListTelephone);
                if (oldIdPersonneOfTelephoneListTelephone != null) {
                    oldIdPersonneOfTelephoneListTelephone.getTelephoneList().remove(telephoneListTelephone);
                    oldIdPersonneOfTelephoneListTelephone = em.merge(oldIdPersonneOfTelephoneListTelephone);
                }
            }
            for (PersonneAdresse personneAdresseListPersonneAdresse : personne.getPersonneAdresseList()) {
                Personne oldPersonneOfPersonneAdresseListPersonneAdresse = personneAdresseListPersonneAdresse.getPersonne();
                personneAdresseListPersonneAdresse.setPersonne(personne);
                personneAdresseListPersonneAdresse = em.merge(personneAdresseListPersonneAdresse);
                if (oldPersonneOfPersonneAdresseListPersonneAdresse != null) {
                    oldPersonneOfPersonneAdresseListPersonneAdresse.getPersonneAdresseList().remove(personneAdresseListPersonneAdresse);
                    oldPersonneOfPersonneAdresseListPersonneAdresse = em.merge(oldPersonneOfPersonneAdresseListPersonneAdresse);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personne personne) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personne persistentPersonne = em.find(Personne.class, personne.getId());
            PersonneDetail personneDetailOld = persistentPersonne.getPersonneDetail();
            PersonneDetail personneDetailNew = personne.getPersonneDetail();
            List<Telephone> telephoneListOld = persistentPersonne.getTelephoneList();
            List<Telephone> telephoneListNew = personne.getTelephoneList();
            List<PersonneAdresse> personneAdresseListOld = persistentPersonne.getPersonneAdresseList();
            List<PersonneAdresse> personneAdresseListNew = personne.getPersonneAdresseList();
            List<String> illegalOrphanMessages = null;
            if (personneDetailOld != null && !personneDetailOld.equals(personneDetailNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain PersonneDetail " + personneDetailOld + " since its personne field is not nullable.");
            }
            for (PersonneAdresse personneAdresseListOldPersonneAdresse : personneAdresseListOld) {
                if (!personneAdresseListNew.contains(personneAdresseListOldPersonneAdresse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonneAdresse " + personneAdresseListOldPersonneAdresse + " since its personne field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Telephone> attachedTelephoneListNew = new ArrayList<Telephone>();
            for (Telephone telephoneListNewTelephoneToAttach : telephoneListNew) {
                telephoneListNewTelephoneToAttach = em.getReference(telephoneListNewTelephoneToAttach.getClass(), telephoneListNewTelephoneToAttach.getId());
                attachedTelephoneListNew.add(telephoneListNewTelephoneToAttach);
            }
            telephoneListNew = attachedTelephoneListNew;
            personne.setTelephoneList(telephoneListNew);
            List<PersonneAdresse> attachedPersonneAdresseListNew = new ArrayList<PersonneAdresse>();
            for (PersonneAdresse personneAdresseListNewPersonneAdresseToAttach : personneAdresseListNew) {
                personneAdresseListNewPersonneAdresseToAttach = em.getReference(personneAdresseListNewPersonneAdresseToAttach.getClass(), personneAdresseListNewPersonneAdresseToAttach.getPersonneAdressePK());
                attachedPersonneAdresseListNew.add(personneAdresseListNewPersonneAdresseToAttach);
            }
            personneAdresseListNew = attachedPersonneAdresseListNew;
            personne.setPersonneAdresseList(personneAdresseListNew);
            personne = em.merge(personne);
            for (Telephone telephoneListOldTelephone : telephoneListOld) {
                if (!telephoneListNew.contains(telephoneListOldTelephone)) {
                    telephoneListOldTelephone.setIdPersonne(null);
                    telephoneListOldTelephone = em.merge(telephoneListOldTelephone);
                }
            }
            for (Telephone telephoneListNewTelephone : telephoneListNew) {
                if (!telephoneListOld.contains(telephoneListNewTelephone)) {
                    Personne oldIdPersonneOfTelephoneListNewTelephone = telephoneListNewTelephone.getIdPersonne();
                    telephoneListNewTelephone.setIdPersonne(personne);
                    telephoneListNewTelephone = em.merge(telephoneListNewTelephone);
                    if (oldIdPersonneOfTelephoneListNewTelephone != null && !oldIdPersonneOfTelephoneListNewTelephone.equals(personne)) {
                        oldIdPersonneOfTelephoneListNewTelephone.getTelephoneList().remove(telephoneListNewTelephone);
                        oldIdPersonneOfTelephoneListNewTelephone = em.merge(oldIdPersonneOfTelephoneListNewTelephone);
                    }
                }
            }
            for (PersonneAdresse personneAdresseListNewPersonneAdresse : personneAdresseListNew) {
                if (!personneAdresseListOld.contains(personneAdresseListNewPersonneAdresse)) {
                    Personne oldPersonneOfPersonneAdresseListNewPersonneAdresse = personneAdresseListNewPersonneAdresse.getPersonne();
                    personneAdresseListNewPersonneAdresse.setPersonne(personne);
                    personneAdresseListNewPersonneAdresse = em.merge(personneAdresseListNewPersonneAdresse);
                    if (oldPersonneOfPersonneAdresseListNewPersonneAdresse != null && !oldPersonneOfPersonneAdresseListNewPersonneAdresse.equals(personne)) {
                        oldPersonneOfPersonneAdresseListNewPersonneAdresse.getPersonneAdresseList().remove(personneAdresseListNewPersonneAdresse);
                        oldPersonneOfPersonneAdresseListNewPersonneAdresse = em.merge(oldPersonneOfPersonneAdresseListNewPersonneAdresse);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personne.getId();
                if (findPersonne(id) == null) {
                    throw new NonexistentEntityException("The personne with id " + id + " no longer exists.");
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
            Personne personne;
            try {
                personne = em.getReference(Personne.class, id);
                personne.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personne with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;

            List<PersonneAdresse> personneAdresseListOrphanCheck = personne.getPersonneAdresseList();
            for (PersonneAdresse personneAdresseListOrphanCheckPersonneAdresse : personneAdresseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personne (" + personne + ") cannot be destroyed since the PersonneAdresse " + personneAdresseListOrphanCheckPersonneAdresse + " in its personneAdresseList field has a non-nullable personne field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Telephone> telephoneList = personne.getTelephoneList();
            for (Telephone telephoneListTelephone : telephoneList) {
                telephoneListTelephone.setIdPersonne(null);
                telephoneListTelephone = em.merge(telephoneListTelephone);
            }
            em.remove(personne);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personne> findPersonneEntities() {
        return findPersonneEntities(true, -1, -1);
    }

    public List<Personne> findPersonneEntities(int maxResults, int firstResult) {
        return findPersonneEntities(false, maxResults, firstResult);
    }

    private List<Personne> findPersonneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personne.class));
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

    public Personne findPersonne(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personne.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personne> rt = cq.from(Personne.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
