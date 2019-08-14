package fr.ocr.prj06.business.businessCtrl;


import fr.ocr.prj06.entity.common.JpaEmfInterface;

import java.io.IOException;

import static fr.ocr.prj06.entity.common.JpaEMFUtility.getInstanceEMF;

public class PersistenceMgmt {

    JpaEmfInterface jpa;

    public PersistenceMgmt() throws IOException {
        jpa = getInstanceEMF();
    }

    public void openDAO() throws Exception {
        jpa.openDao();
    }

    public void closeDao() throws Exception {
        jpa.closeDao();
    }
}
