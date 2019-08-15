package fr.ocr.prj06.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.stub.dbLongueurEntity;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class LongeurJpaCtrl extends JpaCtrl {

    public LongeurJpaCtrl() throws Exception {
        super(dbLongueurEntity.class);
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }
}
