package ulaval.glo2003.domain.seller;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

class SellerParamValidator {
    private final Seller seller;

    public SellerParamValidator(Seller seller) {
        this.seller = seller;
    }

    public void validateSellerParamThrowIfInvalid() {
        if (isNameInvalid(seller.getName())) throw new InvalidParamException("name");

        if (isBirthdateInvalid(seller.getBirthdate())) throw new InvalidParamException("birthdate");

        if (isEmailInvalid(seller.getEmail())) throw new InvalidParamException("email");

        if (isPhoneNumberInvalid(seller.getPhoneNumber())) throw new InvalidParamException("phone number");

        if (isBioInvalid(seller.getBio())) throw new InvalidParamException("bio");
    }

    protected boolean isNameInvalid(String s) {
        return s.isBlank();
    }

    protected boolean isBioInvalid(String s) {
        return s.isBlank();
    }

    protected boolean isPhoneNumberInvalid(String phoneNumber) {
        return !(phoneNumber.chars().allMatch(Character::isDigit) && phoneNumber.length() == 11);
    }

    protected boolean isEmailInvalid(String email) {
        Pattern p = Pattern.compile(
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    protected boolean isBirthdateInvalid(String birthDate) {
        return isBirthdateFormatInvalid(birthDate) || !isBirthdate18orMore(birthDate);
    }

    protected boolean isBirthdateFormatInvalid(String birthdate) {
        try {
            LocalDate.parse(birthdate);
            return false;
        } catch (DateTimeParseException ignored) {
            return true;
        }
    }

    protected boolean isBirthdate18orMore(String birthdate) {
        try {
            String birthdateOffset = birthdate + "T00:00Z";
            OffsetDateTime birthdayDate = OffsetDateTime.parse(birthdateOffset);
            OffsetDateTime now = currentTime();
            OffsetDateTime birthday18Plus = birthdayDate.plusYears(18);
            return birthday18Plus.isBefore(now);
        } catch (DateTimeParseException ignored) {
            return false;
        }
    }

    protected OffsetDateTime currentTime() {
        return OffsetDateTime.now();
    }
}
