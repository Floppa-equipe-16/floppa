package ulaval.glo2003.api.exceptionHandling;

import jakarta.validation.ValidationException;

public abstract class ParamValidationException extends ValidationException {
    public final ErrorDescription subError;

    public ParamValidationException(ErrorDescription error) {
        this.subError = error;
    }
}
