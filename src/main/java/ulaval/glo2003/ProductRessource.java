package ulaval.glo2003;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.security.InvalidParameterException;
import java.util.ArrayList;

@Path("/products")
public class ProductRessource {
    private final ArrayList<Product> products = new ArrayList<>();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducts(Product product, @HeaderParam("X-Seller-Id") String xSellerId, @Context UriInfo uriInfo) {


        if(isMissingParameter(product)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(!isCategoryExist(product)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String location = uriInfo.getAbsolutePath().toString() + "/" + product.id;
        products.add(product);
        return Response.status(Response.Status.CREATED).header("Location",location).build();
    }

    private boolean isMissingParameter(Product product){
        return (product.title == null || product.description == null
                || product.suggestedPrice == null || product.category == null);
    }

    private boolean isCategoryExist(Product product){
        try {
        ProductCategory.valueOf(product.category);
        }catch (Exception e){
           return false;
        }
        return true;
    }
}
