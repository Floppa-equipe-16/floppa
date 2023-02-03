package ulaval.glo2003.api.exceptionHandling;

import jakarta.validation.ValidationException;

public abstract class  SellerException extends ValidationException {
    public final ErrorDescription subError;

    public SellerException(ErrorDescription error) {
        this.subError = error;
    }
}