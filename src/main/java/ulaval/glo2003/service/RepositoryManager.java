package ulaval.glo2003.service;

import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.InMemoryProductRepository;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.InMemorySellerRepository;

public class RepositoryManager {
    private final ISellerRepository sellerRepository;
    private final IProductRepository productRepository;

    public RepositoryManager() {
        //TODO Delegate to factory
        sellerRepository = new InMemorySellerRepository();
        productRepository = new InMemoryProductRepository();
    }

    public String createSeller(SellerRequest sellerRequest) {
        sellerRequest.validate();

        Seller seller = SellerMapper.requestToSeller(sellerRequest);
        sellerRepository.add(seller);

        return seller.getId();
    }

    public SellerResponse getSeller(String sellerId) {
        Seller foundSeller = sellerRepository.findById(sellerId);

        return SellerMapper.sellerToResponse(foundSeller);
    }

    public String createProduct(String xSellerId, ProductRequest productRequest) {
        productRequest.validate();

        Seller foundSeller = sellerRepository.findById(xSellerId);
        Product product = ProductMapper.requestToProduct(xSellerId, productRequest);
        foundSeller.addProduct(product);

        return product.getId();
    }

    public ProductResponse getProduct(String productId) {
        Seller seller = findSellerByProductId(productId);
        Product product = seller.getProductById(productId);

        return ProductMapper.productToResponseWithSeller(product, seller);
    }

    public void createOffer(String buyerName, String productId, OfferRequest offerRequest) {
        offerRequest.validateOfferNonNullParameter();

        Seller seller = findSellerByProductId(productId);
        Offer offer = OfferMapper.requestToOffer(productId, buyerName, offerRequest);
        seller.getProductById(productId).addOffer(offer);
    }

    private Seller findSellerByProductId(String productId) {
        String sellerId = productRepository.findById(productId).getSellerId();
        return sellerRepository.findById(sellerId);
    }
}
