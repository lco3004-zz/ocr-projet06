package fr.ocr.prj06.controllers;

import fr.ocr.prj06.entities.DbLongueur;
import fr.ocr.prj06.entities.DbSecteur;
import fr.ocr.prj06.entities.DbSpot;
import fr.ocr.prj06.entities.DbVoie;


public class JpaCtrl  {


    /*
     *************************************************************************************************************
     * LONGUEUR
     * ************************************************************************************************************
     */
    public DbLongueur createLongueur(Integer idVoie, String nom, String cotation, Integer nbSpits) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            DbLongueur dbLongueur = new DbLongueur();
            try {
                jpa.getEm().getTransaction().begin();
                dbLongueur.setCotation(cotation);
                dbLongueur.setNom(nom);
                dbLongueur.setNombreDeSpits(nbSpits);
                dbLongueur.setVoieByVoieIdvoie(jpa.getEm().find(DbVoie.class, idVoie));

                jpa.getEm().persist(dbLongueur);

                jpa.getEm().getTransaction().commit();

                return dbLongueur;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    /*
     *************************************************************************************************************
     * VOIE
     * ************************************************************************************************************
     */
    public DbVoie createVoie(Integer idSecteur, String nom) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            DbVoie dbVoie = new DbVoie();
            try {
                jpa.getEm().getTransaction().begin();
                dbVoie.setNom(nom);
                dbVoie.setSecteurBySecteurIdsecteur(jpa.getEm().find(DbSecteur.class, idSecteur));

                jpa.getEm().persist(dbVoie);

                jpa.getEm().getTransaction().commit();

                return dbVoie;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    /*
     *************************************************************************************************************
     * SECTEUR
     * ************************************************************************************************************
     */
    public DbSecteur createSecteur(Integer idSpot, String nom) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            DbSecteur dbSecteur = new DbSecteur();
            try {
                jpa.getEm().getTransaction().begin();
                dbSecteur.setNom(nom);
                dbSecteur.setSpotBySpotIdspot(jpa.getEm().find(DbSpot.class, idSpot));

                jpa.getEm().persist(dbSecteur);

                jpa.getEm().getTransaction().commit();

                return dbSecteur;
            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }
}
