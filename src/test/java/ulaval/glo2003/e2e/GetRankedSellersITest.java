package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerCollectionResponse;
import ulaval.glo2003.utils.OfferTestUtils;
import ulaval.glo2003.utils.ProductTestUtils;
import ulaval.glo2003.utils.SellerTestUtils;

public class GetRankedSellersITest extends ApiTest {

    @BeforeEach
    public void setUp() throws Exception {
        String sellerId = sellingService.createSeller(SellerTestUtils.createSellerRequest());
        String productId = sellingService.createProduct(sellerId, ProductTestUtils.createProductRequest());
        sellingService.createOffer("buyerName", productId, OfferTestUtils.createOfferRequest());
    }

    @Test
    public void canGetRankedSellers() {
        Response response = target("/sellers").request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertMediaTypeIsJson(response);

        SellerCollectionResponse responseServer = response.readEntity(SellerCollectionResponse.class);
        SellerCollectionResponse responseInternal = sellingService.getRankedSellers(1);
        assertThat(responseInternal).isEqualTo(responseServer);
    }
}
