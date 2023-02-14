package ulaval.glo2003.domain.exceptions;

import jakarta.validation.ValidationException;
import ulaval.glo2003.domain.exceptions.ErrorDescription;

public abstract class ParamValidationException extends ValidationException {
    public final ErrorDescription errorDescription;

    public ParamValidationException(ErrorDescription error) {
        this.errorDescription = error;
    }
}
