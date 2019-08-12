package fr.ocr.prj06.business.businessCtrl;

import fr.ocr.prj06.entity.controller.UserJpaController;
import fr.ocr.prj06.entity.stub.dbUserEntity;

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
            System.out.println(s);
            stringArrayList.add(s);
        }
        return stringArrayList;
    }
}
