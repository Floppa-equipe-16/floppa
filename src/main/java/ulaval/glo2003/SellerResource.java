package ulaval.glo2003;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/sellers")
public class SellerResource {

    private final ArrayList<Seller> sellers = new ArrayList<>();

    @GET
    @Path("/{sellerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        return Response.ok().entity(getSellerById(sellerId)).build();
    }

    private Seller getSellerById(String sellerId) {
        for (Seller seller : sellers) {
            if (seller.id.equals(sellerId)) {
                return seller;
            }
        }

        throw new NotFoundException(String.format("Seller with id '%s' not found", sellerId));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@Context UriInfo uriInfo, Seller seller) {
        if (isSellerMissingParameter(seller))
            throw new ValidationException("MISSING_PARAMETER");
        if (isSellerInvalidParameter(seller))
            throw new ValidationException("INVALID_PARAMETER");
        sellers.add(seller);
        return Response.status(Response.Status.CREATED).entity(seller.id).header("Location", uriInfo.getAbsolutePath() + "/" + seller.id).build();
    }

    private boolean isSellerMissingParameter(Seller seller){
        return (seller.name == null ||
                seller.email == null ||
                seller.birthdate == null ||
                seller.phoneNumber == null ||
                seller.bio == null);
    }

    private boolean isSellerInvalidParameter(Seller seller){
        return (seller.name.isEmpty() ||
                isBirthdateInvalid(seller.birthdate) ||
                isEmailInvalid(seller.email) ||
                isPhoneInvalid(seller.phoneNumber) ||
                seller.bio.isEmpty());
    }

    private boolean isBirthdateInvalid(String birthdate){

        return (!(isDateValid("dd-MM-yyyy",birthdate) ||
                  isDateValid("dd/MM/yyyy",birthdate) ||
                  isDateValid("dd MM yyyy",birthdate) ||
                  isDateValid("yyyy-MM-dd",birthdate) ||
                  isDateValid("yyyy/MM/dd",birthdate) ||
                  isDateValid("yyyy MM dd",birthdate)));
    }

    private boolean isEmailInvalid(String email){
        Pattern p = Pattern.compile("^(.+)@(.+)$");
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    private boolean isPhoneInvalid(String phone){
        Pattern p = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");
        Matcher m = p.matcher(phone);
        return !m.matches();
    }

    private boolean isDateValid(String pattern, String s){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try{
            dateFormat.parse(s);
            return true;
        }catch (ParseException ignored){
            return false;
        }
    }

}
