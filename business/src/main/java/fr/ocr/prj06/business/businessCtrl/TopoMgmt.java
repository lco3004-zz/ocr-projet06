package fr.ocr.prj06.business.businessCtrl;

import fr.ocr.prj06.entity.controller.TopoJpaCtrl;
import fr.ocr.prj06.entity.stub.dbTopoEntity;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class TopoMgmt {
    LogsProjet logs;

    public TopoMgmt() {

        logs = getLogsInstance();
    }

    public ArrayList<String> getListTopos() throws Exception {
        TopoJpaCtrl topoJpaController = new TopoJpaCtrl();
        List<dbTopoEntity> dbTopoEntities = topoJpaController.findDbEntities();
        int i = 0;
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (dbTopoEntity topoEntity : dbTopoEntities) {
            String s = "rang: " + topoEntity.getIdtopo() +
                    " , Nom: " + topoEntity.getNom() +
                    " , Lieu: " + topoEntity.getLieu() +
                    " , Resume: " + topoEntity.getResume() +
                    " , DateParution " + topoEntity.getDateDeParution();
            stringArrayList.add(s);
            logs.maTrace(Level.DEBUG, "--> Liste Topos : " + s);
        }
        return stringArrayList;
    }

}
