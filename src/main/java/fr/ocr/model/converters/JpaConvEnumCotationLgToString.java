package fr.ocr.model.converters;

import fr.ocr.prj06.constantes.CotationLongueur;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.constantes.CotationLongueur.*;

@Converter(autoApply = true)
public class JpaConvEnumCotationLgToString implements AttributeConverter<CotationLongueur, String> {
    @Override
    public String convertToDatabaseColumn(CotationLongueur attribute) {
        String valRet = null;
        if (attribute != null) {
            switch (attribute) {
                case QUATRE_A:
                    valRet = QUATRE_A.name().toUpperCase();
                    break;
                case QUATRE_B:
                    valRet = QUATRE_B.name().toUpperCase();
                    break;
                case QUATRE_C:
                    valRet = QUATRE_C.name().toUpperCase();
                    break;
                case SIX_A :
                    valRet = SIX_A.name().toUpperCase();
                    break;
                case SIX_APLUS :
                    valRet = SIX_APLUS.name().toUpperCase();
                    break;
                case SIX_BPLUS :
                    valRet = SIX_BPLUS.name().toUpperCase();
                    break;
                case SIX_CPLUS :
                    valRet = SIX_CPLUS.name().toUpperCase();
                    break;
                default:
                    valRet =null;
            }
        }
        return valRet;
    }

    @Override
    public CotationLongueur convertToEntityAttribute(String dbData) {
        CotationLongueur valRet = null;
        if (dbData != null && !"".equalsIgnoreCase(dbData.trim())) {
            String tmp = dbData.trim().toUpperCase();
            if (QUATRE_A.name().toUpperCase().equals(tmp)) {
                valRet = QUATRE_A;
            } else if (QUATRE_B.name().toUpperCase().equals(tmp)) {
                valRet = QUATRE_B;
            } else if (QUATRE_C.name().toUpperCase().equals(tmp)) {
                valRet = QUATRE_C;
            } else if (SIX_A.name().toUpperCase().equals(tmp)) {
                valRet = SIX_A;
            } else if (SIX_APLUS.name().toUpperCase().equals(tmp)) {
                valRet = SIX_APLUS;
            } else if (SIX_BPLUS.name().toUpperCase().equals(tmp)) {
                valRet = SIX_BPLUS;
            } else if (SIX_CPLUS.name().toUpperCase().equals(tmp)) {
                valRet = SIX_CPLUS;
            }
        }
        return valRet;
    }
 }
