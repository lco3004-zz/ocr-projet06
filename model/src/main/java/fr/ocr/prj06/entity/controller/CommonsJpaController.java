package fr.ocr.prj06.entity.controller;

import fr.ocr.prj06.entity.common.JpaUtility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.prj06.entity.common.JpaUtility.getInstance;
import static fr.ocr.prj06.utility.constante.Messages.ConstantesPgm.UNITE_DE_PERSISTANCE;

public interface CommonsJpaController {

    Properties properties = new Properties();

    default void lireProps() throws IOException {
        InputStream inputStream = CommonsJpaController.class.getResourceAsStream("/info.properties");
        properties.load(inputStream);
    }

    default JpaUtility getJpaUtility() throws ExceptionInInitializerError {
        return getInstance(properties.getProperty(UNITE_DE_PERSISTANCE.getValeurConstante()));
    }
}

