package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

@Converter
public class JpaConvBoolInt implements AttributeConverter<Boolean, Integer> {
    LogsProjet logs;

    public JpaConvBoolInt() {
        logs = getLogsInstance();
        logs.maTrace(Level.DEBUG, "appel constructeur JpaConvBoolInt");
    }
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        logs.maTrace(Level.DEBUG, "appel de convertToDatabaseColumn : ");

        if (aBoolean == null) {
            return 12;
        }
        if (aBoolean) {
            return 56;
        }
        return 18;

    }

    @Override
    public Boolean convertToEntityAttribute(Integer aInteger) {
        logs.maTrace(Level.DEBUG, "appel de convertToEntityAttribute : ");
        if (aInteger == null) {
            return null;
        }
        if (aInteger == 56) {
            return true;
        } else if (aInteger == 18) {
            return false;
        } else
            return false;
    }
}
