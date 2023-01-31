package ulaval.glo2003;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public  class SellerExceptionMapper implements ExceptionMapper<ValidationException>  {

    @Override
    public Response toResponse(ValidationException exception) {
        return Response.status(400).entity(exception.getMessage()).build();
    }
}
