package ulaval.glo2003.api.seller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerTestUtils;

import static com.google.common.truth.Truth.assertThat;

public class SellerEqualsTest {
    private Seller seller;

    @BeforeEach
    public void setUp(){
        seller = SellerTestUtils.createSeller();
    }

    @Test
    public void sellerRequestEqualsToSeller() {
        SellerRequest request = SellerTestUtils.createSellerRequest();

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isTrue();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenNameDiff() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.name = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenEmailDiff() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.email = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenPhoneNumberDiff() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.phoneNumber = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenBioDiff() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.bio = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerRequestNotEqualsToSellerWhenBirthdateDiff() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.birthdate = "???";

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isFalse();
    }

    @Test
    public void sellerResponseEqualsToSeller() {
        SellerResponse response = SellerTestUtils.createSellerResponse();

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isTrue();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenNameDiff() {
        SellerResponse response = SellerTestUtils.createSellerResponse();
        response.name = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenIdDiff() {
        SellerResponse response = SellerTestUtils.createSellerResponse();
        response.id = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenCreateAtDiff() {
        SellerResponse response = SellerTestUtils.createSellerResponse();
        response.createdAt = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenBioDiff() {
        SellerResponse response = SellerTestUtils.createSellerResponse();
        response.bio = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenBirthdateDiff() {
        SellerResponse response = SellerTestUtils.createSellerResponse();
        response.birthdate = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenEmailDiff() {
        SellerResponse response = SellerTestUtils.createSellerResponse();
        response.email = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }

    @Test
    public void sellerResponseNotEqualsToSellerWhenPhoneNumberDiff() {
        SellerResponse response = SellerTestUtils.createSellerResponse();
        response.phoneNumber = "???";

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isFalse();
    }
}
