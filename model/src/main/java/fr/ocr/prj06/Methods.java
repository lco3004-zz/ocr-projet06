package fr.ocr.prj06;

import fr.ocr.prj06.commons.JpaHibernateCommons;
import fr.ocr.prj06.logs.LogsProjet;
import fr.ocr.prj06.stubs.dbCommentaireEntity;
import fr.ocr.prj06.stubs.dbSpotEntity;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManager;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static fr.ocr.prj06.commons.JpaHibernateCommons.*;
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

                try (JpaHibernateCommons jpa = getInstance()) {
                    jpa.getEt().begin();
                    dbSpotEntity spot = (dbSpotEntity)jpa.getEm().find(dbSpotEntity.class, (Integer)1 );

                     Set<dbCommentaireEntity> x = spot.getCommentairesByIdspot();
                    jpa.getEt().commit();
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


