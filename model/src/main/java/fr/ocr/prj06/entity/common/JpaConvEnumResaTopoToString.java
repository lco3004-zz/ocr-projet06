package fr.ocr.prj06.entity.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.entity.common.EtatsResaTopo.*;

@Converter(autoApply = true)
public class JpaConvEnumResaTopoToString implements AttributeConverter<EtatsResaTopo, String> {
    @Override
    public String convertToDatabaseColumn(EtatsResaTopo attribute) {
        String valRet = null;
        if (attribute != null) {
            switch (attribute) {
                case R_FR:   //demande de rservation
                    valRet = R_FR.name().toLowerCase();
                    break;
                case A_FR:  //reservation acceptée
                    valRet = A_FR.name().toLowerCase();
                    break;
                case W_FR:  //en attente de rersevation
                    valRet = W_FR.name().toLowerCase();
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
            String tmp = dbData.trim().toLowerCase();
            if (R_FR.name().toLowerCase().equals(tmp)) {
                valRet = R_FR;
            } else if (A_FR.name().toLowerCase().equals(tmp)) {
                valRet = A_FR;
            } else if (W_FR.name().toLowerCase().equals(tmp)) {
                valRet = W_FR;
            }
        }
        return valRet;
    }
}






