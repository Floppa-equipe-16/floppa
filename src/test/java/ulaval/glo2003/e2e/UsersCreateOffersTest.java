package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.offer.OfferRequest;
import ulaval.glo2003.utils.OfferTestUtils;
import ulaval.glo2003.utils.ProductTestUtils;
import ulaval.glo2003.utils.SellerTestUtils;

public class UsersCreateOffersTest extends ApiTest {

    private String productId;
    private static final String BUYER_USERNAME = "Bobi";

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        String sellerId = sellingService.createSeller(SellerTestUtils.createSellerRequest());
        productId = sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest());
    }

    @Test
    public void canUserCreateOfferOnProduct() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();

        Response response = createOffer(offerRequest);

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void failUserCreateOfferOnProductMissingUsername() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();

        Response response = target("/products/{productId}/offers")
                .resolveTemplate("productId", productId)
                .request()
                .post(Entity.entity(offerRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateOfferOnProductInvalidProductId() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();

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
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();

        Response response = target("/products/{productId}/offers")
                .resolveTemplate("productId", productId)
                .request()
                .header("X-Buyer-Username", "")
                .post(Entity.entity(offerRequest, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateOfferOnProductInvalidAmount() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();
        offerRequest.amount = -1d;

        Response response = createOffer(offerRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateOfferOnProductInvalidMessage() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();
        offerRequest.message = "not long enough";

        Response response = createOffer(offerRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateOfferOnProductMissingAmount() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();
        offerRequest.amount = null;

        Response response = createOffer(offerRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateOfferOnProductMissingMessage() {
        OfferRequest offerRequest = OfferTestUtils.createOfferRequest();
        offerRequest.message = null;

        Response response = createOffer(offerRequest);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    private Response createOffer(OfferRequest request) {
        return target("/products/{productId}/offers")
                .resolveTemplate("productId", productId)
                .request()
                .header("X-Buyer-Username", BUYER_USERNAME)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));
    }
}
