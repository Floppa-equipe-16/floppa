package ulaval.glo2003.service;

import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;

public class SellerMapper {

    public static Seller requestToSeller(SellerRequest request) {
        return new Seller(request.name, request.birthdate, request.email, request.phoneNumber, request.bio);
    }

    public static SellerResponse sellerToResponse(Seller seller) {
        SellerResponse response = new SellerResponse();

        response.id = seller.getId();
        response.createAt = seller.getCreatedAt();
        response.name = seller.getName();
        response.birthdate = seller.getBirthdate();
        response.email = seller.getEmail();
        response.phoneNumber = seller.getPhoneNumber();
        response.bio = seller.getBio();
        response.products = ProductMapper.productsMapToResponsesList(seller.getProducts());

        return response;
    }
}
