package fr.ocr.prj06.entity.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.stub.dbUserEntity;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class UserJpaCtrl extends JpaCtrl {

    public UserJpaCtrl() throws Exception {
        super(dbUserEntity.class);
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }
}
