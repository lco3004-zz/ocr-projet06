package fr.ocr.prj06.business.grimpeur;


import fr.ocr.prj06.constantes.UserProfile;
import fr.ocr.prj06.controllers.JpaCtrlGrimpeur;
import fr.ocr.prj06.entities.DbGrimpeur;

import java.util.List;

public interface CtrlMetierGrimpeur {
    CtrlMetierGrimpeur CTRL_METIER_GRIMPEUR = new CtrlMetierGrimpeur_impl();

    DbGrimpeur ajouterGrimpeur(DbGrimpeur dbGrimpeur) throws Exception ;


    DbGrimpeur modifierProfilGrimpeur(Integer idUser, UserProfile userProfile) throws Exception ;

    List<DbGrimpeur> listeTousGrimpeurs() throws Exception;
    List<DbGrimpeur> listeTousMembre() throws Exception;
    DbGrimpeur consulterCeGrimpeur(Integer idGrimpeur) throws Exception;

}

class  CtrlMetierGrimpeur_impl implements CtrlMetierGrimpeur{

    private JpaCtrlGrimpeur jpaCtrlGrimpeur;

    CtrlMetierGrimpeur_impl() {

        this.jpaCtrlGrimpeur = JpaCtrlGrimpeur.JPA_CTRL_GRIMPEUR;
    }

    @Override
    public DbGrimpeur ajouterGrimpeur(DbGrimpeur dbGrimpeur) throws Exception {
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
}
