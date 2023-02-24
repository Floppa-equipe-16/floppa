package ulaval.glo2003.domain.seller;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

class SellerValidator {
    private static final int PHONE_NUMBER_LENGTH = 11;
    private static final int MINIMUM_AGE = 18;
    private static final String UTC_MIDNIGHT_OFFSET = "T00:00Z";

    public static void validateParam(Seller seller) {
        if (isNameInvalid(seller.getName())) throw new InvalidParamException("name");
        if (isBirthdateInvalid(seller.getBirthdate())) throw new InvalidParamException("birthdate");
        if (isEmailInvalid(seller.getEmail())) throw new InvalidParamException("email");
        if (isPhoneNumberInvalid(seller.getPhoneNumber())) throw new InvalidParamException("phone number");
        if (isBioInvalid(seller.getBio())) throw new InvalidParamException("bio");
    }

    protected static boolean isNameInvalid(String s) {
        return s.isBlank();
    }

    protected static boolean isBioInvalid(String s) {
        return s.isBlank();
    }

    protected static boolean isPhoneNumberInvalid(String phoneNumber) {
        return !(phoneNumber.chars().allMatch(Character::isDigit) && phoneNumber.length() == PHONE_NUMBER_LENGTH);
    }

    protected static boolean isEmailInvalid(String email) {
        Pattern p = Pattern.compile(
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    protected static boolean isBirthdateInvalid(String birthDate) {
        return isBirthdateFormatInvalid(birthDate) || !isOldEnough(birthDate);
    }

    protected static boolean isBirthdateFormatInvalid(String birthdate) {
        try {
            LocalDate.parse(birthdate);
            return false;
        } catch (DateTimeParseException ignored) {
            return true;
        }
    }

    protected static boolean isOldEnough(String birthdate) {
        try {
            String birthdateOffset = birthdate + UTC_MIDNIGHT_OFFSET;
            OffsetDateTime birthdayDate = OffsetDateTime.parse(birthdateOffset);
            OffsetDateTime now = OffsetDateTime.now();
            OffsetDateTime birthdayOldEnough = birthdayDate.plusYears(MINIMUM_AGE);
            return birthdayOldEnough.isBefore(now);
        } catch (DateTimeParseException ignored) {
            return false;
        }
    }
}
