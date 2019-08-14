package fr.ocr.prj06.entity.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.stub.dbVoieEntity;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class VoieJpaCtrl extends JpaCtrl {

    public VoieJpaCtrl() throws Exception {
        super(dbVoieEntity.class);
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }
}
