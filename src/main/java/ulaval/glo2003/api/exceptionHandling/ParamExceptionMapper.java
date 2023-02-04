package ulaval.glo2003.api.exceptionHandling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ParamExceptionMapper implements ExceptionMapper<ParamValidationException> {

    @Override
    public Response toResponse(ParamValidationException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.subError).build();
    }
}
