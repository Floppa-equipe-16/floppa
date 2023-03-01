package ulaval.glo2003.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerFactory;
import ulaval.glo2003.domain.seller.SellerTestUtils;

class SellerMapperTest {

    @Mock
    private ProductMapper productMapper = mock(ProductMapper.class);
    @Mock
    private SellerFactory factory = mock(SellerFactory.class);
    private SellerMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new SellerMapper(factory, productMapper);
    }

    @Test
    void canMapRequestToSeller() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        doReturn(SellerTestUtils.createSeller()).when(factory).createSeller(request.name, request.birthdate, request.email, request.phoneNumber, request.bio);

        Seller seller = mapper.requestToSeller(request);

        assertThat(seller.getName()).isEqualTo(request.name);
        assertThat(seller.getBirthdate()).isEqualTo(request.birthdate);
        assertThat(seller.getEmail()).isEqualTo(request.email);
        assertThat(seller.getPhoneNumber()).isEqualTo(request.phoneNumber);
        assertThat(seller.getBio()).isEqualTo(request.bio);
    }

    @Test
    void canMapSellerToResponse() {
        Seller seller = SellerTestUtils.createSeller();

        SellerResponse response = mapper.sellerToResponse(seller);

        assertThat(response.id).isEqualTo(seller.getId());
        assertThat(response.name).isEqualTo(seller.getName());
        assertThat(response.birthdate).isEqualTo(seller.getBirthdate());
        assertThat(response.email).isEqualTo(seller.getEmail());
        assertThat(response.phoneNumber).isEqualTo(seller.getPhoneNumber());
        assertThat(response.bio).isEqualTo(seller.getBio());
    }
}
