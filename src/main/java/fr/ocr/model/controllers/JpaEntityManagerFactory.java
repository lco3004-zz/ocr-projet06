package fr.ocr.model.controllers;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.utility.constantes.Messages.ConstantesPgm.UNITE_DE_PERSISTANCE;



public class JpaEntityManagerFactory  {

    private EntityManagerFactory emf = null;

    private static JpaEntityManagerFactory jpaEmf = null;

    private  String persistenceUnitName;

    private JpaEntityManagerFactory()  {

        Properties properties = new Properties();
        try {
            InputStream inputStream = JpaEntityManagerFactory.class.getResourceAsStream("/info.properties");
            properties.load(inputStream);
            this.persistenceUnitName = properties.getProperty(UNITE_DE_PERSISTANCE.getValeurConstante());
            //this.persistenceUnitName = properties.getProperty( "nomDeLaPersistance" );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static JpaEntityManagerFactory getJpaEntityManagerFactory()  {
        if (jpaEmf == null)
            jpaEmf = new JpaEntityManagerFactory();
        return jpaEmf;
    }


    public synchronized EntityManagerFactory getEmf()   {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        }
        return emf;
    }

    public synchronized void closeEmf()  {
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }

    public void openDao()  {
        getEmf();
    }


    public void closeDao()  {
        closeEmf();
    }
}
