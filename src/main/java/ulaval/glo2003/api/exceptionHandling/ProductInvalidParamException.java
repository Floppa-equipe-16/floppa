package ulaval.glo2003.api.exceptionHandling;

public class ProductInvalidParamException extends ProductException{

    public ProductInvalidParamException(String message) {
        super(new ErrorDescription("INVALID_PARAMETER",message));
    }
}
