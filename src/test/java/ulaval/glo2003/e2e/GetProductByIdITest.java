package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.utils.ProductTestUtils;
import ulaval.glo2003.utils.SellerTestUtils;

public class GetProductByIdITest extends ApiTest {

    private String productId;

    @BeforeEach
    public void setUp() throws Exception {
        String sellerId = sellingService.createSeller(SellerTestUtils.createSellerRequest());
        productId = sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest());
    }

    @Test
    public void canGetProduct() {
        Response response = target("/products/{productId}")
                .resolveTemplate("productId", productId)
                .request()
                .get();

        ProductResponse productResponseServer = response.readEntity(ProductResponse.class);
        ProductResponse productResponseInternal = sellingService.getProduct(productId);

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);
        assertThat(productResponseServer).isEqualTo(productResponseInternal);
    }

    @Test
    public void failGetProductInvalidId() {
        Response response = target("/products/{productId}")
                .resolveTemplate("productId", "not valid")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }
}
