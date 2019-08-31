package fr.ocr.prj06.business.topo;

import fr.ocr.prj06.constantes.EtatsResaTopo;
import fr.ocr.prj06.controllers.JpaCtrlTopo;
import fr.ocr.prj06.entities.DbTopo;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public interface CtrlMetierTopo  {

    CtrlMetierTopo CTRL_METIER_TOPO = new CtrlMetierTopo_impl();

    List<DbTopo> listerMesTopos(Integer idGrimpeur) throws Exception;

    List<DbTopo> listerTousTopos() throws Exception;

    DbTopo enregistrerMonTopo(Integer idGrimpeur, String lieuTopo, String nomTopo, String resumeTopo) throws Exception;

    DbTopo publierMonTopo(int idTopo) throws Exception;

    DbTopo demanderResaTopo(int idGrimpeur, int idTopo) throws Exception;

    DbTopo accepterResaTopo(int idTopo) throws Exception;

    DbTopo consulterMonTopo(int idTopo) throws Exception;
}


class CtrlMetierTopo_impl implements CtrlMetierTopo{

    private JpaCtrlTopo jpaCtrlTopo;

    CtrlMetierTopo_impl() {

        this.jpaCtrlTopo = JpaCtrlTopo.JPA_CTRL_TOPO;
    }

    @Override
    public List<DbTopo> listerMesTopos(Integer idGrimpeur)  throws Exception {
        List<DbTopo> valRet=null;
        valRet = jpaCtrlTopo.findListeTopos(idGrimpeur);
        return valRet;
    }

    @Override
    public List<DbTopo> listerTousTopos() throws Exception {
        return listerMesTopos(null);
    }

    @Override
    public DbTopo enregistrerMonTopo(Integer idGrimpeur,  String lieuTopo, String nomTopo, String resumeTopo) throws Exception {
        DbTopo dbTopo = new DbTopo();

        EtatsResaTopo etatsResaTopo = EtatsResaTopo.W_FR;
        Boolean estPublieTopo =false;

        dbTopo.setDateDeParution(new Date());
        dbTopo.setEtatReservation(etatsResaTopo);
        dbTopo.setLieu(lieuTopo);
        dbTopo.setEstPublie(estPublieTopo);
        dbTopo.setNom(nomTopo);
        dbTopo.setResume(resumeTopo);
        dbTopo = jpaCtrlTopo.createTopo(idGrimpeur,dbTopo);

        return dbTopo;
   }

    @Override
    public DbTopo publierMonTopo(int idTopo) throws Exception{
        DbTopo dbTopo = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEstPublie(true);
        dbTopo = jpaCtrlTopo.updateTopo(dbTopo);
        return dbTopo;
    }

    @Override
    public DbTopo demanderResaTopo(int idGrimpeur, int idTopo) throws Exception {
        DbTopo dbTopo  = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEtatReservation(EtatsResaTopo.R_FR);
        dbTopo = jpaCtrlTopo.updateTopo(dbTopo);
        return dbTopo;
    }

    @Override
    public DbTopo accepterResaTopo(int idTopo) throws Exception {
        DbTopo dbTopo = jpaCtrlTopo.readTopo(idTopo);
        dbTopo.setEtatReservation(EtatsResaTopo.A_FR);
        dbTopo = jpaCtrlTopo.updateTopo(dbTopo);
        return dbTopo;
    }

    @Override
    public DbTopo consulterMonTopo(int idTopo) throws  Exception {
        return jpaCtrlTopo.readTopo(idTopo);
    }
}
