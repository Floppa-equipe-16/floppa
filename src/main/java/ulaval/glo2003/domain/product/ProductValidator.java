package ulaval.glo2003.domain.product;

import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.exceptions.NotPermittedException;
import ulaval.glo2003.domain.offer.Offer;

class ProductValidator {
    private final Product product;

    public ProductValidator(Product product) {
        this.product = product;
    }

    public void validateParamThrowIfInvalid() {
        if (isSellerIdInvalid(product.getSellerId())) throw new InvalidParamException("id");
        if (isTitleInvalid(product.getTitle())) throw new InvalidParamException("title");
        if (isDescriptionInvalid(product.getDescription())) throw new InvalidParamException("description");
        if (isCategoryInvalid(product.getCategory())) throw new InvalidParamException("category");
        if (isSuggestedPriceInvalid(product.getSuggestedPrice())) throw new InvalidParamException("suggested price");
    }

    public void validateOfferEligibleThrowIfInvalid(Offer offer) {
        if (isOfferAmountLessThenSuggestedPrice(offer.getAmount())) throw new InvalidParamException("amount");
        if (hasBuyerAlreadyMadeAnOffer(offer.getUsername()))
            throw new NotPermittedException(
                    "user with username `" + offer.getUsername() + "` has already made an offer");
    }

    protected boolean isSellerIdInvalid(String s) {
        return s.isBlank();
    }

    protected boolean isTitleInvalid(String s) {
        return s.isBlank();
    }

    protected boolean isDescriptionInvalid(String s) {
        return s.isBlank();
    }

    protected boolean isSuggestedPriceInvalid(Double suggestedPrice) {
        return suggestedPrice < 1d;
    }

    protected boolean isCategoryInvalid(String category) {
        try {
            ProductCategory.valueOf(category);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    protected boolean isOfferAmountLessThenSuggestedPrice(Double offerAmount) {
        return offerAmount < product.getSuggestedPrice();
    }

    protected boolean hasBuyerAlreadyMadeAnOffer(String buyerUsername) {
        return product.getOffers().stream().anyMatch(offer -> buyerUsername.equals(offer.getUsername()));
    }
}
