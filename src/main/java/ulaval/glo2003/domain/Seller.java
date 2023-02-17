package ulaval.glo2003.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class Seller {
    private final String name;
    private final String birthdate;
    private final String email;
    private final String phoneNumber;
    private final String bio;
    private final String id;
    private final String createdAt;
    private final List<Product> products;

    public Seller(SellerParams params) {
        this.name = params.name;
        this.birthdate = params.birthdate;
        this.email = params.email;
        this.phoneNumber = params.phoneNumber;
        this.bio = params.bio;
        this.products = new ArrayList<>();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();

        validateParameters();
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

    public List<Product> getProducts() {
        return products;
    }

    //TODO API in Domain
    public ArrayList<ProductResponse> getProductResponses() {
        ArrayList<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(new ProductResponse(product));
        }
        return productResponses;
    }

    //TODO Return optional ?
    public Product getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    private void validateParameters() {
        if (name.isBlank()) throw new InvalidParamException("name");
        if (isBirthdateInvalid()) throw new InvalidParamException("birthdate");
        if (isEmailInvalid()) throw new InvalidParamException("email");
        if (isPhoneInvalid()) throw new InvalidParamException("phone number");
        if (bio.isBlank()) throw new InvalidParamException("bio");
    }

    private boolean isBirthdateInvalid() {
        return !(isDateValid() && is18orMore());
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

    private boolean is18orMore() {
        try {
            String birthdateOffset = this.birthdate + "T00:00Z";
            OffsetDateTime birthdayDate = OffsetDateTime.parse(birthdateOffset);
            OffsetDateTime now = OffsetDateTime.now();
            OffsetDateTime birthday18Plus = birthdayDate.plusYears(18);
            return (birthday18Plus.isBefore(now));
        } catch (DateTimeParseException ignored) {
            return false;
        }
    }
}
