package ulaval.glo2003.domain.seller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.product.Product;

public class Seller {
    private final String name;
    private final String birthdate;
    private final String email;
    private final String phoneNumber;
    private final String bio;
    private final String id;
    private final String createdAt;
    private final Map<String, Product> productsMap;

    public Seller(String name, String birthdate, String email, String phoneNumber, String bio) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.productsMap = new HashMap<>();

        validateSellerParameters();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Map<String, Product> getProducts() {
        return productsMap;
    }

    public Product getProductById(String productId) {
        return productsMap.get(productId);
    }

    public void addProduct(Product product) {
        productsMap.put(product.getId(), product);
    }

    private void validateSellerParameters() {
        if (name.isBlank()) throw new InvalidParamException("name");
        if (isBirthdateInvalid()) throw new InvalidParamException("birthdate");
        if (isEmailInvalid()) throw new InvalidParamException("email");
        if (isPhoneInvalid()) throw new InvalidParamException("phone number");
        if (bio.isBlank()) throw new InvalidParamException("bio");
    }

    private boolean isBirthdateInvalid() {
        return !(isDateValid() && is18orMore(this.birthdate));
    }

    private boolean isEmailInvalid() {

        Pattern p = Pattern.compile(
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher m = p.matcher(this.email);
        return !m.matches();
    }

    private boolean isPhoneInvalid() {
        return !this.phoneNumber.chars().allMatch(Character::isDigit) || this.phoneNumber.length() != 11;
    }

    private boolean isDateValid() {
        try {
            LocalDate.parse(birthdate);
            return true;
        } catch (DateTimeParseException ignored) {
            return false;
        }
    }

    protected boolean is18orMore(String birthdate) {
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
