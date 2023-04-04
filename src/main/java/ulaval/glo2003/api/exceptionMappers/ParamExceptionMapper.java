package ulaval.glo2003.api.exceptionMappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.domain.exceptions.ParamValidationException;

public class ParamExceptionMapper implements ExceptionMapper<ParamValidationException> {

    @Override
    public Response toResponse(ParamValidationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.errorDescription)
                .build();
    }
}
