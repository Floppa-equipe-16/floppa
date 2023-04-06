package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductCollectionResponse;
import ulaval.glo2003.domain.product.ProductFilter;
import ulaval.glo2003.utils.ProductTestUtils;
import ulaval.glo2003.utils.SellerTestUtils;

public class GetProductsFilterITest extends ApiTest {

    @BeforeEach
    public void setUp() throws Exception {
        String sellerId = sellingService.createSeller(SellerTestUtils.createSellerRequest());
        sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest());
        sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest2());
        sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest3());
        sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest4());
    }

    @Test
    public void getProductsWithoutFilter() {
        Response response = target("/products").request().get();
        ProductFilter filter = ProductTestUtils.createEmptyFilter();

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsWithSellerIdFilter() {
        Response response =
                target("/products").queryParam("sellerId", "test").request().get();
        ProductFilter filter = new ProductFilter("test", null, null, null, null);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsWithTitleFilter() {
        Response response =
                target("/products").queryParam("title", "Iphone").request().get();
        ProductFilter filter = new ProductFilter(null, "Iphone", null, null, null);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsWithCategoryFilter() {
        Response response = target("/products")
                .queryParam("category", "electronics")
                .request()
                .get();
        ProductFilter filter = new ProductFilter(null, null, "electronics", null, null);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsWithMinPriceFilter() {
        Response response =
                target("/products").queryParam("minPrice", 500d).request().get();
        ProductFilter filter = new ProductFilter(null, null, null, 500d, null);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsWithMaxPriceFilter() {
        Response response =
                target("/products").queryParam("maxPrice", 50d).request().get();
        ProductFilter filter = new ProductFilter(null, null, null, null, 50d);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsWithMixFilter1() {
        Response response = target("/products")
                .queryParam("minPrice", 1000d)
                .queryParam("maxPrice", 1200d)
                .request()
                .get();
        ProductFilter filter = new ProductFilter(null, null, null, 1000d, 1200d);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsWithMixFilter2() {
        Response response = target("/products")
                .queryParam("title", "iphone")
                .queryParam("minPrice", 1000d)
                .queryParam("maxPrice", 1200d)
                .request()
                .get();
        ProductFilter filter = new ProductFilter(null, "iphone", null, 1000d, 1200d);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    @Test
    public void getProductsMixWithFilter3() {
        Response response = target("/products")
                .queryParam("title", "prime")
                .queryParam("category", "sport")
                .queryParam("maxPrice", 10d)
                .request()
                .get();
        ProductFilter filter = new ProductFilter(null, "prime", null, null, 10d);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertCollectionEquals(response, filter);
    }

    private void assertCollectionEquals(Response response, ProductFilter filter) {
        ProductCollectionResponse collectionResponseServer = response.readEntity(ProductCollectionResponse.class);
        ProductCollectionResponse collectionResponseInternal = sellingService.getProducts(filter);

        assertThat(collectionResponseServer).isEqualTo(collectionResponseInternal);
    }
}
