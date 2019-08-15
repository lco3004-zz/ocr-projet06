package fr.ocr.prj06.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.stub.dbTopoEntity;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class TopoJpaCtrl extends JpaCtrl {

    public TopoJpaCtrl() throws Exception {
        super(dbTopoEntity.class);
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }
}
