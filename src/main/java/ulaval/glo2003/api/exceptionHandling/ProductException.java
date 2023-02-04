package ulaval.glo2003.api.exceptionHandling;

import jakarta.validation.ValidationException;

public abstract class ProductException extends ValidationException {

    public final ErrorDescription subError;

    public ProductException(ErrorDescription error) {
        this.subError = error;
    }
}
