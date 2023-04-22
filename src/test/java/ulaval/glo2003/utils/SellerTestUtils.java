package ulaval.glo2003.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import ulaval.glo2003.api.product.ProductResponse;
import ulaval.glo2003.api.seller.SellerRequest;
import ulaval.glo2003.api.seller.SellerResponse;
import ulaval.glo2003.domain.seller.Seller;

public class SellerTestUtils {
    private static final String ID = "1";
    private static final String ID_2 = "2";
    private static final String NAME = "Bob";
    private static final String BIRTHDATE = "2000-01-01";
    private static final String EMAIL = "Bob@bob.bob";
    private static final String PHONE_NUMBER = "11234567890";
    private static final String BIO = "My name is Bob.";
    private static final String CREATED_AT = Instant.MAX.toString();

    private static final double SCORE = 0;

    public static Seller createSeller() {
        return new Seller(ID, NAME, CREATED_AT, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO, SCORE);
    }

    public static Seller createSeller2() {
        return new Seller(ID_2, NAME, CREATED_AT, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO, SCORE);
    }

    public static ProductResponse.SellerInfo createSellerInfo() {
        ProductResponse.SellerInfo info = new ProductResponse.SellerInfo();
        info.name = NAME;
        info.id = ID;

        return info;
    }

    public static Seller createSellerStub() {
        Seller seller = mock(Seller.class);
        when(seller.getId()).thenReturn(ID);
        when(seller.getName()).thenReturn(NAME);
        when(seller.getCreatedAt()).thenReturn(CREATED_AT);
        when(seller.getBirthdate()).thenReturn(BIRTHDATE);
        when(seller.getEmail()).thenReturn(EMAIL);
        when(seller.getPhoneNumber()).thenReturn(PHONE_NUMBER);
        when(seller.getBio()).thenReturn(BIO);
        return seller;
    }

    public static SellerRequest createSellerRequest() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    public static SellerResponse createSellerResponse() {
        SellerResponse response = new SellerResponse();
        response.name = NAME;
        response.birthdate = BIRTHDATE;
        response.email = EMAIL;
        response.phoneNumber = PHONE_NUMBER;
        response.bio = BIO;
        response.id = ID;
        response.createdAt = CREATED_AT;
        response.products = new ArrayList<>();
        response.products.add(ProductTestUtils.createProductResponse());

        return response;
    }

    public static SellerRequest createSellerRequestInvalidBirthdateTooYoung() {
        SellerRequest request = createSellerRequest();
        request.birthdate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        return request;
    }
}
