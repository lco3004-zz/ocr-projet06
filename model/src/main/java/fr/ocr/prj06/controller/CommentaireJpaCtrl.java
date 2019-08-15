package fr.ocr.prj06.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.common.JpaEmUtility;
import fr.ocr.prj06.entity.stub.dbCommentaireEntity;
import fr.ocr.prj06.entity.stub.dbSpotEntity;
import fr.ocr.prj06.model.Commentaire;
import fr.ocr.prj06.model.Spot;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

import static fr.ocr.prj06.utility.constante.Messages.ErreurMessages.*;
import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class CommentaireJpaCtrl extends JpaCtrl {

    /**
     * @throws Exception
     */
    public CommentaireJpaCtrl() throws Exception {
        super(dbCommentaireEntity.class);
        setLogs();
    }

    /**
     * @param commentaire
     * @return
     * @throws Exception
     */
    public Commentaire create(Commentaire commentaire) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                dbCommentaireEntity commentaireEntity = new dbCommentaireEntity();

                jpa.getEm().getTransaction().begin();

                commentaireEntity.setEstVisible(commentaire.getEstVisible());
                commentaireEntity.setTexte(commentaire.getTexte());
                commentaireEntity.setSpotBySpotIdspot(jpa.getEm().find(dbSpotEntity.class, commentaire.getIdSpot()));

                jpa.getEm().persist(commentaireEntity);

                jpa.getEm().getTransaction().commit();

                commentaire.setIdcommentaire(commentaireEntity.getIdcommentaire());

                return commentaire;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_CREATE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }


    /**
     * @param commentaire
     * @return
     * @throws Exception
     */
    public Commentaire update(Commentaire commentaire) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();
                dbCommentaireEntity commentaireEntity = jpa.getEm().find(dbCommentaireEntity.class, commentaire.getIdcommentaire());

                commentaireEntity.setEstVisible(commentaire.getEstVisible());
                commentaireEntity.setTexte(commentaire.getTexte());

                jpa.getEm().getTransaction().commit();

                commentaire.setEstVisible(commentaireEntity.getEstVisible());
                commentaire.setTexte(commentaireEntity.getTexte());

                return commentaire;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_UPDATE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }


    /**
     * @param commentaire
     * @return
     * @throws Exception
     */
    public Commentaire read(Commentaire commentaire) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();
                dbCommentaireEntity commentaireEntity = jpa.getEm().find(dbCommentaireEntity.class, commentaire.getIdcommentaire());
                jpa.getEm().getTransaction().commit();

                commentaire.setEstVisible(commentaireEntity.getEstVisible());
                commentaire.setTexte(commentaireEntity.getTexte());
                commentaire.setIdcommentaire(commentaireEntity.getIdcommentaire());
                commentaire.setIdSpot(commentaireEntity.getSpotBySpotIdspot().getIdspot());

                return commentaire;

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READ_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }

    }


    /**
     * @param spot
     * @return
     * @throws Exception
     */
    public ArrayList<Commentaire> findCommentaires(Spot spot) throws Exception {
        try {
            ArrayList<Commentaire> listCommentaires = new ArrayList<>();
            for (dbCommentaireEntity y : (List<dbCommentaireEntity>) findDbEntities()) {
                Commentaire x = new Commentaire();
                x.setEstVisible(y.getEstVisible());
                x.setTexte(y.getTexte());
                x.setIdcommentaire(y.getIdcommentaire());
                x.setIdSpot(y.getSpotBySpotIdspot().getIdspot());
                listCommentaires.add(x);
            }
            return listCommentaires;

        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READLISTE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
    }

    /**
     * @param commentaire
     * @throws Exception
     */
    public void delete(Commentaire commentaire) throws Exception {
        try (JpaEmUtility jpa = new JpaEmUtility()) {
            try {
                jpa.getEm().getTransaction().begin();
                dbCommentaireEntity commentaireEntity = jpa.getEm().find(dbCommentaireEntity.class, commentaire.getIdcommentaire());
                jpa.getEm().remove(commentaireEntity);
                jpa.getEm().getTransaction().commit();
            } catch (Exception hex2) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(hex2);
            }
        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_DELETE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }
        try {

        } catch (Exception hex1) {
            logs.maTrace(Level.ERROR, CONTROLLER_JPA_READLISTE_COMMENTAIRE.getMessageErreur() + hex1.getLocalizedMessage());
            throw new Exception(hex1);
        }

    }
}



