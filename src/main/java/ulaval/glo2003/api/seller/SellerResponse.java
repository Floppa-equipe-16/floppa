package ulaval.glo2003.api.seller;

import java.util.List;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.seller.Seller;

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

    private boolean isSellerEquals(Seller seller) {
        return name.equals(seller.getName())
                && birthdate.equals(seller.getBirthdate())
                && email.equals(seller.getEmail())
                && phoneNumber.equals(seller.getPhoneNumber())
                && bio.equals(seller.getBio())
                && id.equals(seller.getId())
                && createdAt.equals(seller.getCreatedAt());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof SellerResponse) {
            SellerResponse sellerResponse = ((SellerResponse) o);
            return isSellerResponseEquals(sellerResponse);
        } else if (o instanceof Seller) {
            Seller seller = ((Seller) o);
            return isSellerEquals(seller);
        } else return false;
    }
}
