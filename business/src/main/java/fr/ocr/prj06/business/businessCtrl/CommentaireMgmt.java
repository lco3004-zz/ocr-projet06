package fr.ocr.prj06.business.businessCtrl;

import fr.ocr.prj06.controller.CommentaireJpaCtrl;
import fr.ocr.prj06.model.Commentaire;
import fr.ocr.prj06.model.Spot;

import java.util.ArrayList;

public class CommentaireMgmt {

    private CommentaireJpaCtrl commentaireJpaCtrl;

    public CommentaireMgmt() throws Exception {
        commentaireJpaCtrl = new CommentaireJpaCtrl();
    }

    public Commentaire ajouterCommentaire(Commentaire commentaire) throws Exception {
        return commentaireJpaCtrl.create(commentaire);
    }

    public void supprimerCommentaire(Commentaire commentaire) throws Exception {
        commentaireJpaCtrl.delete(commentaire);
    }

    public Commentaire lireCommentaire(Integer id) throws Exception {
        Commentaire commentaire = new Commentaire();
        commentaire.setIdcommentaire(id);
        return commentaireJpaCtrl.read(commentaire);
    }

    public ArrayList<Commentaire> listerCommentaires(Spot spot) throws Exception {
        return commentaireJpaCtrl.findCommentaires(spot);
    }

    public Commentaire modiferCommentaire(Commentaire commentaire) throws Exception {
        return commentaireJpaCtrl.update(commentaire);
    }
}
