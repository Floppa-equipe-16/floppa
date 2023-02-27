package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerTestUtils;

class SellerMapperTest {

    @Test
    void canMapRequestToSeller() {
        SellerRequest request = SellerTestUtils.createSellerRequest();

        Seller seller = SellerMapper.requestToSeller(request);

        assertThat(seller.getName()).isEqualTo(request.name);
        assertThat(seller.getBirthdate()).isEqualTo(request.birthdate);
        assertThat(seller.getEmail()).isEqualTo(request.email);
        assertThat(seller.getPhoneNumber()).isEqualTo(request.phoneNumber);
        assertThat(seller.getBio()).isEqualTo(request.bio);
    }

    @Test
    void canMapSellerToResponse() {
        Seller seller = SellerTestUtils.createSeller();

        SellerResponse response = SellerMapper.sellerToResponse(seller);

        assertThat(response.id).isEqualTo(seller.getId());
        assertThat(response.name).isEqualTo(seller.getName());
        assertThat(response.birthdate).isEqualTo(seller.getBirthdate());
        assertThat(response.email).isEqualTo(seller.getEmail());
        assertThat(response.phoneNumber).isEqualTo(seller.getPhoneNumber());
        assertThat(response.bio).isEqualTo(seller.getBio());
    }
}
