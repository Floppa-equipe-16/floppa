package ulaval.glo2003.api.exceptionHandling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class SellerExceptionMapper implements ExceptionMapper<ParamValidationException> {

    @Override
    public Response toResponse(ParamValidationException exception) {
        return Response.status(400).entity(exception.subError).build();
    }
}
