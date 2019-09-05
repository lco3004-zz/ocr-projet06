package fr.ocr.business.grimpeur;


import fr.ocr.model.constantes.UserProfile;
import fr.ocr.model.controllers.JpaCtrlGrimpeur;
import fr.ocr.model.entities.DbGrimpeur;

import java.util.List;

public interface CtrlMetierGrimpeur {
    CtrlMetierGrimpeur CTRL_METIER_GRIMPEUR = new CtrlMetierGrimpeur_impl();

    DbGrimpeur ajouterGrimpeur(String nom, String mdp) throws Exception ;


    DbGrimpeur modifierProfilGrimpeur(Integer idUser, UserProfile userProfile) throws Exception ;

    List<DbGrimpeur> listeTousGrimpeurs() throws Exception;
    List<DbGrimpeur> listeTousMembre() throws Exception;
    DbGrimpeur consulterCeGrimpeur(Integer idGrimpeur) throws Exception;
    List<DbGrimpeur>  retrouverCeGrimpeur(String NomGrimpeur) throws Exception;

}

class  CtrlMetierGrimpeur_impl implements CtrlMetierGrimpeur{

    private JpaCtrlGrimpeur jpaCtrlGrimpeur;

    CtrlMetierGrimpeur_impl() {

        this.jpaCtrlGrimpeur = JpaCtrlGrimpeur.JPA_CTRL_GRIMPEUR;
    }

    @Override
    public DbGrimpeur ajouterGrimpeur(String nom, String mdp) throws Exception {
        DbGrimpeur dbGrimpeur = new DbGrimpeur();
        dbGrimpeur.setUserName(nom);
        dbGrimpeur.setUserPass(mdp);
        dbGrimpeur.setRoleName(UserProfile.GRIMPEUR);
        return  jpaCtrlGrimpeur.createGrimpeur(dbGrimpeur);
    }

    @Override
    public DbGrimpeur modifierProfilGrimpeur(Integer idUser, UserProfile userProfile) throws Exception {
        return jpaCtrlGrimpeur.updateGrimpeur(idUser,userProfile);
    }

    @Override
    public List<DbGrimpeur> listeTousGrimpeurs() throws Exception {
        return jpaCtrlGrimpeur.findListGrimpeurs(UserProfile.GRIMPEUR);
    }

    @Override
    public List<DbGrimpeur> listeTousMembre() throws Exception {
        return jpaCtrlGrimpeur.findListGrimpeurs(UserProfile.MEMBRE);
    }

    @Override
    public DbGrimpeur consulterCeGrimpeur(Integer idGrimpeur) throws Exception {
        return jpaCtrlGrimpeur.readGrimpeur(idGrimpeur);
    }

    @Override
    public List<DbGrimpeur>  retrouverCeGrimpeur(String NomGrimpeur) throws Exception {
        return jpaCtrlGrimpeur.findListGrimpeurs(NomGrimpeur);
    }
}
