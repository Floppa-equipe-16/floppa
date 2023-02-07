package ulaval.glo2003.api;

import java.util.ArrayList;
import ulaval.glo2003.domain.Seller;

public class SellerResponse {
    public String id;
    public String createAt;
    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;
    public ArrayList<ProductResponse> products;

    public SellerResponse(Seller seller) {
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
