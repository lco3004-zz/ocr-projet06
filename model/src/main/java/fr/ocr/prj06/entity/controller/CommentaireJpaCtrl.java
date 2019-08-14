package fr.ocr.prj06.entity.controller;

import fr.ocr.prj06.entity.common.JpaCtrl;
import fr.ocr.prj06.entity.stub.dbCommentaireEntity;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class CommentaireJpaCtrl extends JpaCtrl {

    public CommentaireJpaCtrl() throws Exception {
        super(dbCommentaireEntity.class);
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }
}



