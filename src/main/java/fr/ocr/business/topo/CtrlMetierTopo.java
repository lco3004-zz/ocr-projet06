/*
 * **********************************************************
 * Projet 06
 * Contrôleur : méthode en lien avec les Topos
 *
 * Le nom de chaque méthode suffit à comprendre sa fonction
 * ************************************************************
 */

package fr.ocr.business.topo;

import fr.ocr.model.constantes.EtatsResaTopo;
import fr.ocr.model.controllers.JpaCtrlTopo;
import fr.ocr.model.entities.DbTopo;

import java.util.Date;
import java.util.List;

/*
 ***********************************************************************************
 */
public interface CtrlMetierTopo  {

    CtrlMetierTopo CTRL_METIER_TOPO = new CtrlMetierTopo_impl();

    List<DbTopo> listerMesTopos(Integer idGrimpeur) throws Exception;

    List<DbTopo> listerMesDemandeDeResa(Integer idGrimpeur) throws Exception;

    List<DbTopo> listerMesToposReserver(Integer idGrimpeur) throws Exception;

    List<DbTopo> listerMesReservationTopo(Integer idEmprunteur) throws Exception;

    List<DbTopo> listerTousToposDisponibles() throws Exception;

    List<DbTopo> listerMesToposNonPublies(Integer idGrimpeur) throws Exception;

    DbTopo enregistrerCeTopo(Integer idGrimpeur, String lieuTopo, String nomTopo, String resumeTopo) throws Exception;

    void publierCeTopo(int idTopo) throws Exception;

    void demanderResaCeTopo(int idTopo, int idGrimpeurDemandeur) throws Exception;

    void restituerResaCeTopo(int idTopo) throws Exception;

    void accepterResaCeTopo(int idTopo) throws Exception;

}

/*
 ***********************************************************************************
 */
class CtrlMetierTopo_impl implements CtrlMetierTopo{

    private JpaCtrlTopo jpaCtrlTopo;

    CtrlMetierTopo_impl() { this.jpaCtrlTopo = JpaCtrlTopo.JPA_CTRL_TOPO; }

    /**
     *
     * @param idGrimpeur Integer , id du Grimpeur concerné
     * @return List DbTopo , objet renseigné (null possible)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbTopo> listerMesTopos(Integer idGrimpeur)  throws Exception {

        return jpaCtrlTopo.listerToposSelonFlag(idGrimpeur, null, null, null);
    }

    /**
     *
     * @param idGrimpeur Integer , id du Grimpeur concerné
     * @return List DbTopo , objet renseigné (null possible)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbTopo> listerMesDemandeDeResa(Integer idGrimpeur) throws Exception {
        return jpaCtrlTopo.listerToposSelonFlag(idGrimpeur, true, EtatsResaTopo.R_FR, null);
    }

    /**
     *
     * @param idGrimpeur Integer , id du Grimpeur concerné
     * @return List DbTopo , objet renseigné (null possible)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbTopo> listerMesToposReserver(Integer idGrimpeur) throws Exception {
        return jpaCtrlTopo.listerToposSelonFlag(idGrimpeur, true, EtatsResaTopo.A_FR, null);
    }

    /**
     *
     * @param idEmprunteur Integer , id du Grimpeur empruntant le topo
     * @return List DbTopo , objet renseigné (null possible)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbTopo> listerMesReservationTopo(Integer idEmprunteur) throws Exception {
        return jpaCtrlTopo.listerToposSelonFlag(null, true, EtatsResaTopo.A_FR, idEmprunteur);
    }

    /**
     *
     * @return List DbTopo , objet renseigné (null possible)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbTopo> listerTousToposDisponibles() throws Exception {
        return jpaCtrlTopo.listerToposSelonFlag(null, true, EtatsResaTopo.W_FR, null);
    }

    /**
     *
     * @param idGrimpeur Integer , id du Grimpeur concerné
     * @return List DbTopo , objet renseigné (null possible)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public List<DbTopo> listerMesToposNonPublies(Integer idGrimpeur) throws Exception {
        return jpaCtrlTopo.listerToposSelonFlag(idGrimpeur, false, null, null);
    }

    /**
     *
     * @param idGrimpeur Integer  ,id du Grimpeur concerné
     * @param lieuTopo String  , valeur de attribut Lieu
     * @param nomTopo String , valeur de attribut Nom
     * @param resumeTopo String , valeur de attribut  Resume
     * @return DbTopo , objet renseigné (null possible)
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public DbTopo enregistrerCeTopo(Integer idGrimpeur,  String lieuTopo, String nomTopo, String resumeTopo) throws Exception {
        DbTopo dbTopo = new DbTopo();

        dbTopo.setDateDeParution(new Date());
        dbTopo.setEtatReservation(EtatsResaTopo.W_FR);
        dbTopo.setEstPublie(false);

        dbTopo.setLieu(lieuTopo);
        dbTopo.setNom(nomTopo);
        dbTopo.setResume(resumeTopo);

        return jpaCtrlTopo.createTopo(idGrimpeur,dbTopo);
   }

    /**
     *
     * @param idTopo int , id du Topo concerné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void publierCeTopo(int idTopo) throws Exception{
        DbTopo dbTopo = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEstPublie(true);
        jpaCtrlTopo.updateTopo(dbTopo);
    }

    /**
     *
     * @param idTopo int , id du Topo concerné
     * @param idGrimpeurDemandeur  int , id du Grimpeur concerné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void demanderResaCeTopo(int idTopo, int idGrimpeurDemandeur) throws Exception {
        DbTopo dbTopo  = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEtatReservation(EtatsResaTopo.R_FR);
        dbTopo.setIdEmprunteur(idGrimpeurDemandeur);
        jpaCtrlTopo.updateTopo(dbTopo);
    }

    /**
     *
     * @param idTopo int , id du Topo concerné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void restituerResaCeTopo(int idTopo) throws Exception {
        DbTopo dbTopo = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEtatReservation(EtatsResaTopo.W_FR);
        dbTopo.setIdEmprunteur(null);
        jpaCtrlTopo.updateTopo(dbTopo);
    }

    /**
     *
     * @param idTopo int , id du Topo concerné
     * @throws Exception , levée si erreur niveau modèle
     */
    @Override
    public void accepterResaCeTopo(int idTopo) throws Exception {
        DbTopo dbTopo = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEtatReservation(EtatsResaTopo.A_FR);
        jpaCtrlTopo.updateTopo(dbTopo);
    }

}
