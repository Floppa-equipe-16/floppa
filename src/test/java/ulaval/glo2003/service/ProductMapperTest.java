package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.api.offer.OfferCollectionResponse;
import ulaval.glo2003.api.product.ProductCollectionResponse;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductCategory;
import ulaval.glo2003.domain.product.ProductFactory;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerTestUtils;

class ProductMapperTest {
    private static final String ID = "1";
    private static final String SELLER_ID = "SELLER";
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Good description";
    private static final double SUGGESTED_PRICE = 20d;
    private static final String CATEGORY = ProductCategory.other.toString();

    @Mock
    private OfferMapper offerMapper = mock(OfferMapper.class);

    @Mock
    private ProductFactory factory = mock(ProductFactory.class);

    private ProductMapper mapper;
    private Product product;

    @BeforeEach
    public void setUp() {
        mapper = new ProductMapper(factory, offerMapper);

        product = new Product(ID, SELLER_ID, TITLE, Instant.MAX.toString(), DESCRIPTION, SUGGESTED_PRICE, CATEGORY);
    }

    @Test
    public void canMapRequestToProduct() {
        doReturn(product).when(factory).createProduct(SELLER_ID, TITLE, DESCRIPTION, SUGGESTED_PRICE, CATEGORY);
        ProductRequest request = createRequest();

        Product product = mapper.requestToProduct(SELLER_ID, request);

        assertThat(product.getTitle()).isEqualTo(request.title);
        assertThat(product.getDescription()).isEqualTo(request.description);
        assertThat(product.getCategory()).isEqualTo(request.category);
        assertThat(product.getSuggestedPrice()).isEqualTo(request.suggestedPrice);
    }

    private ProductRequest createRequest() {
        ProductRequest request = new ProductRequest();
        request.title = TITLE;
        request.description = DESCRIPTION;
        request.category = CATEGORY;
        request.suggestedPrice = SUGGESTED_PRICE;
        return request;
    }

    @Test
    public void canMapProductToResponse() {
        doReturn(new OfferCollectionResponse()).when(offerMapper).offersToCompleteCollectionResponse(any());

        ProductResponse response = mapper.productToResponse(product);

        assertFieldsAreEqual(response, product);
        assertThat(response.offers).isNotNull();
        assertThat(response.seller).isNull();
    }

    @Test
    public void canMapProductsToCollectionResponse() {
        doReturn(new OfferCollectionResponse()).when(offerMapper).offersToCompleteCollectionResponse(any());

        ProductCollectionResponse response =
                mapper.productsToCollectionResponse(List.of(mapper.productToResponse(product)));

        assertFieldsAreEqual(response.products.get(0), product);
        assertThat(response.products.get(0).offers).isNotNull();
        assertThat(response.products.get(0).seller).isNull();
    }

    @Test
    public void canMapProductToResponseWithSeller() {
        Seller seller = SellerTestUtils.createSeller();

        ProductResponse response = mapper.productToResponseWithSeller(product, seller);

        assertFieldsAreEqual(response, product);
        assertThat(response.seller).isNotNull();
    }

    private void assertFieldsAreEqual(ProductResponse response, Product product) {
        assertThat(response.id).isEqualTo(product.getId());
        assertThat(response.createdAt).isEqualTo(product.getCreatedAt());
        assertThat(response.title).isEqualTo(product.getTitle());
        assertThat(response.description).isEqualTo(product.getDescription());
        assertThat(response.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(response.category).isEqualTo(product.getCategory());
    }

    @Test
    public void canMapProductsMapToResponsesList() {
        Map<String, Product> products = new HashMap<>();
        products.put("1", product);
        products.put("2", product);

        List<ProductResponse> responses = mapper.productsMapToResponsesList(products);

        assertThat(responses.size()).isEqualTo(2);
    }
}
