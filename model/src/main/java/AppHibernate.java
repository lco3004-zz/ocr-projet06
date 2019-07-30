
import fr.ocr.prj06.hibernate.logs.LogsProjet;
import org.apache.logging.log4j.*;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;

import javax.persistence.metamodel.EntityType;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static fr.ocr.prj06.hibernate.logs.LogsProjet.geLogsInstance;
import static fr.ocr.prj06.hibernate.tools.HibernateUtil.getSession;

public class AppHibernate {

    public static void main(final String[] args) throws Exception {
        //
        LogsProjet.geLogsInstance(AppHibernate.class).maTrace(Level.INFO,"Hello from ");
        //

        Properties properties = new Properties();

        final Session session = getSession();

        try (InputStream inputStream = AppHibernate.class.getResourceAsStream("/info.properties")) {
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