package fr.ocr.prj06.business.businessCtrl;

import fr.ocr.prj06.entity.controller.UserJpaCtrl;
import fr.ocr.prj06.entity.stub.dbUserEntity;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class UserMgmt {
    LogsProjet logs;
    public UserMgmt() {
        logs = getLogsInstance();
    }

    public ArrayList<String> getListUsers() throws Exception {
        UserJpaCtrl userJpaController = new UserJpaCtrl();
        List<dbUserEntity> dbUserEntities = userJpaController.findDbEntities();
        int i = 0;
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (dbUserEntity user : dbUserEntities) {
            String s = "rang : " + user.getIduser() +
                    " , Nom : " + user.getNom() +
                    " , eMail :" + user.getEmail();
            stringArrayList.add(s);
            logs.maTrace(Level.DEBUG, "--> Liste User : " + s);
        }
        return stringArrayList;
    }
}
