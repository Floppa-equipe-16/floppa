package ulaval.glo2003.api.exceptionMappers;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.domain.exceptions.ErrorDescription;

public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorDescription("ITEM_NOT_FOUND", e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
