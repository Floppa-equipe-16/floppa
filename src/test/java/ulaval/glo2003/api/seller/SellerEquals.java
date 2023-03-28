package ulaval.glo2003.api.seller;

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
}
