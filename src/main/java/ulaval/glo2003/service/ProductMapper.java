package ulaval.glo2003.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ulaval.glo2003.api.product.ProductCollectionResponse;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductFactory;
import ulaval.glo2003.domain.seller.Seller;

public class ProductMapper {
    private final ProductFactory factory;
    private final OfferMapper offerMapper;

    public ProductMapper(ProductFactory factory, OfferMapper offerMapper) {
        this.factory = factory;
        this.offerMapper = offerMapper;
    }

    public Product requestToProduct(String sellerId, ProductRequest productRequest) {
        return factory.createProduct(
                sellerId,
                productRequest.title,
                productRequest.description,
                productRequest.suggestedPrice,
                productRequest.category);
    }

    public ProductResponse productToResponse(Product product) {
        ProductResponse response = initializeResponse(product);
        response.offers = offerMapper.offersToCompleteCollectionResponse(product.getOffers());

        if (product.isSold()) {
            response.selectedOffer = offerMapper.offerToSelectedOfferResponse(product.getSelectedOffer());
        }

        return response;
    }

    public ProductResponse productToResponseWithSeller(Product product, Seller seller) {
        ProductResponse response = initializeResponse(product);

        response.addSellerInfo(seller.getId(), seller.getName());
        response.offers = offerMapper.offersToSummaryCollectionResponse(product.getOffers());

        return response;
    }

    public ProductCollectionResponse productsToCollectionResponse(List<ProductResponse> productResponses) {
        ProductCollectionResponse response = new ProductCollectionResponse();
        response.products = productResponses;

        return response;
    }

    private ProductResponse initializeResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.title = product.getTitle();
        response.description = product.getDescription();
        response.suggestedPrice = product.getSuggestedPrice();
        response.category = product.getCategory();
        response.id = product.getId();
        response.createdAt = product.getCreatedAt();
        response.saleStatus = product.getSaleStatus().toString();

        return response;
    }

    public List<ProductResponse> productsMapToResponsesList(Map<String, Product> productsMap) {
        return productsMap.values().stream().map(this::productToResponse).collect(Collectors.toList());
    }
}
