package fr.ocr.prj06.filelogs;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Hello world!
 */
public class Utile {
    private static FileWriter fileWriter;
    private String fileLogsName;
    private static Utile utile = null;

    private Utile()  {
        try {
            Properties properties = new Properties();
            InputStream inputStream = Utile.class.getResourceAsStream("/info.properties");
            properties.load(inputStream);
            this.fileLogsName = properties.getProperty("logsfile");
            fileWriter = new FileWriter(this.fileLogsName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getUtile()  {

        if (utile== null) {
            utile = new Utile();
        }
    }

    public static void EcrireLogs(String s)  {
        try {
            if (fileWriter == null) {
                getUtile();
            }
            fileWriter.write(s +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

