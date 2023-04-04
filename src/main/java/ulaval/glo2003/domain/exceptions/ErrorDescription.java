package ulaval.glo2003.domain.exceptions;

public class ErrorDescription {

    public String code;

    public String description;

    public ErrorDescription() {}

    public ErrorDescription(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
