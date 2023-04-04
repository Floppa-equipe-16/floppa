package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductRequest;
import ulaval.glo2003.utils.ProductUtils;
import ulaval.glo2003.utils.SellerUtils;

public class UsersCreateProductsTest extends ApiTestUtils {

    private String sellerId;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        sellerId = sellingService.createSeller(SellerUtils.createSellerRequest());
    }

    @Test
    public void canUserCreateProducts() {

        ProductRequest productRequest = ProductUtils.createProductRequest();

        Response response = target("/products")
                .request()
                .header("X-Seller-Id", sellerId)
                .post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void failUserCreateProductsMissingSellerIdHeader() {

        ProductRequest productRequest = ProductUtils.createProductRequest();

        Response response =
                target("/products").request().post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateProductsInvalidSellerId() {

        ProductRequest productRequest = ProductUtils.createProductRequest();

        Response response = target("/products")
                .request()
                .header("X-Seller-Id", "")
                .post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateProductsSellerIdDoesntExist() {

        ProductRequest productRequest = ProductUtils.createProductRequest();

        Response response = target("/products")
                .request()
                .header("X-Seller-Id", "Not an id")
                .post(Entity.entity(productRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void failUserCreateProductsInvalidTitle() {

        ProductRequest productRequest = ProductUtils.createProductRequestInvalidTitle();

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateProductsInvalidCategory() {

        ProductRequest productRequest = ProductUtils.createProductRequestInvalidCategory();

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateProductsInvalidDescription() {

        ProductRequest productRequest = ProductUtils.createProductRequestInvalidDescription();

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateProductsInvalidSuggestedPrice() {

        ProductRequest productRequest = ProductUtils.createProductRequestInvalidSuggestedPrice();

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateProductsMissingTitle() {

        ProductRequest productRequest = ProductUtils.createProductRequestMissingTitle();

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateProductsMissingCategory() {

        ProductRequest productRequest = ProductUtils.createProductRequestMissingCategory();

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateProductsMissingDescription() {

        ProductRequest productRequest = ProductUtils.createProductRequestMissingDescription();

        Response response = createProduct(productRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateProductsMissingSuggestedPrice() {

        ProductRequest productRequest = ProductUtils.createProductRequestMissingPrice();

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
