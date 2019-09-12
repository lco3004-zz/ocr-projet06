package fr.ocr.model.controllers;

import fr.ocr.model.constantes.EtatsResaTopo;
import fr.ocr.model.entities.DbGrimpeur;
import fr.ocr.model.entities.DbTopo;
import fr.ocr.model.entities.DbTopo_;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/*

 */
public interface JpaCtrlTopo {
     JpaCtrlTopo JPA_CTRL_TOPO = new JpaCtrlTopo_impl();
     List<DbTopo> findListeTopos(Integer idUser) throws  Exception;
     DbTopo createTopo(Integer idUser, DbTopo dbTopo) throws Exception;
     DbTopo readTopo(Integer idTopo) throws Exception;
     DbTopo updateTopo(DbTopo dbTopo) throws Exception;

    List<DbTopo> listerToposSelonFlag(Integer idGrimpeur, Boolean estPublie, EtatsResaTopo etatsResaTopo) throws Exception;
}
/*

 */
class JpaCtrlTopo_impl implements JpaCtrlTopo {

    @Override
    public List<DbTopo> findListeTopos(Integer idUser) throws Exception {
        return listerToposSelonFlag(idUser, null, null);
    }

    @Override
    public DbTopo createTopo(Integer idUser, DbTopo dbTopo) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {

            try {
                jpa.getEm().getTransaction().begin();

                dbTopo.setGrimpeurByGrimpeurIdgrimpeur(jpa.getEm().find(DbGrimpeur.class, idUser));

                jpa.getEm().persist(dbTopo);

                jpa.getEm().getTransaction().commit();

                return readTopo(dbTopo.getIdtopo());

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }

    @Override
    public DbTopo readTopo(Integer idTopo) throws Exception {
        DbTopo dbTopo ;
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            dbTopo = jpa.getEm().find(DbTopo.class,idTopo);
        }
        return dbTopo;
    }

    @Override
    public DbTopo updateTopo(DbTopo x) throws Exception {

        try (JpaEntityManager jpa = new JpaEntityManager()) {
            try {
                jpa.getEm().getTransaction().begin();
                DbTopo dbTopo = jpa.getEm().find(DbTopo.class, (x.getIdtopo()));

                dbTopo.setDateDeParution(x.getDateDeParution());
                dbTopo.setEstPublie(x.getEstPublie());
                dbTopo.setEtatReservation(x.getEtatReservation());
                dbTopo.setLieu(x.getLieu());
                dbTopo.setNom(x.getNom());
                dbTopo.setResume(x.getResume());

                jpa.getEm().getTransaction().commit();

            } catch (Exception ex) {
                jpa.getEm().getTransaction().rollback();
                throw new Exception(ex);
            }
        }
        return readTopo(x.getIdtopo());
    }

    @Override
    public List<DbTopo> listerToposSelonFlag(Integer idGrimpeur, Boolean estPublie, EtatsResaTopo etatsResaTopo) throws Exception {
        try (JpaEntityManager jpa = new JpaEntityManager()) {
            jpa.getEm().getTransaction().begin();

            CriteriaBuilder criteriaBuilder = jpa.getEm().getCriteriaBuilder();

            CriteriaQuery<DbTopo> criteriaQuery = criteriaBuilder.createQuery(DbTopo.class);

            Root<DbTopo> root = criteriaQuery.from(DbTopo.class);
            criteriaQuery.select(root);

            ArrayList<Predicate> predicateArrayList = new ArrayList<>();

            if (idGrimpeur != null) {
                DbGrimpeur userByUserIduser = jpa.getEm().find(DbGrimpeur.class, idGrimpeur);
                predicateArrayList.add(criteriaBuilder.equal(root.get(DbTopo_.GRIMPEUR_BY_GRIMPEUR_IDGRIMPEUR), userByUserIduser));
            }
            if (estPublie != null) {
                predicateArrayList.add(criteriaBuilder.equal(root.get(DbTopo_.EST_PUBLIE), estPublie));
            }
            if (etatsResaTopo != null) {
                predicateArrayList.add(criteriaBuilder.equal(root.get(DbTopo_.ETAT_RESERVATION), etatsResaTopo));
            }
            Predicate[] predicates = new Predicate[predicateArrayList.size()];
            int n = 0;
            for (Predicate predicate : predicateArrayList) {
                predicates[n++] = predicate;
            }

            criteriaQuery.where(predicates);
            Query query = jpa.getEm().createQuery(criteriaQuery);

            List<DbTopo> dbTopos = (List<DbTopo>) query.getResultList();

            jpa.getEm().getTransaction().commit();
            return dbTopos;

        } catch (Exception hex1) {
            throw new Exception(hex1);
        }
    }
}
