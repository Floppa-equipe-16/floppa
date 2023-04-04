package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.utils.OfferUtils;
import ulaval.glo2003.utils.ProductUtils;
import ulaval.glo2003.utils.SellerUtils;

public class UsersCreateOffersTest extends ApiTestUtils {

    private String productId;
    private static final String BUYER_USERNAME = "Bobi";

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        String sellerId = sellingService.createSeller(SellerUtils.createSellerRequest());
        productId = sellingService.createProduct(sellerId, ProductUtils.createProductRequest());
    }

    @Test
    public void canUserCreateOfferOnProduct() {
        OfferRequest offerRequest = OfferUtils.createOfferRequest();

        Response response = target("/products/{productId}/offers")
                .resolveTemplate("productId", productId)
                .request()
                .header("X-Buyer-Username", BUYER_USERNAME)
                .post(Entity.entity(offerRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void failUserCreateOfferOnProductMissingUsername() {
        OfferRequest offerRequest = OfferUtils.createOfferRequest();

        Response response = target("/products/{productId}/offers")
                .resolveTemplate("productId", productId)
                .request()
                .post(Entity.entity(offerRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateOfferOnProductInvalidProductId() {
        OfferRequest offerRequest = OfferUtils.createOfferRequest();

        Response response = target("/products/{productId}/offers")
                .resolveTemplate("productId", "invalid")
                .request()
                .header("X-Buyer-Username", BUYER_USERNAME)
                .post(Entity.entity(offerRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }

    @Test
    public void failUserCreateOfferOnProductInvalidUsername() {
        OfferRequest offerRequest = OfferUtils.createOfferRequest();

        Response response = target("/products/{productId}/offers")
                .resolveTemplate("productId", productId)
                .request()
                .header("X-Buyer-Username", "")
                .post(Entity.entity(offerRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }
}
