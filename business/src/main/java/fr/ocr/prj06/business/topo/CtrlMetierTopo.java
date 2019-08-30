package fr.ocr.prj06.business.topo;

import fr.ocr.prj06.constantes.EtatsResaTopo;
import fr.ocr.prj06.controllers.JpaCtrlTopo;
import fr.ocr.prj06.entities.DbTopo;

import java.util.Date;
import java.util.List;

public interface CtrlMetierTopo  {

    CtrlMetierTopo CTRL_METIER_TOPO = new CtrlMetierTopo_impl();

    List<DbTopo> listerMesTopos(Integer idGrimpeur);

    List<DbTopo> listerTousTopos();

    DbTopo enregistrerMonTopo(Integer idGrimpeur, Date dateParutionTopo, EtatsResaTopo etatsResaTopo, String lieuTopo, String nomTopo, String resumeTopo, Boolean estPublieTopo);

    DbTopo publierMonTopo(int idTopo);

    DbTopo demanderResaTopo(int idGrimpeur, int idTopo);

    DbTopo accepterResaTopo(int idTopo);

    DbTopo consulterMonTopo(int idTopo);
}


class CtrlMetierTopo_impl implements CtrlMetierTopo{

    private JpaCtrlTopo jpaCtrlTopo;

    CtrlMetierTopo_impl() {

        this.jpaCtrlTopo = JpaCtrlTopo.JPA_CTRL_TOPO;
    }

    @Override
    public List<DbTopo> listerMesTopos(Integer idGrimpeur) {
        List<DbTopo> valRet=null;
        try {
            valRet = jpaCtrlTopo.findListeTopos(idGrimpeur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valRet;
    }

    @Override
    public List<DbTopo> listerTousTopos() {

        return listerMesTopos(null);
    }

    @Override
    public DbTopo enregistrerMonTopo(Integer idGrimpeur, Date dateParutionTopo, EtatsResaTopo etatsResaTopo, String lieuTopo, String nomTopo, String resumeTopo, Boolean estPublieTopo) {
        DbTopo dbTopo = new DbTopo();

        dbTopo.setDateDeParution(dateParutionTopo);
        dbTopo.setEtatReservation(etatsResaTopo);
        dbTopo.setLieu(lieuTopo);
        dbTopo.setEstPublie(estPublieTopo);
        dbTopo.setNom(nomTopo);
        dbTopo.setResume(resumeTopo);
        try {
            dbTopo = jpaCtrlTopo.createTopo(idGrimpeur,dbTopo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbTopo;
   }

    @Override
    public DbTopo publierMonTopo(int idTopo) {
        DbTopo dbTopo = null;

        try {
            dbTopo = jpaCtrlTopo.readTopo(idTopo);
            dbTopo.setEstPublie(true);
            dbTopo = jpaCtrlTopo.updateTopo(dbTopo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbTopo;
    }

    @Override
    public DbTopo demanderResaTopo(int idGrimpeur, int idTopo) {
        DbTopo dbTopo = null;
        try {
            dbTopo = jpaCtrlTopo.readTopo(idTopo);
            dbTopo.setEtatReservation(EtatsResaTopo.R_FR);
            dbTopo = jpaCtrlTopo.updateTopo(dbTopo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbTopo;
    }

    @Override
    public DbTopo accepterResaTopo(int idTopo) {
        DbTopo dbTopo = null;
        try {
            dbTopo = jpaCtrlTopo.readTopo(idTopo);
            dbTopo.setEtatReservation(EtatsResaTopo.A_FR);
            dbTopo = jpaCtrlTopo.updateTopo(dbTopo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbTopo;
    }

    @Override
    public DbTopo consulterMonTopo(int idTopo) {
        DbTopo dbTopo = null;
        try {
            dbTopo=jpaCtrlTopo.readTopo(idTopo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbTopo;
    }
}
