package ulaval.glo2003.api.exceptionHandling;

public class SellerInvalidParamException extends SellerException{

    public SellerInvalidParamException(String message) {
        super(new ErrorDescription("INVALID_PARAMETER",message));
    }
}
