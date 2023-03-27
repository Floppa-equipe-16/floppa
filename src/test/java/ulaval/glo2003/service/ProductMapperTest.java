package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
import ulaval.glo2003.domain.product.ProductFactory;
import ulaval.glo2003.domain.product.ProductTestUtils;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerTestUtils;

class ProductMapperTest {

    @Mock
    private OfferMapper offerMapper = mock(OfferMapper.class);

    @Mock
    private ProductFactory factory = mock(ProductFactory.class);

    @Mock
    private Product productStub = ProductTestUtils.createProductStub();

    private ProductMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ProductMapper(factory, offerMapper);
    }

    @Test
    public void canMapRequestToProduct() {
        doReturn(productStub)
                .when(factory)
                .createProduct(anyString(), anyString(), anyString(), anyDouble(), anyString());

        ProductRequest request = ProductTestUtils.createProductRequest();

        Product product = mapper.requestToProduct(ProductTestUtils.SELLER_ID, request);

        assertThat(request).isEqualTo(product);
    }

    @Test
    public void canMapProductToResponse() {
        doReturn(new OfferCollectionResponse()).when(offerMapper).offersToCompleteCollectionResponse(any());

        ProductResponse response = mapper.productToResponse(productStub);

        assertThat(response).isEqualTo(productStub);
        assertThat(response.offers).isNotNull();
        assertThat(response.seller).isNull();
    }

    @Test
    public void canMapProductsToCollectionResponse() {
        doReturn(new OfferCollectionResponse()).when(offerMapper).offersToCompleteCollectionResponse(any());

        ProductCollectionResponse response =
                mapper.productsToCollectionResponse(List.of(mapper.productToResponse(productStub)));

        assertThat(response.products.get(0)).isEqualTo(productStub);
        assertThat(response.products.get(0).offers).isNotNull();
        assertThat(response.products.get(0).seller).isNull();
    }

    @Test
    public void canMapProductToResponseWithSeller() {
        Seller seller = SellerTestUtils.createSellerStub();

        ProductResponse response = mapper.productToResponseWithSeller(productStub, seller);

        assertThat(response).isEqualTo(productStub);
        assertThat(response.seller).isNotNull();
    }

    @Test
    public void canMapProductsMapToResponsesList() {
        Map<String, Product> products = new HashMap<>();
        products.put("1", productStub);
        products.put("2", productStub);

        List<ProductResponse> responses = mapper.productsMapToResponsesList(products);

        assertThat(responses.size()).isEqualTo(2);
    }
}
