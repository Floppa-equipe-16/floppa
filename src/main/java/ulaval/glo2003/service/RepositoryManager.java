package ulaval.glo2003.service;

import java.util.ArrayList;
import java.util.List;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.product.ProductCollectionResponse;
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
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.InMemorySellerRepository;
import ulaval.glo2003.domain.seller.Seller;

public class RepositoryManager {
    private final ISellerRepository sellerRepository;
    private final IProductRepository productRepository;
    private final IOfferRepository offerRepository;

    public RepositoryManager() {
        sellerRepository = new InMemorySellerRepository();
        productRepository = new InMemoryProductRepository();
        offerRepository = new InMemoryOfferRepository();
    }

    public String createSeller(SellerRequest sellerRequest) {
        sellerRequest.validate();

        Seller seller = SellerMapper.requestToSeller(sellerRequest);
        sellerRepository.save(seller);

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

    public String createProduct(String sellerId, ProductRequest productRequest) {
        productRequest.validate();

        Product product = ProductMapper.requestToProduct(sellerId, productRequest);
        Seller seller = sellerRepository.findById(sellerId);
        seller.addProduct(product);

        productRepository.save(product);

        return product.getId();
    }

    public ProductCollectionResponse getProducts(ProductFilter productFilter) {
        List<Product> products = getProductsWithOffers(productFilter);
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = ProductMapper.productToResponseWithSeller(
                    product, sellerRepository.findById(product.getSellerId()));
            productResponses.add(productResponse);
        }

        return ProductMapper.productsToCollectionResponse(productResponses);
    }

    public ProductResponse getProduct(String productId) {
        Product product = getProductWithOffers(productId);

        Seller seller = sellerRepository.findById(product.getSellerId());

        return ProductMapper.productToResponseWithSeller(product, seller);
    }

    public String createOffer(String buyerName, String productId, OfferRequest offerRequest) {
        offerRequest.validate();

        Offer offer = OfferMapper.requestToOffer(productId, buyerName, offerRequest);

        Product product = getProductWithOffers(productId);
        product.addOffer(offer);
        offerRepository.save(offer);

        return offer.getId();
    }

    private List<Product> getProductsWithOffers(ProductFilter productFilter) {
        List<Product> products = productRepository.findAllProduct(productFilter);

        for (Product product : products) {
            offerRepository.findAllByProductId(product.getId()).forEach(product::addOffer);
        }

        return products;
    }

    private Product getProductWithOffers(String id) {
        Product product = productRepository.findById(id);
        offerRepository.findAllByProductId(product.getId()).forEach(product::addOffer);

        return product;
    }
}
