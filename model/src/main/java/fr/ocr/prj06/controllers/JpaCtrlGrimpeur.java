package fr.ocr.prj06.controllers;

import fr.ocr.prj06.constantes.UserProfile;
import fr.ocr.prj06.entities.DbGrimpeur;

import java.util.List;

public interface JpaCtrlGrimpeur {
    JpaCtrlGrimpeur getInstance = new JpaCtrlGrimpeur_impl();
    DbGrimpeur readGrimpeur(int idGrimpeur) throws Exception ;
    List<DbGrimpeur> findListGrimpeurs(UserProfile userProfile) throws Exception;
    DbGrimpeur createGrimpeur(DbGrimpeur dbGrimpeur) throws Exception;
    DbGrimpeur updateGrimpeur(DbGrimpeur dbGrimpeur) throws Exception;
}

class JpaCtrlGrimpeur_impl implements JpaCtrlGrimpeur {
    @Override
    public DbGrimpeur readGrimpeur(int idGrimpeur) throws Exception {

        return null;
    }

    @Override
    public List<DbGrimpeur> findListGrimpeurs(UserProfile userProfile) throws Exception {
        return null;
    }

    @Override
    public DbGrimpeur createGrimpeur(DbGrimpeur dbGrimpeur) throws Exception {
        return null;
    }

    @Override
    public DbGrimpeur updateGrimpeur(DbGrimpeur dbGrimpeur) throws Exception {
        return null;
    }
}
