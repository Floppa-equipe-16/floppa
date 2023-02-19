package ulaval.glo2003.domain.seller;

import java.time.Instant;
import java.util.*;
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

        SellerParamValidator sellerParamValidator = new SellerParamValidator(this);
        sellerParamValidator.validateSellerParamThrowIfInvalid();

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
}
