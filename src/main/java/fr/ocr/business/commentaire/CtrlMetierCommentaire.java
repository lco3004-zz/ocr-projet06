package fr.ocr.business.commentaire;


import fr.ocr.prj06.controllers.JpaCtrlCommentaire;
import fr.ocr.prj06.entities.DbCommentaire;

import java.util.List;

public interface CtrlMetierCommentaire {

        CtrlMetierCommentaire CTRL_METIER_COMMENTAIRE = new CtrlMetierCommentaire_impl();

         DbCommentaire ajouterCommentaire(Integer idSpot, String txtComment, Boolean isVisible) throws Exception ;

         void supprimerCommentaire(Integer idCommentaire, Boolean doitEtreSupprimer) throws Exception ;

         DbCommentaire lireCommentaire(Integer id) throws Exception ;

         List listerCommentairesActifs(Integer idSpot) throws Exception ;

         List listerCommentairesArchives(Integer idSpot) throws Exception ;

         List listerTousLesCommentaires(Integer idSpot) throws Exception ;

         DbCommentaire modiferCommentaire(Integer idCommentaire, String txtComment, Boolean isVisible) throws Exception ;

}


class  CtrlMetierCommentaire_impl implements CtrlMetierCommentaire{

    private JpaCtrlCommentaire jpaCtrlCommentaire;
    CtrlMetierCommentaire_impl() {
        this.jpaCtrlCommentaire = JpaCtrlCommentaire.JPA_CTRL_COMMENTAIRE;
    }

    public DbCommentaire ajouterCommentaire(Integer idSpot, String txtComment, Boolean isVisible) throws Exception {
            return jpaCtrlCommentaire.createCommentaire(idSpot, txtComment, isVisible);
    }


    public void supprimerCommentaire(Integer idCommentaire, Boolean doitEtreSupprimer) throws Exception {
            if (doitEtreSupprimer)
                jpaCtrlCommentaire.deleteCommentaire(idCommentaire);
            else {
                jpaCtrlCommentaire.archiveCommentaire(idCommentaire);
            }
    }

    public DbCommentaire lireCommentaire(Integer id) throws Exception {
            return jpaCtrlCommentaire.readCommentaire(id);
    }


    public List listerCommentairesActifs(Integer idSpot) throws Exception {
            return jpaCtrlCommentaire.findListeCommentaires(idSpot, true, true);
    }

    public List listerCommentairesArchives(Integer idSpot) throws Exception {
            return jpaCtrlCommentaire.findListeCommentaires(idSpot, true, false);
    }


    public List listerTousLesCommentaires(Integer idSpot) throws Exception {
            return jpaCtrlCommentaire.findListeCommentaires(idSpot, false,null);
    }


    public DbCommentaire modiferCommentaire(Integer idCommentaire, String txtComment, Boolean isVisible) throws Exception {
            return jpaCtrlCommentaire.updateCommentaire(idCommentaire, txtComment, isVisible);
    }

}
