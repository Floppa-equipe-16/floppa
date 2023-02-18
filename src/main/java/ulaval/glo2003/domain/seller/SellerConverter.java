package ulaval.glo2003.domain.seller;

import ulaval.glo2003.api.seller.SellerRequest;

public class SellerConverter {

    public static Seller sellerRequestToSeller(SellerRequest sellerRequest) {
        return new Seller(
                sellerRequest.name,
                sellerRequest.birthdate,
                sellerRequest.email,
                sellerRequest.phoneNumber,
                sellerRequest.bio);
    }
}
