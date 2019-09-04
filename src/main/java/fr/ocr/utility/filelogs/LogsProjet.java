package fr.ocr.utility.filelogs;

import org.apache.logging.log4j.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.utility.constantes.Messages.ConstantesPgm.NOM_APPLICATION;


/**
 *
 */
public class LogsProjet implements AutoCloseable{

    private static final Integer MARKER_PROJET = 1;
    private static final Integer MARKER_JPA = 2;
    private static LogsProjet ourInstance = null;


    private final   Logger logger;

    private final Marker markerProject;

    private LogsProjet(Object object) {
        markerProject = MarkerManager.getMarker(loadProperties(MARKER_PROJET));
        logger = LogManager.getLogger(this);
    }

    public static LogsProjet getLogsInstance(Object object) {
        if (ourInstance == null)
            ourInstance = new LogsProjet(object);
        return ourInstance;
    }

    private String loadProperties(Integer choixProperty) {
        Properties properties = new Properties();
        InputStream inputStream = LogsProjet.class.getResourceAsStream("/info.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (choixProperty == MARKER_JPA) {
            return  "JPA";
        }

        return properties.getProperty(NOM_APPLICATION.getValeurConstante());
    }

    public void info_projet(String msg) {
        logger.log(Level.INFO,msg);
    }
    public void debug_projet(String msg) {
        logger.log(Level.DEBUG,msg);
    }
    public void warn_projet(String msg) {
        logger.log(Level.WARN,msg);
    }
    public void error_projet(String msg) {
        logger.log(Level.ERROR,msg);
    }

    @Override
    public void close() {
        info_projet("Close : Shutdown LogManager ! Bye !");
        LogManager.shutdown();
    }
}
