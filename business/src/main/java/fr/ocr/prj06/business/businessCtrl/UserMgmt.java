package fr.ocr.prj06.business.businessCtrl;

import fr.ocr.prj06.entity.controller.UserJpaController;
import fr.ocr.prj06.entity.stub.dbUserEntity;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

public class UserMgmt {
    public UserMgmt() {

    }

    public ArrayList<String> getListUsers() throws Exception {
        UserJpaController userJpaController = new UserJpaController();
        List<dbUserEntity> dbUserEntities = userJpaController.findDbUserEntities();
        int i = 0;
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (dbUserEntity user : dbUserEntities) {
            String s = "rang : " + user.getIduser() + " ,  Nom : " + user.getNom() + " eMail :" + user.getEmail();
            stringArrayList.add(s);
            LogsProjet.geLogsInstance(UserMgmt.class).maTrace(Level.DEBUG, "--> Liste User : " + s);
        }
        return stringArrayList;
    }
}
