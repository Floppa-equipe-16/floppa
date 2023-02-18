package ulaval.glo2003.service;

import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellersRepository;

public class RepositoryManager {

    private static RepositoryManager instance = null;
    public SellersRepository sellersRepository;

    private RepositoryManager() {
        sellersRepository = new SellersRepository();
    }

    public static RepositoryManager getInstance() {
        if (instance == null) instance = new RepositoryManager();
        return instance;
    }

    public String createSeller(SellerRequest sellerRequest) {
        sellerRequest.validateSellerNonNullParameters();

        Seller seller = SellerMapper.requestToSeller(sellerRequest);
        sellersRepository.addSeller(seller);

        return seller.getId();
    }

    public SellerResponse getSeller(String sellerId) {
        Seller foundSeller = sellersRepository.findSellerBySellerId(sellerId);

        return SellerMapper.sellerToResponse(foundSeller);
    }

    public String createProduct(String xSellerId, ProductRequest productRequest) {
        productRequest.validateProductNonNullParameter();

        Seller foundSeller = sellersRepository.findSellerBySellerId(xSellerId);
        Product product = ProductMapper.requestToProduct(productRequest);
        foundSeller.addProduct(product);

        return product.getId();
    }

    public ProductResponse getProduct(String productId) {
        Seller foundSeller = sellersRepository.findSellerByProductId(productId);
        Product foundProduct = foundSeller.getProductById(productId);

        return ProductMapper.productToResponseWithSeller(foundProduct, foundSeller);
    }

    public void createOffer(String xBuyerUsername, String productId, OfferRequest offerRequest) {
        offerRequest.validateOfferNonNullParameter();

        Seller foundSeller = sellersRepository.findSellerByProductId(productId);
        Offer offer = OfferMapper.requestToOffer(xBuyerUsername, offerRequest);
        foundSeller.getProductById(productId).addOffer(offer);
    }
}
