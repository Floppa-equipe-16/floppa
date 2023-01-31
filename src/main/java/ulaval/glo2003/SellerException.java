package ulaval.glo2003;

import jakarta.validation.ValidationException;

public class SellerException extends ValidationException {
    public final Suberror subError;

    public SellerException(Suberror error) {
        this.subError = error;
    }
}
