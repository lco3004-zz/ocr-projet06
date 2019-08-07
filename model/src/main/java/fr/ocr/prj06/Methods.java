package fr.ocr.prj06;

import fr.ocr.prj06.commons.JpaHibernateCommons;
import fr.ocr.prj06.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.prj06.commons.JpaHibernateCommons.getEt;
import static fr.ocr.prj06.commons.JpaHibernateCommons.getInstance;
import static fr.ocr.prj06.logs.LogsProjet.geLogsInstance;
import static fr.ocr.prj06.messages.Messages.InfosMessages.LANCEMENT_APPLICATION;

/**
 * Hello jPA World!
 */
public class Methods {
    public static void main(final String[] args) throws Exception {
        //
        try (LogsProjet logs = geLogsInstance(Methods.class)) {
            Properties properties = new Properties();
            try (InputStream inputStream = Methods.class.getResourceAsStream("/info.properties")) {
                properties.load(inputStream);

                logs.maTrace(org.apache.logging.log4j.Level.INFO, String.format("%s Version= %s", LANCEMENT_APPLICATION.getMessageInfos(), properties.getProperty("version")));

                try (JpaHibernateCommons jpaHibernateCommons = getInstance()) {
                    getEt().begin();

                    getEt().commit();
                }catch (Exception ecp1) {
                    logs.maTrace(Level.ERROR,"Rollback "+ecp1.getLocalizedMessage() );

                }
            }
        } catch (Exception ecp) {
            System.out.println("Exception " + ecp.getLocalizedMessage());
            throw new Exception(ecp);
        }
    }
}


