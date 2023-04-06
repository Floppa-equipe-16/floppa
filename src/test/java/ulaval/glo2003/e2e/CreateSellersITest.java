package ulaval.glo2003.e2e;

import static com.google.common.truth.Truth.assertThat;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.utils.SellerTestUtils;

public class CreateSellersITest extends ApiTest {

    @Test
    public void canCreateSellers() {

        SellerRequest request = SellerTestUtils.createSellerRequest();

        Response response = createSeller(request);
        String location = response.getHeaderString("Location");

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(location).isNotNull();
        assertThat(location).isNotEmpty();
    }

    @Test
    public void failCreateSellersInvalidName() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.name = "";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateSellersInvalidBirthdateTooYoung() {

        SellerRequest request = SellerTestUtils.createSellerRequestInvalidBirthdateTooYoung();

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateSellersInvalidEmail() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.email = "";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateSellersInvalidPhoneNumber() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.phoneNumber = "123";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateSellersInvalidBio() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.bio = "";

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("INVALID_PARAMETER");
    }

    @Test
    public void failCreateSellersMissingName() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.name = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateSellersMissingBirthdate() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.birthdate = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateSellersMissingEmail() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.email = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateSellersMissingPhone() {

        SellerRequest request = SellerTestUtils.createSellerRequest();
        request.phoneNumber = null;

        Response response = createSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertMediaTypeIsJson(response);
        assertThat(getErrorCode(response)).isEqualTo("MISSING_PARAMETER");
    }

    @Test
    public void failCreateSellersMissingBio() {

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
