package fr.ocr.prj06.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class JpaConvBoolInt implements AttributeConverter<Boolean, Integer> {

    public JpaConvBoolInt() {
    }
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {

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
