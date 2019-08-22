package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

@Converter(autoApply = true)
public class JpaConvBoolInt implements AttributeConverter<Boolean, Integer> {
    private LogsProjet logs;

    public JpaConvBoolInt() {
        logs = getLogsInstance();
        logs.maTrace(Level.DEBUG, "appel constructeur JpaConvBoolInt");
    }
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        logs.maTrace(Level.DEBUG, "appel de BooleanconvertToDatabaseColumn : " + aBoolean);

        if (aBoolean == null) {
            return null;
        }
        if (aBoolean) {
            return 1;
        }
        return 0;

    }

    @Override
    public Boolean convertToEntityAttribute(Integer aInteger) {
        logs.maTrace(Level.DEBUG, "appel de BooleanconvertToEntityAttribute : " + aInteger);
        if (aInteger == null) {
            return null;
        }
        if (aInteger == 1) {
            return true;
        } else if (aInteger == 0) {
            return false;
        } else
            return false;
    }
}
