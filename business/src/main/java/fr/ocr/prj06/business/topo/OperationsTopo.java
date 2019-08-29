package fr.ocr.prj06.business.topo;

import fr.ocr.prj06.constantes.EtatsResaTopo;
import fr.ocr.prj06.entities.DbTopo;
import fr.ocr.prj06.interfaces.JpaInterface_EntityManagerFactory;

import java.util.Date;
import java.util.List;

import static fr.ocr.prj06.controllers.JpaEntityManagerFactory.getJpaEMFUtility;

public interface OperationsTopo  {

    OperationsTopo operationsTopo = new CtrMetierTopo();

    JpaInterface_EntityManagerFactory jpaCtrl = getJpaEMFUtility();

    List<DbTopo> listerMesTopos(Integer idGrimpeur);

    List<DbTopo> listerTousTopos();

    DbTopo enregistrerMonTopo(Integer idGrimpeur, Date dateParutionTopo, EtatsResaTopo etatsResaTopo, String lieuTopo, String nomTopo, String resumeTopo, Boolean estPublieTopo);

    DbTopo publierMonTopo(int idTopo);

    DbTopo demanderResaTopo(int idGrimpeur, int idTopo);

    DbTopo accepterResaTopo(int idTopo);

    DbTopo consulterMonTopo(int idTopo);
}
