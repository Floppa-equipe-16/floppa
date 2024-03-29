package ulaval.glo2003.domain.offer;

public class Offer {
    private final String id;
    private final String productId;
    private final String username;
    private final Double amount;
    private final String message;
    private final String createdAt;
    private boolean selected;

    public Offer(
            String id,
            String productId,
            String username,
            Double amount,
            String message,
            String createdAt,
            Boolean selected) {
        this.id = id;
        this.productId = productId;
        this.username = username;
        this.amount = amount;
        this.message = message;
        this.createdAt = createdAt;
        this.selected = selected;
    }

    public Offer(Offer that) {
        productId = that.getProductId();
        username = that.getUsername();
        amount = that.getAmount();
        message = that.getMessage();
        id = that.getId();
        createdAt = that.getCreatedAt();
        selected = that.isSelected();
    }

    public String getUsername() {
        return username;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getProductId() {
        return productId;
    }

    public String getId() {
        return id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Offer that = (Offer) obj;

        return id.equals(that.getId())
                && productId.equalsIgnoreCase(that.getProductId())
                && username.equalsIgnoreCase(that.getUsername())
                && message.equalsIgnoreCase(that.getMessage())
                && amount.equals(that.getAmount())
                && createdAt.equalsIgnoreCase(that.getCreatedAt());
    }
}
