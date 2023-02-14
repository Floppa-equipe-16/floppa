package ulaval.glo2003.domain.exceptions;

public class NotPermittedException extends ParamValidationException {

    public NotPermittedException(String notPermittedAction) {
        super(new ErrorDescription("NOT_PERMITTED", String.format("Not permitted action '%s'.", notPermittedAction)));
    }
}
