/*
 * **********************************************************
 * Projet 06
 * Contrôleur : méthode en lien avec les utilsiteurs (Grimpeur)
 *
 * Le nom de chaque méthode suffit à comprendre sa fonction
 * ************************************************************
 */

package fr.ocr.business.grimpeur;

import fr.ocr.business.utile.HashMdp;
import fr.ocr.model.constantes.UserProfile;
import fr.ocr.model.controllers.JpaCtrlGrimpeur;
import fr.ocr.model.entities.DbGrimpeur;

import java.util.List;

/*
***********************************************************************************
 */
public interface CtrlMetierGrimpeur {

    CtrlMetierGrimpeur CTRL_METIER_GRIMPEUR = new CtrlMetierGrimpeur_impl();

    DbGrimpeur ajouterGrimpeur(String nom, String mdp) throws Exception ;

    DbGrimpeur connecterCeGrimpeur(String NomGrimpeur, String mdpGrimpeur) throws Exception;
}

/*
 ***********************************************************************************
 */
class  CtrlMetierGrimpeur_impl implements CtrlMetierGrimpeur{

    private JpaCtrlGrimpeur jpaCtrlGrimpeur;

    CtrlMetierGrimpeur_impl() { this.jpaCtrlGrimpeur = JpaCtrlGrimpeur.JPA_CTRL_GRIMPEUR; }

    /**
     *
     * @param NomGrimpeur String nom utilisateur
     * @param mdpGrimpeur String mdp de utilisateur
     * @return DbGrimpeur , reflet du tupple en base
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public DbGrimpeur ajouterGrimpeur(String NomGrimpeur, String mdpGrimpeur) throws Exception {
        DbGrimpeur dbGrimpeur = new DbGrimpeur();
        dbGrimpeur.setUserName(NomGrimpeur);
        dbGrimpeur.setUserPass((new HashMdp(mdpGrimpeur)).getStrMdp());
        dbGrimpeur.setRoleName(UserProfile.GRIMPEUR);
        return  jpaCtrlGrimpeur.createGrimpeur(dbGrimpeur);
    }

    /**
     *
     * @param NomGrimpeur String nom utilisateur
     * @param mdpGrimpeur String mdp de utilisateur
     * @return DbGrimpeur , reflet du tupple en base
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public DbGrimpeur connecterCeGrimpeur(String NomGrimpeur, String mdpGrimpeur) throws Exception {
        List<DbGrimpeur> dbGrimpeurs = jpaCtrlGrimpeur.findGrimpeurByName(NomGrimpeur);
        DbGrimpeur dbGrimpeur = null;
        if (dbGrimpeurs.size() == 1) {
            if (dbGrimpeurs.get(0).getUserPass().equals((new HashMdp(mdpGrimpeur)).getStrMdp())) {
                dbGrimpeur = dbGrimpeurs.get(0);
            }
        }
        return dbGrimpeur;
    }
}
