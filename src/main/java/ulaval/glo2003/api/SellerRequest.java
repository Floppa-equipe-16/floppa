package ulaval.glo2003.api;

import ulaval.glo2003.api.exceptionHandling.SellerException;
import ulaval.glo2003.api.exceptionHandling.ErrorDescription;

public class SellerRequest {

    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;

    public SellerRequest() {
        validateSellerNonNullParameter();
    }

    protected void validateSellerNonNullParameter() {
        if (name == null)
            throw new SellerException(new ErrorDescription("MISSING_PARAMETER", "Missing name value"));
        if (email == null)
            throw new SellerException(new ErrorDescription("MISSING_PARAMETER", "Missing email value"));
        if (birthdate == null)
            throw new SellerException(new ErrorDescription("MISSING_PARAMETER", "Missing birthdate value"));
        if (phoneNumber == null)
            throw new SellerException(new ErrorDescription("MISSING_PARAMETER", "Missing phone number"));
        if (bio == null)
            throw new SellerException(new ErrorDescription("MISSING_PARAMETER", "Missing bio value"));
    }
}
