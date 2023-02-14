package ulaval.glo2003.domain.exceptions;

import ulaval.glo2003.domain.exceptions.ErrorDescription;
import ulaval.glo2003.domain.exceptions.ParamValidationException;

public class MissingParamException extends ParamValidationException {

    public MissingParamException(String missingParameter) {
        super(new ErrorDescription("MISSING_PARAMETER", String.format("Missing parameter '%s'.", missingParameter)));
    }
}
