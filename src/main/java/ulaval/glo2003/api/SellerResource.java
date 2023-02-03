package ulaval.glo2003.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.domain.Seller;

import java.util.List;

@Path("/sellers")
public class SellerResource {

    private final List<Seller> sellers;

    public SellerResource(List<Seller> sellers) {
        this.sellers = sellers;
    }

    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        return Response.ok().entity(getSellerResponseById(sellerId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@Context UriInfo uriInfo, SellerRequest sellerRequest) {
        Seller seller =
                new Seller(
                        sellerRequest.name,
                        sellerRequest.birthdate,
                        sellerRequest.email,
                        sellerRequest.phoneNumber,
                        sellerRequest.bio);
        sellers.add(seller);
        return Response.status(Response.Status.CREATED)
                .header("Location", uriInfo.getAbsolutePath() + "/" + seller.getId())
                .build();
    }

    private SellerResponse getSellerResponseById(String sellerId) {
        Seller foundSeller =
                sellers.stream()
                        .filter(seller -> seller.getId().equals(sellerId))
                        .findFirst()
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                String.format(
                                                        "Seller with id '%s' not found",
                                                        sellerId)));

        return new SellerResponse(
                foundSeller.getId(),
                foundSeller.getCreatedAt(),
                foundSeller.getName(),
                foundSeller.getBirthdate(),
                foundSeller.getEmail(),
                foundSeller.getPhoneNumber(),
                foundSeller.getBio());
    }
}
