package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.utils.SellerTestUtils;

public class UsersCreateSellersTest extends ApiTest {

    @Test
    public void canUserCreateSellers() {

        SellerRequest request = SellerTestUtils.createSellerRequest();

        Response response = createSeller(request);
        String location = response.getHeaderString("Location");

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(location).isNotNull();
        assertThat(location).isNotEmpty();
    }

    @Test
    public void failUserCreateSellersInvalidName() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.name = "";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidBirthdateTooYoung() {

        SellerRequest request = SellerTestUtils.createSellerRequestInvalidBirthdateTooYoung();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidEmail() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.email = "";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidPhoneNumber() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.phoneNumber = "123";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersInvalidBio() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.bio = "";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingName() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.name = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingBirthdate() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.birthdate = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingEmail() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.email = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingPhone() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.phoneNumber = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failUserCreateSellersMissingBio() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.bio = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    private Response createSeller(SellerRequest request) {
        return target("/sellers").request().post(Entity.entity(request, MediaType.APPLICATION_JSON));
    }
}
