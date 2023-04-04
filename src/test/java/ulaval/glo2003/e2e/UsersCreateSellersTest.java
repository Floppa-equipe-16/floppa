package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.utils.SellerUtils;

public class UsersCreateSellersTest extends ApiTestUtils {

    @Test
    public void canUserCreateSellers() {

        SellerRequest request = SellerUtils.createSellerRequest();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void failUserCreateSellersInvalidName() {

        SellerRequest request = SellerUtils.createSellerRequestInvalidName();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidBirthdateTooYoung() {

        SellerRequest request = SellerUtils.createSellerRequestInvalidbirthdateTooYoung();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidEmail() {

        SellerRequest request = SellerUtils.createSellerRequestInvalidEmail();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidPhoneNumber() {

        SellerRequest request = SellerUtils.createSellerRequestInvalidPhone();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidBio() {

        SellerRequest request = SellerUtils.createSellerRequestInvalidBio();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingName() {

        SellerRequest request = SellerUtils.createSellerRequestMissingName();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingBirthdate() {

        SellerRequest request = SellerUtils.createSellerRequestMissingBirthdate();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingEmail() {

        SellerRequest request = SellerUtils.createSellerRequestMissingEmail();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingPhone() {

        SellerRequest request = SellerUtils.createSellerRequestMissingPhone();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingBio() {

        SellerRequest request = SellerUtils.createSellerRequestMissingBio();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    private Response createSeller(SellerRequest request) {
        return target("/sellers").request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
    }
}
