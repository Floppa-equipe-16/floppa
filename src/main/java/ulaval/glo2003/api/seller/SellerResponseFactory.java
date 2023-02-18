package ulaval.glo2003.api.seller;

import java.util.stream.Collectors;
import ulaval.glo2003.api.product.ProductResponseFactory;
import ulaval.glo2003.domain.Seller;

public class SellerResponseFactory {
    public static SellerResponse createSimpleResponse(Seller seller) {
        SellerResponse response = new SellerResponse();
        response.id = seller.getId();
        response.name = seller.getName();

        return response;
    }

    public static SellerResponse createCompleteResponse(Seller seller) {
        SellerResponse response = new SellerResponse();
        response.id = seller.getId();
        response.createAt = seller.getCreatedAt();
        response.name = seller.getName();
        response.birthdate = seller.getBirthdate();
        response.email = seller.getEmail();
        response.phoneNumber = seller.getPhoneNumber();
        response.bio = seller.getBio();

        response.products = seller.getProducts().stream()
                .map(ProductResponseFactory::createResponseWithOffers)
                .collect(Collectors.toList());

        return response;
    }
}
