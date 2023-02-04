package ulaval.glo2003.api.exceptionHandling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ProductExceptionMapper implements ExceptionMapper<ProductException> {
    @Override
    public Response toResponse(ProductException exception) {
        return Response.status(400).entity(exception.subError).build();
    }
}
