package fr.ocr.prj06.filelogs;
import org.slf4j.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class LogsProjet implements AutoCloseable{

    private static LogsProjet ourInstance = null;
    // The name of this Logger will be "org.apache.logging.LogsUtil"
    private final   Logger logger;
    private final Marker markerProject;

    private LogsProjet() {
        logger = LoggerFactory.getLogger(LogsProjet.class);

        markerProject = MarkerFactory.getMarker(loadProperties());
    }

    public static LogsProjet getLogsInstance() {
        if (ourInstance == null)
            ourInstance = new LogsProjet();
        return ourInstance;
    }

    private String loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = LogsProjet.class.getResourceAsStream("/info.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(type.getValeurConstante());
    }

    @Override
    public void close() {
        ILoggerFactory x =   LoggerFactory.getILoggerFactory();
        x.
    }
}


/*
 * ***************************************************************************************************************
 *  the end
 * ***************************************************************************************************************
 */