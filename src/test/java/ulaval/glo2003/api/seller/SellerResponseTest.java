package ulaval.glo2003.api.seller;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.utils.SellerUtils;

public class SellerResponseTest {

    private SellerResponse sellerResponse;

    @BeforeEach
    public void setUp() {
        sellerResponse = SellerUtils.createSellerResponse();
    }

    @Test
    public void sellerResponseEqualsToHimSelf() {
        assertThat(sellerResponse).isEqualTo(sellerResponse);
    }

    @Test
    public void sellerResponseEqualsToSellerResponse() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();

        assertThat(sellerResponse).isEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenNameDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();
        sellerResponse.name = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenIdDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();
        sellerResponse.id = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenPhoneNumberDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();
        sellerResponse.phoneNumber = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenBioDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();
        sellerResponse.bio = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenEamilDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();
        sellerResponse.email = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenBirthdateDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();
        sellerResponse.birthdate = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenCreateAtDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();
        sellerResponse.createdAt = "???";

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }

    @Test
    public void sellerResponseNotEqualsToSellerResponseWhenProductsDiff() {
        SellerResponse newSellerResponse = SellerUtils.createSellerResponse();

        sellerResponse.products = new ArrayList<ProductResponse>();

        assertThat(sellerResponse).isNotEqualTo(newSellerResponse);
    }
}
