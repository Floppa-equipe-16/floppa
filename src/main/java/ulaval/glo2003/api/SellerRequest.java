package ulaval.glo2003.api;

import ulaval.glo2003.api.exceptionHandling.SellerMissingParamException;

public class SellerRequest {

    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;

    public void validateSellerNonNullParameter() {
        if (name == null)
            throw new SellerMissingParamException("Missing name value");
        if (email == null)
            throw new SellerMissingParamException("Missing email value");
        if (birthdate == null)
            throw new SellerMissingParamException("Missing birthdate value");
        if (phoneNumber == null)
            throw new SellerMissingParamException("Missing phone number");
        if (bio == null)
            throw new SellerMissingParamException("Missing bio value");
    }
}
