package ulaval.glo2003.domain.seller;

import java.util.*;
import ulaval.glo2003.domain.product.Product;

public class Seller {
    private final String id;
    private final String name;
    private final String birthdate;
    private final String email;
    private final String phoneNumber;
    private final String bio;
    private final String createdAt;
    private final Map<String, Product> productsMap;

    public Seller(
            String id, String name, String createdAt, String birthdate, String email, String phoneNumber, String bio) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.productsMap = new HashMap<>();
    }

    public Seller(Seller that) {
        name = that.getName();
        birthdate = that.getBirthdate();
        email = that.getEmail();
        phoneNumber = that.getPhoneNumber();
        bio = that.getBio();
        id = that.getId();
        createdAt = that.getCreatedAt();

        productsMap = new HashMap<>();
        that.getProducts().forEach((s, product) -> productsMap.put(s, new Product(product)));
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Seller that = (Seller) obj;
        return id.equals(that.getId())
                && name.equalsIgnoreCase(that.getName())
                && birthdate.equalsIgnoreCase(that.getBirthdate())
                && email.equalsIgnoreCase(that.getEmail())
                && phoneNumber.equalsIgnoreCase(that.getPhoneNumber())
                && bio.equalsIgnoreCase(that.getBio())
                && createdAt.equalsIgnoreCase(that.getCreatedAt());
    }
}
