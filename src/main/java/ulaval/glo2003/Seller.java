package ulaval.glo2003;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seller {
    private final String name;
    private final String birthdate;
    private final String email;
    private final String phoneNumber;
    private final String bio;

    private final String id;
    private final String createdAt;

    public Seller(String name, String birthdate, String email, String phoneNumber, String bio) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;

        verificationOfSellerParameters();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    private void verificationOfSellerParameters(){
        if (isStringEmpty(name))
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid name value"));
        if (isBirthdateInvalid())
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid birthdate value"));
        if (isEmailInvalid(email))
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid email value"));
        if (isPhoneInvalid(phoneNumber))
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid phone number"));
        if (isStringEmpty(bio))
            throw new SellerException(new Suberror("INVALID_PARAMETER","Invalid bio value"));
    }

    private boolean isStringEmpty(String s){
        return s.trim().isEmpty();
    }

    private boolean isBirthdateInvalid(){
        return !(isDateValid() &&
                is18orMore());
    }

    private boolean isEmailInvalid(String email){

        Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    private boolean isPhoneInvalid(String phone){
        if (phone.length() != 11)
            return true;
        if (phone.charAt(0) == '-' || phone.charAt(0) == '+')
            return true;
        try{
            Integer.parseInt(phone.substring(5,11));
        }catch (NumberFormatException e){
            return true;
        }
        return false;
    }

    private boolean isDateValid(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dateFormat.parse(birthdate);
            return true;
        }catch (ParseException ignored){
            return false;
        }
    }

    private boolean is18orMore(){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate now = LocalDate.now();
            LocalDate birthdayDate = LocalDate.parse(birthdate, dtf);
            LocalDate birthday18Plus = birthdayDate.plusYears(18);
            return (birthday18Plus.isBefore(now));
        }catch (DateTimeParseException e) {
            return false;
        }
    }
}
