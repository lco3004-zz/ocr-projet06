package fr.ocr.prj06.filelogs;


import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import static fr.ocr.prj06.entity.common.Messages.ConstantesPgm.FILELOGSNAME;

/**
 * Hello world!
 */
public class Utile {
    private FileWriter fileWriter;
    private String fileLogsName;
    private static Utile utile = null;

    private Utile() throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = Utile.class.getResourceAsStream("/info.properties");
        properties.load(inputStream);
        this.fileLogsName = properties.getProperty(FILELOGSNAME.getValeurConstante());

        fileWriter = new FileWriter("");
    }

    public Utile getUtile() throws Exception {
        if (utile.equals(null)) {
            utile = new Utile();
        }
        return utile;
    }

    public  void EcrireLogs(String s) throws Exception {
            if (fileWriter.equals(null)) {
                this.getUtile();
            }
            this.fileWriter.write(s +"\n");
    }
}

