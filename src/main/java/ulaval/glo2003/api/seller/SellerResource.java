package ulaval.glo2003.api.seller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.domain.seller.Seller;
import ulaval.glo2003.domain.seller.SellerConverter;
import ulaval.glo2003.domain.seller.SellersRepository;

@Path("/sellers")
public class SellerResource {
    private final SellersRepository sellersRepository;

    public SellerResource(SellersRepository sellersRepository) {
        this.sellersRepository = sellersRepository;
    }

    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        Seller foundSeller = sellersRepository.findSellerBySellerId(sellerId);

        return Response.ok().entity(new SellerResponse(foundSeller)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@Context UriInfo uriInfo, SellerRequest sellerRequest) {
        sellerRequest.validateSellerNonNullParameters();

        Seller seller = SellerConverter.sellerRequestToSeller(sellerRequest);
        sellersRepository.addSeller(seller);

        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + seller.getId())
                .build();
    }
}
