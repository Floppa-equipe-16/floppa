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
    private final ArrayList<Product> products;

    public Seller(String name, String birthdate, String email, String phoneNumber, String bio) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.products = new ArrayList<>();

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

    public List<Product> getProducts() {
        return products;
    }

    public ArrayList<ProductResponse> getProductResponses() {
        ArrayList<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(new ProductResponse(product));
        }
        return productResponses;
    }

    public Product getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        products.add(product);
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
