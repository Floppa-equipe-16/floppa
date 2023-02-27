package ulaval.glo2003.domain.seller;

import ulaval.glo2003.api.seller.SellerRequest;

public class SellerTestUtils {
    private static final String NAME = "Bob";
    private static final String BIRTHDATE = "2000-01-01";
    private static final String EMAIL = "Bob@bob.bob";
    private static final String PHONE_NUMBER = "11234567890";
    private static final String BIO = "My name is Bob.";

    public static Seller createSeller() {
        return new Seller(NAME, BIRTHDATE, EMAIL, PHONE_NUMBER, BIO);
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
