package ulaval.glo2003.api.exceptionHandling;

public class ProductItemNotFoundException extends ProductException{
    public ProductItemNotFoundException(String message) {
        super(new ErrorDescription("ITEM_NOT_FOUND",message));
    }
}
