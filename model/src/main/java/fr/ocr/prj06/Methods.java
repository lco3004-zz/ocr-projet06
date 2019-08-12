package fr.ocr.prj06;

import fr.ocr.prj06.entity.common.JpaHibernateUtility;
import fr.ocr.prj06.entity.stub.dbCommentaireEntity;
import fr.ocr.prj06.entity.stub.dbSpotEntity;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import static fr.ocr.prj06.entity.common.JpaHibernateUtility.getInstance;
import static fr.ocr.prj06.utility.constante.Messages.InfosMessages.LANCEMENT_APPLICATION;
import static fr.ocr.prj06.utility.logs.LogsProjet.geLogsInstance;

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

                try (JpaHibernateUtility jpa = getInstance(properties.getProperty("persistenceUnitName"))) {
                    jpa.getEt().begin();
                    dbSpotEntity spot = jpa.getEm().find(dbSpotEntity.class, 1 );

                     Collection<dbCommentaireEntity> x = spot.getCommentairesByIdspot();
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


