package fr.ocr.business.topo;

import fr.ocr.model.constantes.EtatsResaTopo;
import fr.ocr.model.controllers.JpaCtrlTopo;
import fr.ocr.model.entities.DbTopo;

import java.util.Date;
import java.util.List;


public interface CtrlMetierTopo  {

    CtrlMetierTopo CTRL_METIER_TOPO = new CtrlMetierTopo_impl();

    List<DbTopo> listerMesTopos(Integer idGrimpeur) throws Exception;

    List<DbTopo> listerTousTopos() throws Exception;

    DbTopo enregistrerCeTopo(Integer idGrimpeur, String lieuTopo, String nomTopo, String resumeTopo) throws Exception;

    DbTopo publierCeTopo(int idTopo) throws Exception;

    DbTopo demanderResaCeTopo(int idGrimpeur, int idTopo) throws Exception;

    DbTopo accepterResaCeTopo(int idTopo) throws Exception;

    DbTopo consulterCeTopo(int idTopo) throws Exception;
}


class CtrlMetierTopo_impl implements CtrlMetierTopo{

    private JpaCtrlTopo jpaCtrlTopo;

    CtrlMetierTopo_impl() {

        this.jpaCtrlTopo = JpaCtrlTopo.JPA_CTRL_TOPO;
    }

    @Override
    public List<DbTopo> listerMesTopos(Integer idGrimpeur)  throws Exception {
        return jpaCtrlTopo.findListeTopos(idGrimpeur);
    }

    @Override
    public List<DbTopo> listerTousTopos() throws Exception {
        return listerMesTopos(null);
    }

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

    @Override
    public DbTopo publierCeTopo(int idTopo) throws Exception{
        DbTopo dbTopo = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEstPublie(true);
        return jpaCtrlTopo.updateTopo(dbTopo);
    }

    @Override
    public DbTopo demanderResaCeTopo(int idGrimpeur, int idTopo) throws Exception {
        DbTopo dbTopo  = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEtatReservation(EtatsResaTopo.R_FR);
        return jpaCtrlTopo.updateTopo(dbTopo);
    }

    @Override
    public DbTopo accepterResaCeTopo(int idTopo) throws Exception {
        DbTopo dbTopo = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEtatReservation(EtatsResaTopo.A_FR);
        return jpaCtrlTopo.updateTopo(dbTopo);
    }

    @Override
    public DbTopo consulterCeTopo(int idTopo) throws  Exception {
        return jpaCtrlTopo.readTopo(idTopo);
    }
}
