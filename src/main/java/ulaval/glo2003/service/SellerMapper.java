package ulaval.glo2003.service;

import java.util.ArrayList;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerFactory;

public class SellerMapper {
    private final SellerFactory factory;
    private final ProductMapper productMapper;
    private final OfferMapper offerMapper;

    public SellerMapper(SellerFactory factory, ProductMapper productMapper, OfferMapper offerMapper) {
        this.factory = factory;
        this.productMapper = productMapper;
        this.offerMapper = offerMapper;
    }

    public Seller requestToSeller(SellerRequest request) {
        return factory.createSeller(request.name, request.birthdate, request.email, request.phoneNumber, request.bio);
    }

    public SellerResponse sellerToResponse(Seller seller) {
        SellerResponse response = new SellerResponse();

        response.id = seller.getId();
        response.createdAt = seller.getCreatedAt();
        response.name = seller.getName();
        response.birthdate = seller.getBirthdate();
        response.email = seller.getEmail();
        response.phoneNumber = seller.getPhoneNumber();
        response.bio = seller.getBio();
        response.products = productMapper.productsMapToResponsesList(seller.getProducts());
        response.selectedOffer = new ArrayList<>();
        for (ProductResponse product : response.products) {
            if (product.selectedOffer != null) {
                response.selectedOffer.add(offerMapper.offerToSelectedOfferResponse(product.selectedOffer));
            }
        }
        if (response.selectedOffer.isEmpty()) {
            response.selectedOffer = null;
        }

        return response;
    }
}
