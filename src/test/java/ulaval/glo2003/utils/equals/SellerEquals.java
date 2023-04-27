package ulaval.glo2003.utils.equals;

import java.util.List;
import ulaval.glo2003.api.seller.SellerCollectionResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;

public class SellerEquals {

    public static boolean sellerRequestEqualsSeller(SellerRequest request, Seller seller) {
        return request.name.equals(seller.getName())
                && request.birthdate.equals(seller.getBirthdate())
                && request.email.equals(seller.getEmail())
                && request.phoneNumber.equals(seller.getPhoneNumber())
                && request.bio.equals(seller.getBio());
    }

    public static boolean sellerResponseEqualsSeller(SellerResponse response, Seller seller) {
        return response.name.equals(seller.getName())
                && response.birthdate.equals(seller.getBirthdate())
                && response.email.equals(seller.getEmail())
                && response.phoneNumber.equals(seller.getPhoneNumber())
                && response.bio.equals(seller.getBio())
                && response.id.equals(seller.getId())
                && response.createdAt.equals(seller.getCreatedAt());
    }

    public static boolean sellerCollectionResponseEqualsSellers(
            SellerCollectionResponse collectionResponse, List<Seller> sellers) {
        boolean assertion = collectionResponse.sellers.size() == sellers.size();
        if (assertion) {
            for (int i = 0; i < sellers.size(); i++) {
                assertion = assertion && sellerResponseEqualsSeller(collectionResponse.sellers.get(i), sellers.get(i));
            }
        }
        return assertion;
    }
}
