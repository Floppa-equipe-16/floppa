package ulaval.glo2003.domain.exceptions;

public class InvalidParamException extends ParamValidationException {

    public InvalidParamException(String invalidParameter) {
        super(new ErrorDescription("INVALID_PARAMETER", String.format("Invalid parameter '%s'.", invalidParameter)));
    }
}
