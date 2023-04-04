package ulaval.glo2003.utils.equals;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.utils.SellerUtils;

public class SellerEqualsTest {
    private Seller seller;

    @BeforeEach
    public void setUp() {
        seller = SellerUtils.createSeller();
    }

    @Test
    public void sellerRequestEqualsToSeller() {
        SellerRequest request = SellerUtils.createSellerRequest();

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isTrue();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenNameDiff() {
        SellerRequest request = SellerUtils.createSellerRequest();
        request.name = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenEmailDiff() {
        SellerRequest request = SellerUtils.createSellerRequest();
        request.email = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenPhoneNumberDiff() {
        SellerRequest request = SellerUtils.createSellerRequest();
        request.phoneNumber = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenBioDiff() {
        SellerRequest request = SellerUtils.createSellerRequest();
        request.bio = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenBirthdateDiff() {
        SellerRequest request = SellerUtils.createSellerRequest();
        request.birthdate = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerResponseEqualsToSeller() {
        SellerResponse response = SellerUtils.createSellerResponse();

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isTrue();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenNameDiff() {
        SellerResponse response = SellerUtils.createSellerResponse();
        response.name = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenIdDiff() {
        SellerResponse response = SellerUtils.createSellerResponse();
        response.id = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenCreateAtDiff() {
        SellerResponse response = SellerUtils.createSellerResponse();
        response.createdAt = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenBioDiff() {
        SellerResponse response = SellerUtils.createSellerResponse();
        response.bio = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenBirthdateDiff() {
        SellerResponse response = SellerUtils.createSellerResponse();
        response.birthdate = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenEmailDiff() {
        SellerResponse response = SellerUtils.createSellerResponse();
        response.email = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenPhoneNumberDiff() {
        SellerResponse response = SellerUtils.createSellerResponse();
        response.phoneNumber = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }
}
