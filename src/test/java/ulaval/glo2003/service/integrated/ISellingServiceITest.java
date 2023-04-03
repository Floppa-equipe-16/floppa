package ulaval.glo2003.service.integrated;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.api.product.ProductCollectionResponse;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.offer.IOfferRepository;
import ulaval.glo2003.domain.offer.Offer;
import ulaval.glo2003.domain.offer.OfferFactory;
import ulaval.glo2003.domain.offer.OfferTestUtils;
import ulaval.glo2003.domain.product.*;
import ulaval.glo2003.domain.seller.ISellerRepository;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerFactory;
import ulaval.glo2003.domain.seller.SellerTestUtils;
import ulaval.glo2003.service.OfferMapper;
import ulaval.glo2003.service.ProductMapper;
import ulaval.glo2003.service.SellerMapper;
import ulaval.glo2003.service.SellingService;

public abstract class ISellingServiceITest {
    private static final String SELLER_ID = "SELLER";
    private static final String BUYER_USERNAME = "BUYER";
    private ISellerRepository sellerRepository;
    private IProductRepository productRepository;
    private IOfferRepository offerRepository;
    private static SellerMapper sellerMapper;
    private static ProductMapper productMapper;
    private static OfferMapper offerMapper;
    private static final SellerFactory sellerFactory = new SellerFactory();
    private static final ProductFactory productFactory = new ProductFactory();
    private static final OfferFactory offerFactory = new OfferFactory();
    private Seller sellerStub;
    private Product productStub;
    private Offer offerStub;
    private SellingService sellingService;

    @BeforeAll
    public static void setUpAll() {
        offerMapper = new OfferMapper(offerFactory);
        productMapper = new ProductMapper(productFactory, offerMapper);
        sellerMapper = new SellerMapper(sellerFactory, productMapper);
    }

    @BeforeEach
    public void setUp() {
        sellerRepository = createSellerRepository();
        productRepository = createProductRepository();
        offerRepository = createOfferRepository();
        sellingService = new SellingService(
                sellerRepository, productRepository, offerRepository, sellerMapper, productMapper, offerMapper);
    }

    @AfterEach
    public void tearDown() {
        sellerRepository.reset();
        productRepository.reset();
        offerRepository.reset();
        sellerStub = null;
        productStub = null;
        offerStub = null;
    }

    @Test
    public void canCreateSeller() {
        SellerRequest sellerRequest = SellerTestUtils.createSellerRequest();

        String id = sellingService.createSeller(sellerRequest);

        Seller seller = sellerRepository.findById(id);
        assertThat(seller).isNotNull();
    }

    @Test
    public void canGetSellerWithNoProduct() {
        saveSellerToRepository();

        SellerResponse sellerResponse = sellingService.getSeller(sellerStub.getId());

        assertThat(sellerResponse.id).isEqualTo(sellerStub.getId());
    }

    @Test
    public void canGetSellerWithProduct() {
        saveSellerToRepository();
        saveProductToRepository();

        SellerResponse sellerResponse = sellingService.getSeller(sellerStub.getId());

        assertThat(sellerResponse.products).hasSize(1);
        assertThat(sellerResponse.id).isEqualTo(sellerStub.getId());
    }

    @Test
    public void canCreateProductWhenSellerExists() {
        saveSellerToRepository();
        ProductRequest productRequest = ProductTestUtils.createProductRequest();

        String id = sellingService.createProduct(sellerStub.getId(), productRequest);

        Product product = productRepository.findById(id);
        assertThat(product).isNotNull();
    }

    @Test
    public void createProductThrowsWhenSellerDoesNotExist() {
        ProductRequest productRequest = ProductTestUtils.createProductRequest();

        assertThrows(NotFoundException.class, () -> sellingService.createProduct(SELLER_ID, productRequest));
    }

    @Test
    public void canGetProductWithNoOffer() {
        saveSellerToRepository();
        saveProductToRepository();

        ProductResponse productResponse = sellingService.getProduct(productStub.getId());

        assertThat(productResponse.id).isEqualTo(productStub.getId());
    }

    @Test
    public void canGetProductWithOffer() {
        saveSellerToRepository();
        saveProductToRepository();
        saveOfferToRepository();

        ProductResponse productResponse = sellingService.getProduct(productStub.getId());

        assertThat(productResponse.id).isEqualTo(productStub.getId());
        assertThat(productResponse.offers.count).isEqualTo(1);
    }

    @Test
    public void canGetProducts() {
        saveSellerToRepository();
        saveProductToRepository();
        ProductFilter productFilter = createEmptyFilter();

        ProductCollectionResponse productResponses = sellingService.getProducts(productFilter);

        assertThat(productResponses.products).hasSize(1);
    }

    @Test
    public void canGetProductsWhenNoProduct() {
        ProductFilter productFilter = createEmptyFilter();

        ProductCollectionResponse productResponses = sellingService.getProducts(productFilter);

        assertThat(productResponses.products).isEmpty();
    }

    @Test
    public void canCreateOfferWhenProductExists() {
        saveSellerToRepository();
        saveProductToRepository();
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();

        String id = sellingService.createOffer(BUYER_USERNAME, productStub.getId(), offerRequest);

        Offer offer = offerRepository.findById(id);
        assertThat(offer).isNotNull();
    }

    @Test
    public void createOfferThrowsWhenProductDoesNotExist() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();

        assertThrows(
                NotFoundException.class, () -> sellingService.createOffer(BUYER_USERNAME, SELLER_ID, offerRequest));
    }

    private ProductFilter createEmptyFilter() {
        return new ProductFilter(null, null, null, null, null);
    }

    private void saveSellerToRepository() {
        sellerStub = SellerTestUtils.createSeller();
        sellerRepository.save(sellerStub);
    }

    private void saveProductToRepository() {
        productStub = productMapper.requestToProduct(sellerStub.getId(), ProductTestUtils.createProductRequest());
        productRepository.save(productStub);
    }

    private void saveOfferToRepository() {
        offerStub =
                offerMapper.requestToOffer(productStub.getId(), BUYER_USERNAME, OfferTestUtils.createOfferRequest());
        offerRepository.save(offerStub);
    }

    protected abstract ISellerRepository createSellerRepository();

    protected abstract IProductRepository createProductRepository();

    protected abstract IOfferRepository createOfferRepository();
}
