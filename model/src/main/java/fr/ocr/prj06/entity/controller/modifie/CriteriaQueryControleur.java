/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eni.jpa.controller.modifie;

import com.eni.jpa.entity.*;
import com.eni.jpa.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author martial
 */
public class CriteriaQueryControleur {

    public CriteriaQueryControleur() {
    }

    public EntityManager getEntityManager() {
        return JpaUtil.getEmf().createEntityManager();
    }

    /**
     * Retourne une List<Personne> en fonction des critères de recherche.
     * Les critères null ou vide sont ignorés.
     * Si aucun critère, toutes les Personne sont retournées dans la liste
     *
     * @param nom           le nom de la personne à chercher
     * @param prenom        le prenom de la personne à chercher
     * @param dateNaissance la date de naissance de la personne à chercher
     * @param numSecu       le numSecu de la personne à chercher
     * @param numTel        le numéroe de téléphone de la personne à chercher
     * @return la liste de personne (vide si pas de résultat)
     */
    public List<Personne> rechercher(String nom, String prenom, Date dateNaissance, String numSecu, String numTel) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Personne> cq = cb.createQuery(Personne.class);
            Root<Personne> p = cq.from(Personne.class);
            List<Predicate> lstPredicate = new ArrayList<>();
            if (nom != null && !"".equals(nom.trim())) {
                lstPredicate.add(cb.like(p.get(Personne_.nom), nom.trim()));
            }
            if (prenom != null && !"".equals(prenom.trim())) {
                lstPredicate.add(cb.like(p.get(Personne_.prenom), prenom.trim()));
            }
            if (dateNaissance != null) {
                lstPredicate.add(cb.equal(p.get(Personne_.dateNaissance), dateNaissance));
            }
            if (numSecu != null && !"".equals(numSecu.trim())) {
                //cette jointure n'est pas obligé d'être déclaré car relation OneToOne
                lstPredicate.add(cb.like(p.get(Personne_.personneDetail).get(PersonneDetail_.numSecu), numSecu.trim()));
            }
            if (numTel != null && !"".equals(numTel.trim())) {
                //Création de la jointure uniquement a ce moment !
                Join<Personne, Telephone> tel = p.join(Personne_.telephoneList);
                lstPredicate.add(cb.like(tel.get(Telephone_.numero), numTel.trim()));
            }

            if (!lstPredicate.isEmpty()) {
                cq.where(lstPredicate.toArray(new Predicate[lstPredicate.size()]));
            }
            //Définit que les entités doivent être uniques dans la liste
            cq.select(p).distinct(true);

            return em.createQuery(cq).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
