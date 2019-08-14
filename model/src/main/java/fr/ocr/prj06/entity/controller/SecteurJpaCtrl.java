package fr.ocr.prj06.entity.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.stub.dbSecteurEntity;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class SecteurJpaCtrl extends JpaCtrl {

    public SecteurJpaCtrl() throws Exception {
        super(dbSecteurEntity.class);
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }
}
