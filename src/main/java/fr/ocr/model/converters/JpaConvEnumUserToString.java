package fr.ocr.model.converters;


import fr.ocr.model.constantes.UserProfile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.model.constantes.UserProfile.GRIMPEUR;
import static fr.ocr.model.constantes.UserProfile.MEMBRE;


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
                    valRet = "GRIMPEUR";
                    break;
                case MEMBRE:
                    valRet = "MEMBRE";
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
            if ("GRIMPEUR".equals(tmp)) {
                valRet = GRIMPEUR;
            } else if ("MEMBRE".equals(tmp)) {
                valRet = MEMBRE;
            }
        }
        return valRet;
    }
}
