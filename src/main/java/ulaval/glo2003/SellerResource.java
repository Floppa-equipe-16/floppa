package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSeller(@Context UriInfo uriInfo, Seller seller) {
        sellerMissingParameter(seller);

        sellerInvalidParameter(seller);
            //throw new ValidationException("INVALID_PARAMETER");
        sellers.add(seller);
        return Response.status(Response.Status.CREATED).entity(seller.id).header("Location", uriInfo.getAbsolutePath() + "/" + seller.id).build();
    }


    private Seller getSellerById(String sellerId) {
        for (Seller seller : sellers) {
            if (seller.id.equals(sellerId)) {
                return seller;
            }
        }

        throw new NotFoundException(String.format("Seller with id '%s' not found", sellerId));
    }


    private void sellerMissingParameter(Seller seller){
        if (seller.name == null)
            throw new SellerException(new Suberror("MISSING_PARAMETER","Missing name value"));
        if (seller.email == null)
            throw new SellerException(new Suberror("MISSING_PARAMETER","Missing email value"));
        if (seller.birthdate == null)
            throw new SellerException(new Suberror("MISSING_PARAMETER","Missing birthdate value"));
        if (seller.phoneNumber == null)
            throw new SellerException(new Suberror("MISSING_PARAMETER","Missing Phone number"));
        if (seller.bio == null)
            throw new SellerException(new Suberror("MISSING_PARAMETER","Missing bio value"));

    }

    private void sellerInvalidParameter(Seller seller){
        if (seller.name.trim().isEmpty())
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid name value"));
        if (isBirthdateInvalid(seller.birthdate))
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid birthdate value"));
        if (isEmailInvalid(seller.email))
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid email value"));
        if (isPhoneInvalid(seller.phoneNumber))
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid phone number"));
        if (seller.bio.trim().isEmpty())
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid bio value"));
    }

    private boolean isBirthdateInvalid(String birthdate){
        return !(isDateValid("yyyy-MM-dd",birthdate) &&
                is18OrMore("yyyy-MM-dd",birthdate));
    }

    private boolean is18OrMore(String pattern, String birthday){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDate now = LocalDate.now();
        LocalDate birthdayDate =  LocalDate.parse(birthday, dtf);
        LocalDate birthday18Plus = birthdayDate.plusYears(18);
        return (birthday18Plus.isBefore(now));
    }
    private boolean isEmailInvalid(String email){

        Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
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
