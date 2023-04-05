package ulaval.glo2003.api.seller;

import java.util.List;
import java.util.Objects;
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

    public SellerResponse() {}

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof SellerResponse) {
            SellerResponse sellerResponse = ((SellerResponse) o);
            return isEqualsTo(sellerResponse);
        } else return false;
    }

    private boolean isEqualsTo(SellerResponse response) {
        return Objects.equals(name, response.name)
                && Objects.equals(birthdate, response.birthdate)
                && Objects.equals(email, response.email)
                && Objects.equals(phoneNumber, response.phoneNumber)
                && Objects.equals(bio, response.bio)
                && Objects.equals(id, response.id)
                && Objects.equals(createdAt, response.createdAt)
                && Objects.equals(products, response.products);
    }
}
