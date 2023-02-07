package ulaval.glo2003.api.exceptionHandling;

public class MissingParamException extends ParamValidationException {

    public MissingParamException(String missingParameter) {
        super(
                new ErrorDescription(
                        "MISSING_PARAMETER",
                        String.format("Missing parameter '%s'.", missingParameter)));
    }
}
