package ulaval.glo2003.domain.product;

import java.util.ArrayList;
import java.util.List;
import ulaval.glo2003.domain.offer.Offer;

public class Product {
    private final String id;
    private final String sellerId;
    private final String title;
    private final String createdAt;
    private final String description;
    private final Double suggestedPrice;
    private final String category;
    private SaleStatus saleStatus;
    private final ArrayList<Offer> offers;

    public Product(
            String id,
            String sellerId,
            String title,
            String createdAt,
            String description,
            Double suggestedPrice,
            SaleStatus saleStatus,
            String category) {
        this.id = id;
        this.sellerId = sellerId;
        this.title = title;
        this.createdAt = createdAt;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.category = category;
        this.saleStatus = saleStatus;
        this.offers = new ArrayList<>();
    }

    public Product(Product that) {
        sellerId = that.getSellerId();
        title = that.getTitle();
        description = that.getDescription();
        suggestedPrice = that.getSuggestedPrice();
        category = that.getCategory();
        id = that.getId();
        createdAt = that.getCreatedAt();
        saleStatus = that.getSaleStatus();

        offers = new ArrayList<>();
        that.getOffers().forEach(offer -> offers.add(new Offer(offer)));
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

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
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
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product that = (Product) obj;

        return id.equals(that.getId())
                && sellerId.equals(that.getSellerId())
                && title.equalsIgnoreCase(that.getTitle())
                && description.equalsIgnoreCase(that.getDescription())
                && suggestedPrice.equals(that.getSuggestedPrice())
                && category.equalsIgnoreCase(that.getCategory())
                && createdAt.equalsIgnoreCase(that.getCreatedAt());
    }
}
