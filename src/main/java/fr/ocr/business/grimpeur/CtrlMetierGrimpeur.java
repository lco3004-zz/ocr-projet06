package fr.ocr.business.grimpeur;


import fr.ocr.model.constantes.UserProfile;
import fr.ocr.model.controllers.JpaCtrlGrimpeur;
import fr.ocr.model.entities.DbGrimpeur;

import java.util.ArrayList;
import java.util.List;

public interface CtrlMetierGrimpeur {
    CtrlMetierGrimpeur CTRL_METIER_GRIMPEUR = new CtrlMetierGrimpeur_impl();

    DbGrimpeur ajouterGrimpeur(String nom, String mdp) throws Exception ;


    DbGrimpeur modifierProfilGrimpeur(Integer idUser, UserProfile userProfile) throws Exception ;

    List<DbGrimpeur> listerTousGrimpeurs() throws Exception;

    List<DbGrimpeur> listerTousMembres() throws Exception;
    DbGrimpeur consulterCeGrimpeur(Integer idGrimpeur) throws Exception;

    DbGrimpeur connecterCeGrimpeur(String NomGrimpeur, String mdpGrimpeur) throws Exception;
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
    public List<DbGrimpeur> listerTousGrimpeurs() throws Exception {
        ArrayList<UserProfile> userProfiles = new ArrayList<>();
        userProfiles.add(UserProfile.GRIMPEUR);
        userProfiles.add(UserProfile.MEMBRE);
        return jpaCtrlGrimpeur.findGrimpeurByProfile(userProfiles);
    }

    @Override
    public List<DbGrimpeur> listerTousMembres() throws Exception {
        ArrayList<UserProfile> userProfiles = new ArrayList<>();
        userProfiles.add(UserProfile.MEMBRE);
        return jpaCtrlGrimpeur.findGrimpeurByProfile(userProfiles);
    }

    @Override
    public DbGrimpeur consulterCeGrimpeur(Integer idGrimpeur) throws Exception {
        return jpaCtrlGrimpeur.readGrimpeur(idGrimpeur);
    }

    @Override
    public DbGrimpeur connecterCeGrimpeur(String NomGrimpeur, String mdpGrimpeur) throws Exception {
        List<DbGrimpeur> dbGrimpeurs = jpaCtrlGrimpeur.findGrimpeurByName(NomGrimpeur);
        DbGrimpeur dbGrimpeur = null;

        if (dbGrimpeurs.size() == 1) {
            if (dbGrimpeurs.get(0).getUserPass().contentEquals(new StringBuffer(mdpGrimpeur))) {
                dbGrimpeur = dbGrimpeurs.get(0);
            }
        }
        return dbGrimpeur;
    }
}
