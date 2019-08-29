package fr.ocr.prj06.converters;


import fr.ocr.prj06.constantes.UserProfile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.constantes.UserProfile.GRIMPEUR;
import static fr.ocr.prj06.constantes.UserProfile.MEMBRE;

@Converter(autoApply = true)
public class JpaConvEnumUserToString implements AttributeConverter<UserProfile, String> {


    public JpaConvEnumUserToString() {
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
        return valRet;
    }

    @Override
    public UserProfile convertToEntityAttribute(String dbData) {

        UserProfile valRet = null;

        if (dbData != null && !"".equalsIgnoreCase(dbData.trim())) {
            String tmp = dbData.trim().toUpperCase();
            if ("U".equals(tmp)) {
                valRet = GRIMPEUR;
            } else if ("M".equals(tmp)) {
                valRet = MEMBRE;
            }
        }
        return valRet;
    }
}
