package ulaval.glo2003.api.seller;

import java.util.List;
import ulaval.glo2003.api.product.ProductResponse;

public class SellerResponse {
    public String id;
    public String createdAt;
    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;
    public List<ProductResponse> products;
}
