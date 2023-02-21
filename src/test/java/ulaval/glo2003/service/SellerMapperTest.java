package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;

class SellerMapperTest {
    private static final String NAME = "Alice";
    private static final String BIRTHDATE = "2000-01-01";
    private static final String EMAIL = "Alice@floppa.com";
    private static final String PHONE_NUMBER = "14181234567";
    private static final String BIO = "My name is Alice!";

    @Test
    void canMapRequestToSeller() {
        SellerRequest request = createRequest();

        Seller seller = SellerMapper.requestToSeller(request);

        assertThat(seller.getName()).isEqualTo(request.name);
        assertThat(seller.getBirthdate()).isEqualTo(request.birthdate);
        assertThat(seller.getEmail()).isEqualTo(request.email);
        assertThat(seller.getPhoneNumber()).isEqualTo(request.phoneNumber);
        assertThat(seller.getBio()).isEqualTo(request.bio);
    }

    private SellerRequest createRequest() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    @Test
    void canMapSellerToResponse() {
        Seller seller = new Seller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);

        SellerResponse response = SellerMapper.sellerToResponse(seller);

        assertThat(response.id).isEqualTo(seller.getId());
        assertThat(response.name).isEqualTo(seller.getName());
        assertThat(response.birthdate).isEqualTo(seller.getBirthdate());
        assertThat(response.email).isEqualTo(seller.getEmail());
        assertThat(response.phoneNumber).isEqualTo(seller.getPhoneNumber());
        assertThat(response.bio).isEqualTo(seller.getBio());
    }
}
