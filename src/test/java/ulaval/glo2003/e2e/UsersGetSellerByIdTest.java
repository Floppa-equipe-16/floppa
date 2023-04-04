package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.utils.OfferUtils;
import ulaval.glo2003.utils.ProductUtils;
import ulaval.glo2003.utils.SellerUtils;

public class UsersGetSellerByIdTest extends ApiTestUtils {

    private String sellerId;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        sellerId = sellingService.createSeller(SellerUtils.createSellerRequest());
        String productId = sellingService.createProduct(sellerId, ProductUtils.createProductRequest());
        sellingService.createOffer("buyerName", productId, OfferUtils.createOfferRequest());
    }

    @Test
    public void canUserGetSellersById() {
        Response response = target("/sellers/{sellerId}")
                .resolveTemplate("sellerId", sellerId)
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);

        SellerResponse sellerResponseServer = response.readEntity(SellerResponse.class);
        SellerResponse sellerResponseInternal = sellingService.getSeller(sellerId);
        assertThat(sellerResponseInternal).isEqualTo(sellerResponseServer);
    }

    @Test
    public void failUserGetSellersByInvalidId() {
        Response response = target("/sellers/{sellerId}")
                .resolveTemplate("sellerId", "do_not_exist")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(404);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("ITEM_NOT_FOUND");
    }
}
