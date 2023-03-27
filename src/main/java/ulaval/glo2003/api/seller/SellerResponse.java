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

    private boolean isSellerResponseEquals(SellerResponse sellerResponse) {
        return name.equals(sellerResponse.name)
                && birthdate.equals(sellerResponse.birthdate)
                && email.equals(sellerResponse.email)
                && phoneNumber.equals(sellerResponse.phoneNumber)
                && bio.equals(sellerResponse.bio)
                && id.equals(sellerResponse.id)
                && createdAt.equals(sellerResponse.createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof SellerResponse) {
            SellerResponse sellerResponse = ((SellerResponse) o);
            return isSellerResponseEquals(sellerResponse);
        } else return false;
    }
}
