package ulaval.glo2003.api.seller;

import java.util.ArrayList;
import ulaval.glo2003.api.product.ProductResponse;
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

    private SellerResponse() {}

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

    public static SellerResponse ctorSimplifiedSellerResponse(Seller seller) {
        SellerResponse sellerResponse = new SellerResponse();
        sellerResponse.id = seller.getId();
        sellerResponse.name = seller.getName();
        return sellerResponse;
    }
}
