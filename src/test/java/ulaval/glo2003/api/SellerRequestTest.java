package ulaval.glo2003.api;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.domain.exceptions.MissingParamException;

public class SellerRequestTest {

    private final SellerRequest sellerRequest = new SellerRequest();

    @BeforeEach
    public void prepareProductRequest() {
        sellerRequest.name = "Bob";
        sellerRequest.birthdate = "2000-01-01";
        sellerRequest.email = "Bob@bob.bob";
        sellerRequest.phoneNumber = "11234567890";
        sellerRequest.bio = "My name is Bob.";
    }

    @Test
    public void validateMethodThrowsWhenNullName() {
        MissingParamException missingParamException = new MissingParamException("name");
        sellerRequest.name = null;

        assertThrows(MissingParamException.class, sellerRequest::validateSellerNonNullParameters);
        MissingParamException thrownMissingName = assertThrows(
                MissingParamException.class,
                sellerRequest::validateSellerNonNullParameters,
                "Expected new `validateSellerNonNullParameters` to throw");
        assertThat(thrownMissingName.errorDescription.description)
                .isEqualTo(missingParamException.errorDescription.description);
    }

    @Test
    public void validateMethodThrowsWhenNullBirthdate() {
        MissingParamException missingParamException = new MissingParamException("birthdate");
        sellerRequest.birthdate = null;

        MissingParamException thrownMissingBirthdate = assertThrows(
                MissingParamException.class,
                sellerRequest::validateSellerNonNullParameters,
                "Expected new `validateSellerNonNullParameters` to throw");
        assertThat(thrownMissingBirthdate.errorDescription.description)
                .isEqualTo(missingParamException.errorDescription.description);
    }

    @Test
    public void validateMethodThrowsWhenNullEmail() {
        MissingParamException missingParamException = new MissingParamException("email");
        sellerRequest.email = null;

        MissingParamException thrownMissingEmail = assertThrows(
                MissingParamException.class,
                sellerRequest::validateSellerNonNullParameters,
                "Expected new `validateSellerNonNullParameters` to throw");
        assertThat(thrownMissingEmail.errorDescription.description)
                .isEqualTo(missingParamException.errorDescription.description);
    }

    @Test
    public void validateMethodThrowsWhenNullPhoneNumber() {
        MissingParamException missingParamException = new MissingParamException("phone number");
        sellerRequest.phoneNumber = null;

        MissingParamException thrownMissingPhoneNumber = assertThrows(
                MissingParamException.class,
                sellerRequest::validateSellerNonNullParameters,
                "Expected new `validateSellerNonNullParameters` to throw");
        assertThat(thrownMissingPhoneNumber.errorDescription.description)
                .isEqualTo(missingParamException.errorDescription.description);
    }

    @Test
    public void validateMethodThrowsWhenNullBio() {
        MissingParamException missingParamException = new MissingParamException("bio");
        sellerRequest.bio = null;

        MissingParamException thrownMissingBio = assertThrows(
                MissingParamException.class,
                sellerRequest::validateSellerNonNullParameters,
                "Expected new `validateSellerNonNullParameters` to throw");
        assertThat(thrownMissingBio.errorDescription.description)
                .isEqualTo(missingParamException.errorDescription.description);
    }
}
