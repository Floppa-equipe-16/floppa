package ulaval.glo2003.api.exceptionHandling;

public class NotPermittedException extends ParamValidationException{

    public NotPermittedException(String notPermittedParameter) {
        super(new ErrorDescription("NOT_PERMITTED", String.format("Not permitted parameter '%s'.", notPermittedParameter)));
    }
}
