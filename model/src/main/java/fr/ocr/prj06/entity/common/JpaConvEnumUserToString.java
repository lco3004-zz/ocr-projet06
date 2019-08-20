package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.entity.common.UserProfile.GRIMPEUR;
import static fr.ocr.prj06.entity.common.UserProfile.MEMBRE;
import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

@Converter(autoApply = true)
public class JpaConvEnumUserToString implements AttributeConverter<UserProfile, String> {

    LogsProjet logs;

    public JpaConvEnumUserToString() {
        logs = getLogsInstance();
        logs.maTrace(Level.DEBUG, "appel constructeur JpaConvEnumUserToString");
    }
    @Override
    public String convertToDatabaseColumn(UserProfile userProfile) {

        String valRet = null;

        if (userProfile != null) {
            switch (userProfile) {
                case GRIMPEUR:
                    valRet = "U";
                    break;
                case MEMBRE:
                    valRet = "M";
                    break;
                default:
                    valRet = null;
            }
        }
        logs.maTrace(Level.DEBUG, "appel de EnumconvertToDatabaseColumn : " + ((valRet == null) ? "valret est null" : valRet));
        return valRet;
    }

    @Override
    public UserProfile convertToEntityAttribute(String dbData) {

        UserProfile valRet = null;
        logs.maTrace(Level.DEBUG, "appel de EnumconvertToEntityAttribute (dbData est null) : ");

        if (dbData != null && !"".equalsIgnoreCase(dbData.trim())) {
            String tmp = dbData.trim().toUpperCase();
            if ("U".equals(tmp)) {
                valRet = GRIMPEUR;
            } else if ("M".equals(tmp)) {
                valRet = MEMBRE;
            }
        }

        logs.maTrace(Level.DEBUG, "appel de EnumconvertToEntityAttribute : " + ((valRet == null) ? "valret est null" : valRet.toString()));
        return valRet;
    }
}
