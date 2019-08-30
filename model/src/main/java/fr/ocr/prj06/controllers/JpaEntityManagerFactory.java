package fr.ocr.prj06.controllers;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Properties;



/**
 * @author Laurent
 * <p>
 * Singleton pour gestion centrale des 3 classes : Transaction, Manager, Factory de JPA (implémentation Hibernate)
 */
public class JpaEntityManagerFactory  {

    private EntityManagerFactory emf = null;

    private static JpaEntityManagerFactory jpaEmf = null;

    private  String persistenceUnitName;


    /**
     * Constructeur qui attend en parametre, le nom l'unité de persistence
     * qui est nommée dans persistence.xml
     */
    private JpaEntityManagerFactory()  {

        Properties properties = new Properties();
        try {
            InputStream inputStream = JpaEntityManagerFactory.class.getResourceAsStream("/info.properties");
            properties.load(inputStream);
            this.persistenceUnitName = properties.getProperty( "nomDeLaPersistance" );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * renvoie instance du singleton - permet d'utiliser l'autocloseable try() {} ce qui ferme
     * l'entitymanager (mais pas le factory qui doit être fermé avec un closeEmFactory
     *
     * @return jpaEMFUtility  - le signleton
     */
    public static JpaEntityManagerFactory getJpaEntityManagerFactory()  {
        if (jpaEmf == null)
            jpaEmf = new JpaEntityManagerFactory();
        return jpaEmf;
    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     * @return emf - objet EntityManagerFactory
     * @throws Exception
     */
    public synchronized EntityManagerFactory getEmf()  {
        try {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return emf;
    }
    /**
     * fermeture EntityMangerFactory
     *
     */
    public synchronized void closeEmf()  {
        try {
            if (emf != null) {
                emf.close();
                emf = null;
            }
        } catch (Throwable ex) {
           ex.printStackTrace();
        }
    }

    public void openDao()  {
        getEmf();
    }


    public void closeDao()  {
        closeEmf();
    }
}
