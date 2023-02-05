package ulaval.glo2003.api;

import ulaval.glo2003.domain.Product;

import java.util.ArrayList;

public class SellerResponse {

    public String id;
    public String createAt;

    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;
    public ArrayList<Product> products;

    public SellerResponse(
            String id,
            String createAt,
            String name,
            String birthdate,
            String email,
            String phoneNumber,
            String bio) {
        this.id = id;
        this.createAt = createAt;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }
}
