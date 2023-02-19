package ulaval.glo2003.service;

import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.InMemoryOfferRepository;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.InMemoryProductRepository;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.InMemorySellerRepository;

import java.util.function.Function;
import java.util.stream.Collectors;

public class RepositoryManager {
    private final ISellerRepository sellerRepository;
    private final IProductRepository productRepository;
    private final IOfferRepository offerRepository;

    public RepositoryManager() {
        //TODO Delegate to factory
        sellerRepository = new InMemorySellerRepository();
        productRepository = new InMemoryProductRepository();
        offerRepository = new InMemoryOfferRepository();
    }

    public String createSeller(SellerRequest sellerRequest) {
        sellerRequest.validate();

        Seller seller = SellerMapper.requestToSeller(sellerRequest);
        sellerRepository.add(seller);

        return seller.getId();
    }

    public SellerResponse getSeller(String sellerId) {
        Seller seller = sellerRepository.findById(sellerId);
        productRepository.findAllBySellerId(sellerId).forEach(product -> {
            offerRepository.findAllByProductId(product.getId()).forEach(product::addOffer);
            seller.addProduct(product);
        });

        return SellerMapper.sellerToResponse(seller);
    }

    public String createProduct(String xSellerId, ProductRequest productRequest) {
        productRequest.validate();

        Product product = ProductMapper.requestToProduct(xSellerId, productRequest);
        productRepository.add(product);

        return product.getId();
    }

    public ProductResponse getProduct(String productId) {
        Product product = productRepository.findById(productId);
        Seller seller = sellerRepository.findById(product.getSellerId());

        return ProductMapper.productToResponseWithSeller(product, seller);
    }

    public String createOffer(String buyerName, String productId, OfferRequest offerRequest) {
        offerRequest.validate();

        Offer offer = OfferMapper.requestToOffer(productId, buyerName, offerRequest);
        offerRepository.add(offer);

        return offer.getId();
    }

    private Seller findSellerByProductId(String productId) {
        String sellerId = productRepository.findById(productId).getSellerId();
        return sellerRepository.findById(sellerId);
    }
}
