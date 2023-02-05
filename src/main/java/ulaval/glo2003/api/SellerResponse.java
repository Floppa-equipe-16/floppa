package ulaval.glo2003.api;

import ulaval.glo2003.domain.Seller;

import java.util.ArrayList;

public class SellerResponse {
    public String id;
    public String createAt;
    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;
    public ArrayList<ProductResponse> products;

    public SellerResponse(
            String id,
            String createAt,
            String name,
            String birthdate,
            String email,
            String phoneNumber,
            String bio,
            ArrayList<ProductResponse> products) {
        this.id = id;
        this.createAt = createAt;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.products = products;
    }
    public SellerResponse(Seller seller){
        this.id = seller.getId();
        this.createAt = seller.getCreatedAt();
        this.name = seller.getName();
        this.birthdate = seller.getBirthdate();
        this.email = seller.getEmail();
        this.phoneNumber = seller.getPhoneNumber();
        this.bio = seller.getBio();
        this.products = seller.getProductResponses();
    }
}
