package ulaval.glo2003.domain.product;

import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ulaval.glo2003.domain.exceptions.NotPermittedException;
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
    private final List<Offer> offers;

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

    public boolean isSold() {
        return saleStatus == SaleStatus.sold;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void sellTo(String username) {
        if (isSold()) {
            throw new NotPermittedException("product has already been sold");
        }

        Optional<Offer> matchingOffer = findUserOffer(username);
        if (matchingOffer.isEmpty()) {
            throw new NotFoundException(
                    String.format("Offer with username: '%s' has no offer on this product", username));
        }

        this.saleStatus = SaleStatus.sold;
        matchingOffer.get().setSelected(true);
    }

    private Optional<Offer> findUserOffer(String username) {
        return offers.stream()
                .filter(offer -> username.equals(offer.getUsername()))
                .findFirst();
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public Offer getSelectedOffer() {
        if (saleStatus == SaleStatus.sold) {
            return offers.stream().filter(Offer::isSelected).findFirst().orElse(null);
        }
        return null;
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
