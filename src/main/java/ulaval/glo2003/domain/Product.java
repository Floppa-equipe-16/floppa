package ulaval.glo2003.domain;

import ulaval.glo2003.api.ProductCategory;
import ulaval.glo2003.api.exceptionHandling.InvalidParamException;
import ulaval.glo2003.api.exceptionHandling.NotPermittedException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private final String title;
    private final String description;
    private final Double suggestedPrice;
    private final String category;
    private final String id;
    private final String createdAt;

    private final ArrayList<Offer> offers;

    public Product(String title, String description, Double suggestedPrice, String category) {
        this.title = title;
        this.description = description;
        this.suggestedPrice = Math.round(suggestedPrice * 100d) / 100d;
        this.category = category;
        this.offers = new ArrayList<>();

        validateProductParameters();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    private void validateProductParameters() {
        if (isStringEmpty(title)) throw new InvalidParamException("title");
        if (isStringEmpty(description)) throw new InvalidParamException("description");
        if (!doesCategoryExist(category)) throw new InvalidParamException("category");
        if (isSuggestedPriceUnder1()) throw new InvalidParamException("suggestedPrice");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void addOffer(Offer offer) {
        validateOfferEligible(offer);
        offers.add(offer);
    }

    private boolean isStringEmpty(String s) {
        return s.trim().isEmpty();
    }

    private boolean isSuggestedPriceUnder1() {
        return suggestedPrice.intValue() < 1;
    }

    private boolean doesCategoryExist(String category) {
        try {
            ProductCategory.valueOf(category);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void validateOfferEligible(Offer offer) {
        if (!isOfferAmountHighEnough(offer.getAmount()))
            throw  new InvalidParamException("amount");
        if (hasBuyerAlreadyMadeAnOffer(offer.getUsername()))
            throw new NotPermittedException("username");
    }

    private boolean hasBuyerAlreadyMadeAnOffer(String buyerUsername) {
        return offers.stream().anyMatch(offer -> buyerUsername.equals(offer.getUsername()));
    }
    private boolean isOfferAmountHighEnough(Double offerAmount) {
        return offerAmount >= suggestedPrice;
    }
}
