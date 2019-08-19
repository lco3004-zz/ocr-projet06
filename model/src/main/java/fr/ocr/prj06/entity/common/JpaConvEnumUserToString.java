package fr.ocr.prj06.entity.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static fr.ocr.prj06.entity.common.UserProfile.GRIMPEUR;
import static fr.ocr.prj06.entity.common.UserProfile.MEMBRE;

@Converter
public class JpaConvEnumUserToString implements AttributeConverter<UserProfile, String> {
    @Override
    public String convertToDatabaseColumn(UserProfile userProfile) {

        if (userProfile == null) {
            return null;
        }

        switch (userProfile) {
            case GRIMPEUR:
                return "U";
            case MEMBRE:
                return "M";
            default:
                return null;
        }
    }

    @Override
    public UserProfile convertToEntityAttribute(String dbData) {
        if (dbData == null || "".equalsIgnoreCase(dbData.trim())) {
            return null;
        }
        String tmp = dbData.trim().toUpperCase();
        if ("U".equals(tmp)) {
            return GRIMPEUR;
        } else if ("M".equals(tmp)) {
            return MEMBRE;
        } else {
            return null;
        }
    }
}
