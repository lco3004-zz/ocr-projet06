package fr.ocr.prj06.utility.logs;

import org.apache.logging.log4j.*;

/**
 *
 */
public class LogsProjet implements AutoCloseable{

    // The name of this Logger will be "org.apache.logging.LogsUtil"
    private   Logger logger;
    private static LogsProjet ourInstance = null;
    private Marker marker;

    private LogsProjet() {
        marker = MarkerManager.getMarker("CLASS");
        logger = LogManager.getLogger(this);
    }

    public static LogsProjet getLogsInstance() {
        if (ourInstance == null)
            ourInstance = new LogsProjet();
        return ourInstance;
    }

    public void maTrace(Level level, String msg) {
        logger.log(level, msg);
    }

    @Override
    public void close() {
        maTrace(Level.DEBUG,"Close : Shutdown LogManager ! Bye !");
        LogManager.shutdown();
    }
}


/*
 * ***************************************************************************************************************
 *  the end
 * ***************************************************************************************************************
 */