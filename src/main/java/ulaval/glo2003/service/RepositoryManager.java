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
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;

public class RepositoryManager {
    private final ISellerRepository sellerRepository;
    private final IProductRepository productRepository;
    private final IOfferRepository offerRepository;

    public RepositoryManager(
            ISellerRepository sellerRepository,
            IProductRepository productRepository,
            IOfferRepository offerRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
    }

    public String createSeller(SellerRequest sellerRequest) {
        sellerRequest.validate();

        Seller seller = SellerMapper.requestToSeller(sellerRequest);
        sellerRepository.save(seller);

        return seller.getId();
    }

    public SellerResponse getSeller(String sellerId) {
        Seller seller = sellerRepository.findById(sellerId);
        addProductsToSeller(seller);

        return SellerMapper.sellerToResponse(seller);
    }

    private void addProductsToSeller(Seller seller) {
        productRepository.findAllBySellerId(seller.getId()).forEach(product -> {
            addOffersToProduct(product);
            seller.addProduct(product);
        });
    }

    public String createProduct(String sellerId, ProductRequest productRequest) {
        productRequest.validate();

        Product product = ProductMapper.requestToProduct(sellerId, productRequest);
        if (canAddProductToSeller(sellerId, product)) {
            productRepository.save(product);
        }

        return product.getId();
    }

    private boolean canAddProductToSeller(String sellerId, Product product) {
        Seller seller = sellerRepository.findById(sellerId);
        seller.addProduct(product);

        return true;
    }

    public ProductResponse getProduct(String productId) {
        Product product = getProductWithOffers(productId);
        Seller seller = sellerRepository.findById(product.getSellerId());

        return ProductMapper.productToResponseWithSeller(product, seller);
    }

    public ProductCollectionResponse getProducts(ProductFilter productFilter) {
        List<Product> products = getProductsWithOffers(productFilter);
        List<ProductResponse> productResponses = new ArrayList<>();

        products.forEach(product -> {
            Seller seller = sellerRepository.findById(product.getSellerId());
            productResponses.add(ProductMapper.productToResponseWithSeller(product, seller));
        });

        return ProductMapper.productsToCollectionResponse(productResponses);
    }

    public String createOffer(String buyerName, String productId, OfferRequest offerRequest) {
        offerRequest.validate();
        Offer offer = OfferMapper.requestToOffer(productId, buyerName, offerRequest);

        if (canAddOfferToProduct(productId, offer)) {
            offerRepository.save(offer);
        }
        return offer.getId();
    }

    private boolean canAddOfferToProduct(String productId, Offer offer) {
        Product product = getProductWithOffers(productId);
        product.addOffer(offer);

        return true;
    }

    private List<Product> getProductsWithOffers(ProductFilter productFilter) {
        List<Product> products = productRepository.findAllProducts(productFilter);
        products.forEach(this::addOffersToProduct);

        return products;
    }

    private Product getProductWithOffers(String id) {
        Product product = productRepository.findById(id);
        addOffersToProduct(product);

        return product;
    }

    private void addOffersToProduct(Product product) {
        offerRepository.findAllByProductId(product.getId()).forEach(product::addOffer);
    }
}
