package ulaval.glo2003.api.seller;

import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.seller.Seller;


public class SellerRequest {
    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String bio;

    public void validate() {
        if (name == null) throw new MissingParamException("name");
        if (email == null) throw new MissingParamException("email");
        if (birthdate == null) throw new MissingParamException("birthdate");
        if (phoneNumber == null) throw new MissingParamException("phone number");
        if (bio == null) throw new MissingParamException("bio");
    }

    private boolean isSellerRequestEquals(SellerRequest sellerRequest){
        return name.equals(sellerRequest.name) &&
                birthdate.equals(sellerRequest.birthdate) &&
                email.equals(sellerRequest.email) &&
                phoneNumber.equals(sellerRequest.phoneNumber) &&
                bio.equals(sellerRequest.bio);
    }

    private boolean isSellerEquals(Seller seller){
        return name.equals(seller.getName()) &&
                birthdate.equals(seller.getBirthdate()) &&
                email.equals(seller.getEmail()) &&
                phoneNumber.equals(seller.getPhoneNumber()) &&
                bio.equals(seller.getBio());
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }

        if(o instanceof SellerRequest){
            SellerRequest sellerRequest = ((SellerRequest) o);
            return  isSellerRequestEquals(sellerRequest);
        }
        else if (o instanceof Seller){
            Seller seller = ((Seller) o);
            return isSellerEquals(seller);
        }
        else return false;

    }
}
