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

public class SellerUtils {
    private static final String ID = "1";
    private static final String NAME = "Bob";
    private static final String BIRTHDATE = "2000-01-01";
    private static final String EMAIL = "Bob@bob.bob";
    private static final String PHONE_NUMBER = "11234567890";
    private static final String BIO = "My name is Bob.";
    private static final String CREATED_AT = Instant.MAX.toString();

    public static Seller createSeller() {
        return new Seller(ID, NAME, CREATED_AT, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);
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
        response.products = new ArrayList<ProductResponse>();
        response.products.add(ProductUtils.createProductResponse());

        return response;
    }

    public static SellerRequest createSellerRequestInvalidName() {
        SellerRequest request = new SellerRequest();
        request.name = "";
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestInvalidbirthdateTooYoung() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestInvalidEmail() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = "";
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestInvalidPhone() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = "132";
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestInvalidBio() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = "";

        return request;
    }

    public static SellerRequest createSellerRequestMissingName() {
        SellerRequest request = new SellerRequest();
        request.name = null;
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestMissingBirthdate() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = null;
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestMissingEmail() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = null;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestMissingPhone() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = null;
        request.bio = BIO;

        return request;
    }

    public static SellerRequest createSellerRequestMissingBio() {
        SellerRequest request = new SellerRequest();
        request.name = NAME;
        request.birthdate = BIRTHDATE;
        request.email = EMAIL;
        request.phoneNumber = PHONE_NUMBER;
        request.bio = null;

        return request;
    }
}
