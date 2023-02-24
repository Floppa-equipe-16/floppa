package ulaval.glo2003.domain.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    private static final double ONE_HUNDRED = 100d;

    public Product(String sellerId, String title, String description, Double suggestedPrice, String category) {
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
        this.suggestedPrice = Math.round(suggestedPrice * ONE_HUNDRED) / ONE_HUNDRED;
        this.category = category;
        this.offers = new ArrayList<>();

        ProductValidator.validateParam(this);

        id = UUID.randomUUID().toString();
        createdAt = Instant.now().toString();
    }

    public Product(Product that) {
        sellerId = that.getSellerId();
        title = that.getTitle();
        description = that.getDescription();
        suggestedPrice = that.getSuggestedPrice();
        category = that.getCategory();
        id = that.getId();
        createdAt = that.getCreatedAt();
        offers = new ArrayList<>();
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
        ProductValidator.validateOfferEligible(this, offer);
        offers.add(offer);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product that = (Product) obj;

            return id.equalsIgnoreCase(that.getId())
                    && sellerId.equalsIgnoreCase(that.getSellerId())
                    && title.equalsIgnoreCase(that.getTitle())
                    && description.equalsIgnoreCase(that.getDescription())
                    && suggestedPrice.equals(that.getSuggestedPrice())
                    && category.equalsIgnoreCase(that.getCategory())
                    && createdAt.equalsIgnoreCase(that.getCreatedAt());
        }
        return false;
    }
}
