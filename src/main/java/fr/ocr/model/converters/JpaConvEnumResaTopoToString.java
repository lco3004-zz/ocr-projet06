package fr.ocr.model.converters;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.constantes.EtatsResaTopo.*;

@Converter(autoApply = true)
public class JpaConvEnumResaTopoToString implements AttributeConverter<EtatsResaTopo, String> {
    @Override
    public String convertToDatabaseColumn(EtatsResaTopo attribute) {
        String valRet = null;
        if (attribute != null) {
            switch (attribute) {
                case R_FR:   //demande de rservation
                    valRet = R_FR.name().toUpperCase();
                    break;
                case A_FR:  //reservation acceptée
                    valRet = A_FR.name().toUpperCase();
                    break;
                case W_FR:  //en attente de rersevation
                    valRet = W_FR.name().toUpperCase();
                    break;
                default:
                valRet =null;
            }
        }
        return valRet;
    }

    @Override
    public EtatsResaTopo convertToEntityAttribute(String dbData) {
        EtatsResaTopo valRet=null;
        if (dbData != null && !"".equalsIgnoreCase(dbData.trim())) {
            String tmp = dbData.trim().toUpperCase();
            if (R_FR.name().toUpperCase().equals(tmp)) {
                valRet = R_FR;
            } else if (A_FR.name().toUpperCase().equals(tmp)) {
                valRet = A_FR;
            } else if (W_FR.name().toUpperCase().equals(tmp)) {
                valRet = W_FR;
            }
        }
        return valRet;
    }
}






