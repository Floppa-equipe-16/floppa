package ulaval.glo2003.domain.exceptions;

public class ErrorDescription {
    public final String code;
    public final String description;

    public ErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
