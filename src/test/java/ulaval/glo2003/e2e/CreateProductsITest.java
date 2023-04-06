package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.utils.ProductTestUtils;
import ulaval.glo2003.utils.SellerTestUtils;

public class CreateProductsITest extends ApiTest {

    private String sellerId;

    @BeforeEach
    public void setUp() throws Exception {
        sellerId = sellingService.createSeller(SellerTestUtils.createSellerRequest());
    }

    @Test
    public void canCreateProducts() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();

        Response response = target("/products")
                .request()
                .header("X-Seller-Id", sellerId)
                .post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(location).isNotNull();
        assertThat(location).isNotEmpty();
    }

    @Test
    public void failCreateProductsMissingSellerIdHeader() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();

        Response response =
                target("/products").request().post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateProductsInvalidSellerId() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();

        Response response = target("/products")
                .request()
                .header("X-Seller-Id", "")
                .post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateProductsSellerIdDoesntExist() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();

        Response response = target("/products")
                .request()
                .header("X-Seller-Id", "Not an id")
                .post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void failCreateProductsInvalidTitle() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.title = "";

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateProductsInvalidCategory() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.category = "";

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateProductsInvalidDescription() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.description = "";

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateProductsInvalidSuggestedPrice() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.suggestedPrice = -1d;

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateProductsMissingTitle() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.title = null;

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateProductsMissingCategory() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.category = null;

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateProductsMissingDescription() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.description = null;

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateProductsMissingSuggestedPrice() {

        ProductRequest productRequest = ProductTestUtils.createProductRequest();
        productRequest.suggestedPrice = null;

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    private Response createProduct(ProductRequest request) {
        return target("/products")
                .request()
                .header("X-Seller-Id", sellerId)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));
    }
}
