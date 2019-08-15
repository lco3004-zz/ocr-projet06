package fr.ocr.prj06.business.businessCtrl;

import fr.ocr.prj06.controller.SpotJpaCtrl;
import fr.ocr.prj06.entity.stub.dbSpotEntity;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class SpotMgmt {
    private LogsProjet logs;

    public SpotMgmt() {
        logs = getLogsInstance();
    }

    public ArrayList<String> getListSpot() throws Exception {
        SpotJpaCtrl spotJpaCtrl = new SpotJpaCtrl();
        List<dbSpotEntity> dbTopoEntities = spotJpaCtrl.findDbEntities();
        int i = 0;
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (dbSpotEntity dbSpotEntity : dbTopoEntities) {
            String s = "rang: " + dbSpotEntity.getIdspot();
            stringArrayList.add(s);
            logs.maTrace(Level.DEBUG, "--> Liste Spot : " + s);
        }
        return stringArrayList;
    }

}
