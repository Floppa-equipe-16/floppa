package ulaval.glo2003.domain.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.exceptions.MissingParamException;
import ulaval.glo2003.domain.exceptions.NotPermittedException;
import ulaval.glo2003.domain.offer.Offer;

public class Product {
    private final String id;
    private final String sellerId;
    private final String title;
    private final String description;
    private final Double suggestedPrice;
    private final String category;

    private final String createdAt;

    private final ArrayList<Offer> offers;

    public Product(String sellerId, String title, String description, Double suggestedPrice, String category) {
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
        this.suggestedPrice = Math.round(suggestedPrice * 100d) / 100d;
        this.category = category;
        this.offers = new ArrayList<>();

        validateParameters();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    private void validateParameters() {
        if (sellerId.isBlank()) throw new MissingParamException("sellerId");
        if (title.isBlank()) throw new InvalidParamException("title");
        if (description.isBlank()) throw new InvalidParamException("description");
        if (!doesCategoryExist()) throw new InvalidParamException("category");
        if (isSuggestedPriceUnder1()) throw new InvalidParamException("suggestedPrice");
    }

    public String getSellerId() {
        return sellerId;
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

    private boolean isSuggestedPriceUnder1() {
        return suggestedPrice.intValue() < 1;
    }

    private boolean doesCategoryExist() {
        try {
            ProductCategory.valueOf(this.category);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void validateOfferEligible(Offer offer) {
        if (!isOfferAmountAtLeastSuggestedPrice(offer.getAmount())) throw new InvalidParamException("amount");
        if (hasBuyerAlreadyMadeAnOffer(offer.getUsername()))
            throw new NotPermittedException("user with username " + offer.getUsername() + " has already made an offer");
    }

    private boolean hasBuyerAlreadyMadeAnOffer(String buyerUsername) {
        return offers.stream().anyMatch(offer -> buyerUsername.equals(offer.getUsername()));
    }

    private boolean isOfferAmountAtLeastSuggestedPrice(Double offerAmount) {
        return offerAmount >= suggestedPrice;
    }
}
