package ulaval.glo2003.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.seller.Seller;

public class ProductMapper {

    public static Product requestToProduct(String sellerId, ProductRequest productRequest) {
        return new Product(
                sellerId,
                productRequest.title,
                productRequest.description,
                productRequest.suggestedPrice,
                productRequest.category);
    }

    public static ProductResponse productToResponse(Product product) {
        ProductResponse productResponse = initializeResponse(product);
        productResponse.offers = OfferMapper.offersToCompleteCollectionResponse(product.getOffers());

        return productResponse;
    }

    public static ProductResponse productToResponseWithSeller(Product product, Seller seller) {
        ProductResponse response = initializeResponse(product);

        response.addSellerInfo(seller.getId(), seller.getName());
        response.offers = OfferMapper.offersToSummaryCollectionResponse(product.getOffers());

        return response;
    }

    public static List<ProductResponse> productsMapToResponsesList(Map<String, Product> productsMap) {
        return productsMap.values().stream()
                .map(ProductMapper::productToResponse)
                .collect(Collectors.toList());
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
