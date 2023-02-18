package ulaval.glo2003.api.product;

import ulaval.glo2003.api.offer.OffersResponseFactory;
import ulaval.glo2003.api.seller.SellerResponseFactory;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

public class ProductResponseFactory {
    public static ProductResponse createResponseWithOffers(Product product) {
        ProductResponse response = initializeResponse(product);

        response.offers = OffersResponseFactory.createCompleteResponse(product.getOffers());

        return response;
    }

    public static ProductResponse createResponseWithSummarySellerAndOffers(Product product, Seller seller) {
        ProductResponse response = initializeResponse(product);
        response.seller = SellerResponseFactory.createSimpleResponse(seller);
        response.offers = OffersResponseFactory.createSummaryResponse(product.getOffers());

        return response;
    }

    private static ProductResponse initializeResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.title = product.getTitle();
        response.description = product.getDescription();
        response.suggestedPrice = product.getSuggestedPrice();
        response.category = product.getCategory();
        response.id = product.getId();
        response.createdAt = product.getCreatedAt();

        return response;
    }
}
