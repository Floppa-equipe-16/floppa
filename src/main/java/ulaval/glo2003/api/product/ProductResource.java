package ulaval.glo2003.api.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.domain.product.Product;
import ulaval.glo2003.domain.product.ProductConverter;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellersRepository;

@Path("/products")
public class ProductResource {
    private final SellersRepository sellersRepository;

    public ProductResource(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProducts(
            @Context UriInfo uriInfo, @HeaderParam("X-Seller-Id") String xSellerId, ProductRequest productRequest) {
        productRequest.validateProductNonNullParameter();

        Seller foundSeller = sellersRepository.findSellerBySellerId(xSellerId);
        Product product = ProductConverter.productRequestToProduct(productRequest);
        foundSeller.addProduct(product);

        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + product.getId())
                .build();
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productId") String productId) {
        Seller foundSeller = sellersRepository.findSellerByProductId(productId);
        Product foundProduct = foundSeller.getProductById(productId);

        return Response.ok()
                .entity(new ProductResponse(foundSeller, foundProduct))
                .build();
    }
}
