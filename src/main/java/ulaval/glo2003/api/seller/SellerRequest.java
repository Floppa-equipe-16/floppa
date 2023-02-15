package ulaval.glo2003.api.seller;

import ulaval.glo2003.domain.exceptions.MissingParamException;

public class SellerRequest {
    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;

    public void validateSellerNonNullParameters() {
        if (name == null) throw new MissingParamException("name");
        if (email == null) throw new MissingParamException("email");
        if (birthdate == null) throw new MissingParamException("birthdate");
        if (phoneNumber == null) throw new MissingParamException("phone number");
        if (bio == null) throw new MissingParamException("bio");
    }
}
