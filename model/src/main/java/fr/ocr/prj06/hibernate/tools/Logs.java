package fr.ocr.prj06.hibernate.tools;


import org.apache.log4j.LogManager;
import org.slf4j.Logger;

/**
 * <p>
 * @author laurent cordier
 * Singleton : pour tracer , il suffit d'écrire logger.{info|debug|erro} ( {chaine de caractère non null} ) ;
 * l'istance doit être créer dans la méthode principale
 * </p>
 */
public class Logs {
    public static Logger logger;
    private static Logs ourInstance = null;

    private Logs(String nomAppelant) {
        logger = (Logger) LogManager.getRootLogger();
    }

    public static Logs getInstance(String nomAppelant) {
        if (ourInstance == null)
            ourInstance = new Logs(nomAppelant);
        return ourInstance;
    }

}
/*
 * ***************************************************************************************************************
 *  the end
 * ***************************************************************************************************************
 */