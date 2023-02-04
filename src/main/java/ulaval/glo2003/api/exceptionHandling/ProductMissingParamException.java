package ulaval.glo2003.api.exceptionHandling;

public class ProductMissingParamException extends ProductException {
    public ProductMissingParamException(String message) {
        super(new ErrorDescription("MISSING_PARAMETER",message));
    }
}
