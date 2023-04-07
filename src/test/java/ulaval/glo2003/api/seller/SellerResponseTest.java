package ulaval.glo2003.api.seller;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.utils.SellerTestUtils;

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
    public void sellerResponseNotEqualsToSellerResponseWhenNameDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.name = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenIdDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.id = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenPhoneNumberDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.phoneNumber = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenBioDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.bio = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenEamilDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.email = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenBirthdateDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.birthdate = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenCreateAtDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();
        sellerResponse.createdAt = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenProductsDiff() {
        SellerResponse newSellerResponse = SellerTestUtils.createSellerResponse();

        sellerResponse.products = new ArrayList<>();

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }
}
