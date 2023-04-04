package ulaval.glo2003.api.seller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.utils.SellerTestUtils;

public class SellerRequestTest {
    private SellerRequest sellerRequest;

    @BeforeEach
    public void setUp() {
        sellerRequest = SellerTestUtils.createSellerRequest();
    }

    @Test
    public void canValidateValidRequest() {
        assertDoesNotThrow(sellerRequest::validate);
    }

    @Test
    public void validateThrowsWhenNameIsNull() {
        sellerRequest.name = null;

        MissingParamException thrownMissingName = assertThrows(MissingParamException.class, sellerRequest::validate);
        assertThat(thrownMissingName.errorDescription.description)
                .ignoringCase()
                .contains("name");
    }

    @Test
    public void validateThrowsWhenBirthdateIsNull() {
        sellerRequest.birthdate = null;

        MissingParamException thrownMissingBirthdate =
                assertThrows(MissingParamException.class, sellerRequest::validate);
        assertThat(thrownMissingBirthdate.errorDescription.description)
                .ignoringCase()
                .contains("birthdate");
    }

    @Test
    public void validateThrowsWhenEmailIsNull() {
        sellerRequest.email = null;

        MissingParamException thrownMissingEmail = assertThrows(MissingParamException.class, sellerRequest::validate);
        assertThat(thrownMissingEmail.errorDescription.description)
                .ignoringCase()
                .contains("email");
    }

    @Test
    public void validateThrowsWhenPhoneNumberIsNull() {
        sellerRequest.phoneNumber = null;

        MissingParamException thrownMissingPhoneNumber =
                assertThrows(MissingParamException.class, sellerRequest::validate);
        assertThat(thrownMissingPhoneNumber.errorDescription.description)
                .ignoringCase()
                .contains("phone number");
    }

    @Test
    public void validateThrowsWhenBioIsNull() {
        sellerRequest.bio = null;

        MissingParamException thrownMissingBio = assertThrows(MissingParamException.class, sellerRequest::validate);
        assertThat(thrownMissingBio.errorDescription.description).ignoringCase().contains("bio");
    }

    @Test
    public void sellerRequestEqualsToHimSelf() {
        assertThat(sellerRequest).isEqualTo(sellerRequest);
    }

    @Test
    public void sellerRequestEqualsToSellerRequest() {
        assertThat(sellerRequest).isEqualTo(SellerTestUtils.createSellerRequest());
    }

    @Test
    public void sellerRequestNotEqualsToSellerRequestWhenNameDiff() {
        sellerRequest.name = "???";

        assertThat(sellerRequest).isNotEqualTo(SellerTestUtils.createSellerRequest());
    }

    @Test
    public void sellerRequestNotEqualsToSellerRequestWhenBirthdateDiff() {
        sellerRequest.birthdate = "???";

        assertThat(sellerRequest).isNotEqualTo(SellerTestUtils.createSellerRequest());
    }

    @Test
    public void sellerRequestNotEqualsToSellerRequestWhenEmailDiff() {
        sellerRequest.email = "???";

        assertThat(sellerRequest).isNotEqualTo(SellerTestUtils.createSellerRequest());
    }

    @Test
    public void sellerRequestNotEqualsToSellerRequestWhenPhoneNumberDiff() {
        sellerRequest.phoneNumber = "???";

        assertThat(sellerRequest).isNotEqualTo(SellerTestUtils.createSellerRequest());
    }

    @Test
    public void sellerRequestNotEqualsToSellerRequestWhenBioDiff() {
        sellerRequest.bio = "???";

        assertThat(sellerRequest).isNotEqualTo(SellerTestUtils.createSellerRequest());
    }
}
