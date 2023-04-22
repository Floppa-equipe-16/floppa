package ulaval.glo2003.service;

import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.product.ProductsStatsResponse;
import ulaval.glo2003.api.seller.SellerCollectionResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerFactory;

public class SellerMapper {
    private final SellerFactory factory;
    private final ProductMapper productMapper;

    public SellerMapper(SellerFactory factory, ProductMapper productMapper) {
        this.factory = factory;
        this.productMapper = productMapper;
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

        return response;
    }

    private SellerResponse sellerToScoredSellerResponse(Seller seller) {
        SellerResponse response = sellerToResponse(seller);
        response.productsStats = new ProductsStatsResponse();
        response.products = null;
        response.score = seller.getScore();

        response.productsStats.count = seller.getProducts().values().size();

        if (!seller.getProducts().values().isEmpty()) {
            response.productsStats.avgAmount = seller.getProducts().values().stream()
                    .mapToDouble(Product::getSuggestedPrice)
                    .average()
                    .orElse(Double.NaN);
        }

        return response;
    }

    public SellerCollectionResponse sellersToRankedCollectionResponse(List<Seller> sellers) {
        SellerCollectionResponse response = new SellerCollectionResponse();

        response.sellers =
                sellers.stream().map(this::sellerToScoredSellerResponse).collect(Collectors.toList());

        return response;
    }
}
