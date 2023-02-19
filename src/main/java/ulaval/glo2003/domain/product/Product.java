package ulaval.glo2003.domain.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import ulaval.glo2003.domain.offer.Offer;

public class Product {
    private final String title;
    private final String description;
    private final Double suggestedPrice;
    private final String category;
    private final String id;
    private final String createdAt;
    private final ArrayList<Offer> offers;
    private final ProductParamValidator productParamValidator;

    public Product(String title, String description, Double suggestedPrice, String category) {
        this.title = title;
        this.description = description;
        this.suggestedPrice = Math.round(suggestedPrice * 100d) / 100d;
        this.category = category;
        this.offers = new ArrayList<>();

        productParamValidator = new ProductParamValidator(this);
        productParamValidator.validateProductParamThrowIfInvalid();

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
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

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void addOffer(Offer offer) {
        productParamValidator.validateOfferEligibleThrowIfInvalid(offer);
        offers.add(offer);
    }
}
