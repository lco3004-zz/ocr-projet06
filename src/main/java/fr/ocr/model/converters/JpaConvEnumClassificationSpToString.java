package fr.ocr.model.converters;

import fr.ocr.model.constantes.SpotClassification;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.model.constantes.SpotClassification.OFFICIEL;
import static fr.ocr.model.constantes.SpotClassification.STANDARD;

@Converter(autoApply = true)
public class JpaConvEnumClassificationSpToString  implements AttributeConverter<SpotClassification, String>{
        @Override
        public String convertToDatabaseColumn(SpotClassification attribute) {
            String valRet = null;
            if (attribute != null) {
                switch (attribute) {
                    case STANDARD:
                        valRet = STANDARD.name().toUpperCase();
                        break;
                    case OFFICIEL:
                        valRet = OFFICIEL.name().toUpperCase();
                        break;
                    default:
                        valRet =null;
                }
            }
            return valRet;
        }

        @Override
        public SpotClassification convertToEntityAttribute(String dbData) {
            SpotClassification valRet = null;
            if (dbData != null && !"".equalsIgnoreCase(dbData.trim())) {
                String tmp = dbData.trim().toUpperCase();
                if (STANDARD.name().toUpperCase().equals(tmp)) {
                    valRet = STANDARD;
                } else if (OFFICIEL.name().toUpperCase().equals(tmp)) {
                    valRet = OFFICIEL;
                }
            }
            return valRet;
        }
    }

