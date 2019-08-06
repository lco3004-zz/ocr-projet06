package fr.ocr.prj06.logs;

import org.apache.logging.log4j.*;

/**
 *
 */
public class LogsProjet implements AutoCloseable{

    // The name of this Logger will be "org.apache.logging.LogsUtil"
    private   Logger logger;
    private   Class aClass ;
    private static LogsProjet ourInstance = null;
    private Marker marker;

    private LogsProjet(Class x) {
        marker = MarkerManager.getMarker("CLASS");
        logger = LogManager.getLogger(this);
        aClass=x;
    }

    public void maTrace(Level level, String msg) {
        logger.log(level,marker,msg+" <-> "+aClass.getSimpleName());
    }

    public static LogsProjet geLogsInstance(Class x) {
        if (ourInstance == null)
            ourInstance = new LogsProjet(x);
        return ourInstance;
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