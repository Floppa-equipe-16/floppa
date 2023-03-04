package ulaval.glo2003.domain.seller;

import java.time.Instant;
import ulaval.glo2003.api.seller.SellerRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SellerTestUtils {
    private static final String ID = "1";
    private static final String NAME = "Bob";
    private static final String BIRTHDATE = "2000-01-01";
    private static final String EMAIL = "Bob@bob.bob";
    private static final String PHONE_NUMBER = "11234567890";
    private static final String BIO = "My name is Bob.";

    public static Seller createSeller() {
        return new Seller(ID, NAME, Instant.MAX.toString(), BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);
    }

    public static Seller createSellerStub() {
        Seller seller = mock(Seller.class);
        when(seller.getId()).thenReturn(ID);
        when(seller.getName()).thenReturn(NAME);
        when(seller.getCreatedAt()).thenReturn(Instant.MAX.toString());
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
}
