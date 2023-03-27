package ulaval.glo2003.api.seller;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerTestUtils;

public class SellerResponseTest {

    private SellerResponse sellerResponse;

    @BeforeEach
    public void setUp() {
        sellerResponse = SellerTestUtils.createSellerResponse();
    }

    @Test
    public void sellerResponseEqualsToHimSelf() {
        assertThat(sellerResponse).isEqualTo(sellerResponse);
    }

    @Test
    public void sellerResponseEqualsToSellerResponse() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();

        assertThat(sellerResponse).isEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponse() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.name = "new name";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseEqualsToSeller() {
        Seller seller = SellerTestUtils.createSeller();

        assertThat(sellerResponse).isEqualTo(seller);
    }

    @Test
    public void sellerResponseNotEqualsToSeller() {
        Seller seller = SellerTestUtils.createSeller();
        sellerResponse.name = "new name";

        assertThat(sellerResponse).isNotEqualTo(seller);
    }
}
