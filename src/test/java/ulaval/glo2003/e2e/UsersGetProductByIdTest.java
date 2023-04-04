package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.utils.ProductUtils;
import ulaval.glo2003.utils.SellerUtils;

public class UsersGetProductByIdTest extends ApiTestUtils {

    private String productId;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        String sellerId = sellingService.createSeller(SellerUtils.createSellerRequest());
        productId = sellingService.createProduct(sellerId, ProductUtils.createProductRequest());
    }

    @Test
    public void canUserGetProduct() {
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
    public void failUserGetProductInvalidId() {
        Response response = target("/products/{productId}")
                .resolveTemplate("productId", "not valid")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }
}
