package ulaval.glo2003.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.NotFoundException;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.product.ProductCollectionResponse;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.product.ProductSellRequest;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.product.IProductRepository;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;

public class SellingService {
    private final ISellerRepository sellerRepository;
    private final IProductRepository productRepository;
    private final IOfferRepository offerRepository;
    private final SellerMapper sellerMapper;
    private final ProductMapper productMapper;
    private final OfferMapper offerMapper;

    public SellingService(
            ISellerRepository sellerRepository,
            IProductRepository productRepository,
            IOfferRepository offerRepository,
            SellerMapper sellerMapper,
            ProductMapper productMapper,
            OfferMapper offerMapper) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.sellerMapper = sellerMapper;
        this.productMapper = productMapper;
        this.offerMapper = offerMapper;
    }

    public String createSeller(SellerRequest sellerRequest) {
        sellerRequest.validate();

        Seller seller = sellerMapper.requestToSeller(sellerRequest);
        sellerRepository.save(seller);

        return seller.getId();
    }

    public SellerResponse getSeller(String sellerId) {
        Seller seller = getSellerWithProducts(sellerId);

        return sellerMapper.sellerToResponse(seller);
    }

    private Seller getSellerWithProducts(String sellerId) {
        Seller seller = sellerRepository.findById(sellerId);
        addProductsAndSelectedOfferToSeller(seller);

        return seller;
    }

    private void addProductsAndSelectedOfferToSeller(Seller seller) {
        productRepository.findAllBySellerId(seller.getId()).forEach(product -> {
            addOffersToProduct(product);
            seller.addProduct(product);
        });
    }

    public String createProduct(String sellerId, ProductRequest productRequest) {
        if (sellerId == null) {
            throw new MissingParamException("seller id");
        }

        productRequest.validate();

        Product product = productMapper.requestToProduct(sellerId, productRequest);
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

        return productMapper.productToResponseWithSeller(product, seller);
    }

    public ProductCollectionResponse getProducts(ProductFilter productFilter) {
        List<Product> products = getProductsWithOffers(productFilter);
        List<ProductResponse> productResponses = new ArrayList<>();

        products.forEach(product -> {
            Seller seller = sellerRepository.findById(product.getSellerId());
            productResponses.add(productMapper.productToResponseWithSeller(product, seller));
        });

        return productMapper.productsToCollectionResponse(productResponses);
    }

    private List<Product> getProductsWithOffers(ProductFilter productFilter) {
        List<Product> products = productRepository.findAll(productFilter);
        products.forEach(this::addOffersToProduct);

        return products;
    }

    public String createOffer(String buyerName, String productId, OfferRequest offerRequest) {
        if (buyerName == null) {
            throw new MissingParamException("buyer name");
        }
        if (buyerName.isBlank()) {
            throw new InvalidParamException("buyer name");
        }

        offerRequest.validate();
        Offer offer = offerMapper.requestToOffer(productId, buyerName, offerRequest);

        if (canAddOfferToProduct(productId, offer)) {
            offerRepository.save(offer);
        }
        return offer.getId();
    }

    public void sellProduct(String sellerId, String productId, ProductSellRequest productRequest) {
        if (sellerId == null) throw new MissingParamException("seller id");
        if (productId == null) throw new MissingParamException("product id");
        productRequest.validate();

        Seller seller = getSellerWithProducts(sellerId);
        Optional<Product> product = seller.getProductById(productId);
        if(product.isEmpty()) {
            throw new NotFoundException(String.format("product with id %s", productId));
        }

        product.get().sellTo(productRequest.username);

        productRepository.save(product.get());
        offerRepository.save(product.get().getSelectedOffer());
    }

    private boolean canAddOfferToProduct(String productId, Offer offer) {
        Product product = getProductWithOffers(productId);
        product.addOffer(offer);

        return true;
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
