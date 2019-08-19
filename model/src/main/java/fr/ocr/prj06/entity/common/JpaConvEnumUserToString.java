package fr.ocr.prj06.entity.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JpaConvEnumUserToString implements AttributeConverter<UserProfile, String> {
    @Override
    public String convertToDatabaseColumn(UserProfile userProfile) {
        if (userProfile == null) {
            return "U";
        }
        if (userProfile == UserProfile.GRIMPEUR) {
            return "U";
        } else if (userProfile == UserProfile.MEMBRE)
            return "M";
        else
            return "U";
    }

    @Override
    public UserProfile convertToEntityAttribute(String s) {
        if (s == null) {
            return UserProfile.GRIMPEUR;
        }
        if (s.equals("U"))
            return UserProfile.GRIMPEUR;
        else if (s.equals("M"))
            return UserProfile.MEMBRE;
        else
            return UserProfile.GRIMPEUR;
    }
}
