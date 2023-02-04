package ulaval.glo2003.api.exceptionHandling;

public class SellerMissingParamException extends SellerException{


    public SellerMissingParamException(String message) {

        super(new ErrorDescription("MISSING_PARAMETER",message));
    }
}
