package ulaval.glo2003.service.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ulaval.glo2003.api.seller.SellerCollectionResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerFactory;
import ulaval.glo2003.service.ProductMapper;
import ulaval.glo2003.service.SellerMapper;
import ulaval.glo2003.utils.SellerTestUtils;
import ulaval.glo2003.utils.equals.SellerEquals;

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
    public void canMapRequestToSeller() {
        SellerRequest request = SellerTestUtils.createSellerRequest();
        doReturn(SellerTestUtils.createSellerStub())
                .when(factory)
                .createSeller(request.name, request.birthdate, request.email, request.phoneNumber, request.bio);

        Seller seller = mapper.requestToSeller(request);

        assertThat(SellerEquals.sellerRequestEqualsSeller(request, seller)).isTrue();
    }

    @Test
    public void canMapSellerToResponse() {
        Seller seller = SellerTestUtils.createSellerStub();

        SellerResponse response = mapper.sellerToResponse(seller);

        assertThat(SellerEquals.sellerResponseEqualsSeller(response, seller)).isTrue();
    }

    @Test
    public void canMapSellersToCollectionResponse() {
        Seller seller1 = SellerTestUtils.createSellerStub();
        Seller seller2 = SellerTestUtils.createSellerStub();
        Seller seller3 = SellerTestUtils.createSellerStub();
        List<Seller> sellers = Arrays.asList(seller1, seller2, seller3);

        SellerCollectionResponse response = mapper.sellersToRankedCollectionResponse(sellers);

        assertThat(SellerEquals.sellerCollectionResponseEqualsSellers(response, sellers))
                .isTrue();
    }
}
