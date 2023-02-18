package ulaval.glo2003.service;

import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;

public class SellerMapper {

    public static Seller requestToSeller(SellerRequest sellerRequest) {
        return new Seller(
                sellerRequest.name,
                sellerRequest.birthdate,
                sellerRequest.email,
                sellerRequest.phoneNumber,
                sellerRequest.bio);
    }

    public static SellerResponse sellerToResponse(Seller seller) {
        SellerResponse sellerResponse = new SellerResponse();

        sellerResponse.id = seller.getId();
        sellerResponse.createAt = seller.getCreatedAt();
        sellerResponse.name = seller.getName();
        sellerResponse.birthdate = seller.getBirthdate();
        sellerResponse.email = seller.getEmail();
        sellerResponse.phoneNumber = seller.getPhoneNumber();
        sellerResponse.bio = seller.getBio();
        sellerResponse.products = ProductMapper.productsMapToResponsesList(seller.getProducts());

        return sellerResponse;
    }
}
