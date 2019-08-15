package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.prj06.utility.constante.Messages.ConstantesPgm.UNITE_DE_PERSISTANCE;
import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

/**
 * @author Laurent
 * <p>
 * Singleton pour gestion centrale des 3 classes : Transaction, Manager, Factory de JPA (implémentation Hibernate)
 */
public class JpaEmfUtility implements JpaEmfInterface {

    private  EntityManagerFactory emf = null;

    private static JpaEmfUtility jpaEMFUtility = null;

    private  String persistenceUnitName;

    private LogsProjet logs;

    /**
     * Constructeur qui attend en parametre, le nom l'unité de persistence
     * qui est nommée dans persistence.xml
     */
    private JpaEmfUtility() throws IOException {
        logs = getLogsInstance();
        Properties properties = new Properties();
        InputStream inputStream = JpaCtrl.class.getResourceAsStream("/info.properties");
        properties.load(inputStream);
        this.persistenceUnitName = properties.getProperty(UNITE_DE_PERSISTANCE.getValeurConstante());
    }

    /**
     * renvoie instance du singleton - permet d'utiliser l'autocloseable try() {} ce qui ferme
     * l'entitymanager (mais pas le factory qui doit être fermé avec un closeEmFactory
     *
     * @return jpaEMFUtility  - le signleton
     */
    public static JpaEmfUtility getInstanceEMF() throws IOException {
        if (jpaEMFUtility == null)
            jpaEMFUtility = new JpaEmfUtility();
        return jpaEMFUtility;
    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     * @return emf - objet EntityManagerFactory
     * @throws Exception
     */
    public synchronized EntityManagerFactory getEmf() throws Exception {
        try {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory(persistenceUnitName);
                logs.maTrace(Level.DEBUG, "Creation  : EntityManagerFactory ");
            }
        } catch (Throwable ex) {
            logs.maTrace(Level.FATAL, "Impossible creé EntityManagerFactory :" + ex.getLocalizedMessage());
            throw new Exception(ex);
        }
        return emf;
    }
    /**
     * fermeture EntityMangerFactory
     *
     */
    public synchronized void closeEmf() throws Exception {
        try {
            if (emf != null) {
                emf.close();
                emf = null;
            }
            logs.maTrace(Level.DEBUG, "Fermeture  : EntityManagerFactory ");
        } catch (Throwable ex) {
            logs.maTrace(Level.FATAL, "Impossible de fermer  E.M.Factory :" + ex.getLocalizedMessage());
            throw new Exception(ex);
        }
    }

    @Override
    public void openDao() throws Exception {
        getEmf();
    }

    @Override
    public void closeDao() throws Exception {
        closeEmf();
    }
}
