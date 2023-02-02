/*package ulaval.glo2003;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SellerConstructorTest {
    private SellerResource sellerResource;

    @BeforeEach
    void setUp() {
        this.sellerResource = new SellerResource();
    }

    @Test
    void missingArgumentTest(){

        Seller seller = new Seller(null, null, null, null, null);
        try{
            this.sellerResource.sellerMissingParameter(seller);
            fail();
        }catch (SellerException se){
            assertEquals(se.subError.code, "MISSING_PARAMETER");
            assertEquals(se.subError.description, "Missing name value");
        }
        seller.name = "test";

        try{
            this.sellerResource.sellerMissingParameter(seller);
            fail();
        }catch (SellerException se){
            assertEquals(se.subError.code, "MISSING_PARAMETER");
            assertEquals(se.subError.description, "Missing email value");
        }
        seller.email = "test@test.com";

        try{
            this.sellerResource.sellerMissingParameter(seller);
            fail();
        }catch (SellerException se){
            assertEquals(se.subError.code, "MISSING_PARAMETER");
            assertEquals(se.subError.description, "Missing birthdate value");
        }
        seller.birthdate = "2022-10-22";

        try{
            this.sellerResource.sellerMissingParameter(seller);
            fail();
        }catch (SellerException se){
            assertEquals(se.subError.code, "MISSING_PARAMETER");
            assertEquals(se.subError.description, "Missing phone number");
        }
        seller.phoneNumber = "14186789988";

        try{
            this.sellerResource.sellerMissingParameter(seller);
            fail();
        }catch (SellerException se){
            assertEquals(se.subError.code, "MISSING_PARAMETER");
            assertEquals(se.subError.description, "Missing bio value");
        }
        seller.bio = "test is a test";

        try{
            this.sellerResource.sellerMissingParameter(seller);
        }catch (SellerException se){
           fail();
        }
    }

    @Test
    void isBirthdateInvalideTest(){
        assertFalse(this.sellerResource.isBirthdateInvalid("1999-11-22"));
        assertTrue(this.sellerResource.isBirthdateInvalid("1999-13-22"));
        assertTrue(this.sellerResource.isBirthdateInvalid("1999/11/22"));
        assertTrue(this.sellerResource.isBirthdateInvalid("1999 11 22"));
        assertTrue(this.sellerResource.isBirthdateInvalid("22-11-2001"));
        assertTrue(this.sellerResource.isBirthdateInvalid("22 11 2001"));
        assertTrue(this.sellerResource.isBirthdateInvalid("22/11/2001"));
        assertTrue(this.sellerResource.isBirthdateInvalid("2oo1-11-22"));
    }

    @Test
    void isStringEmptyTest(){
        assertTrue(this.sellerResource.isStringEmpty(""));
        assertTrue(this.sellerResource.isStringEmpty("\n"));
        assertTrue(this.sellerResource.isStringEmpty("   \t"));
        assertTrue(this.sellerResource.isStringEmpty("\n\t \n"));
        assertFalse(this.sellerResource.isStringEmpty("test \n"));
    }

    @Test
    void isEmailInvalidTest(){
        assertFalse(this.sellerResource.isEmailInvalid("test@test.com"));
        assertFalse(this.sellerResource.isEmailInvalid("test.test@test.test.com"));
        assertTrue(this.sellerResource.isEmailInvalid("test#test.com"));
        assertTrue(this.sellerResource.isEmailInvalid("test@test"));
        assertFalse(this.sellerResource.isEmailInvalid("test@test.test"));
    }

    @Test
    void isPhoneInvalidTest(){
        assertFalse(this.sellerResource.isPhoneInvalid("14186789898"));
        assertTrue(this.sellerResource.isPhoneInvalid("4186789898"));
        assertTrue(this.sellerResource.isPhoneInvalid("+4186789898"));
        assertTrue(this.sellerResource.isPhoneInvalid("14186+89898"));
        assertTrue(this.sellerResource.isPhoneInvalid("14186a89898"));
    }

}
*/