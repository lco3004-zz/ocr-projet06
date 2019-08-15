package fr.ocr.prj06.entity.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JpaConverterBooleanToInt implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        if (aBoolean == null) {
            return 0;
        }
        if (aBoolean) {
            return 1;
        }
        return 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer aInteger) {
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
