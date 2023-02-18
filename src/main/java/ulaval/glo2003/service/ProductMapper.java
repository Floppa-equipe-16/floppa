package ulaval.glo2003.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        ProductResponse productResponse = new ProductResponse();

        productResponse.title = product.getTitle();
        productResponse.description = product.getDescription();
        productResponse.suggestedPrice = product.getSuggestedPrice();
        productResponse.category = product.getCategory();
        productResponse.id = product.getId();
        productResponse.createdAt = product.getCreatedAt();
        productResponse.offers = OfferMapper.offersListToRepositoryResponse(product.getOffers());

        return productResponse;
    }

    public static ProductResponse productToResponseWithSeller(Product product, Seller seller) {
        ProductResponse productResponse = productToResponse(product);

        productResponse.addSellerInfo(seller.getId(), seller.getName());

        return productResponse;
    }

    public static List<ProductResponse> productsMapToResponsesList(Map<String, Product> productsMap) {
        ArrayList<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productsMap.values()) {
            productResponses.add(productToResponse(product));
        }
        return productResponses;
    }
}
