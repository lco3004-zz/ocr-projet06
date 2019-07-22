package fr.ocr.prj06;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        try (InputStream inputStream = App.class.getResourceAsStream("/info.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            System.out.println(properties.getProperty("version"));
            System.out.println(properties.getProperty("type"));
        }
    }
}
