package fr.ocr.prj06.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.stub.dbSpotEntity;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class SpotJpaCtrl extends JpaCtrl {

    public SpotJpaCtrl() throws Exception {
        super(dbSpotEntity.class);
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }
}
