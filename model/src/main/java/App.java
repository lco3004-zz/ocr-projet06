import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.prj06.hibernate.tools.HibernateUtil.getSession;
import static fr.ocr.prj06.hibernate.tools.Logs.logger;
import static fr.ocr.prj06.hibernate.tools.Logs.getInstance;
import static fr.ocr.prj06.hibernate.tools.Messages.InfosMessages.LANCEMENT_APPLICATION;

public class App {

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        Properties properties = new Properties();

        //creation objet logger (singleton)
        //getInstance(App.class.getSimpleName());

        try (InputStream inputStream = App.class.getResourceAsStream("/info.properties")) {
            properties.load(inputStream);
        }

        //logger.info(String.format("%s Version= %s", LANCEMENT_APPLICATION.getMessageInfos(), properties.getProperty("version")));

        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
}