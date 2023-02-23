package ulaval.glo2003.domain.product;

import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.exceptions.NotPermittedException;
import ulaval.glo2003.domain.offer.Offer;

class ProductValidator {

    private static final Double MINIMUM_SUGGESTED_PRICE = 1d;

    public static void validateParam(Product product) {
        if (isSellerIdInvalid(product.getSellerId())) throw new InvalidParamException("id");
        if (isTitleInvalid(product.getTitle())) throw new InvalidParamException("title");
        if (isDescriptionInvalid(product.getDescription())) throw new InvalidParamException("description");
        if (isCategoryInvalid(product.getCategory())) throw new InvalidParamException("category");
        if (isSuggestedPriceInvalid(product.getSuggestedPrice())) throw new InvalidParamException("suggested price");
    }

    public static void validateOfferEligible(Product product, Offer offer) {
        if (isOfferAmountLessThenSuggestedPrice(product, offer.getAmount())) throw new InvalidParamException("amount");
        if (hasBuyerAlreadyMadeAnOffer(product, offer.getUsername()))
            throw new NotPermittedException(
                    "user with username `" + offer.getUsername() + "` has already made an offer");
    }

    protected static boolean isSellerIdInvalid(String sellerId) {
        return sellerId.isBlank();
    }

    protected static boolean isTitleInvalid(String title) {
        return title.isBlank();
    }

    protected static boolean isDescriptionInvalid(String description) {
        return description.isBlank();
    }

    protected static boolean isSuggestedPriceInvalid(Double suggestedPrice) {
        return suggestedPrice < MINIMUM_SUGGESTED_PRICE;
    }

    protected static boolean isCategoryInvalid(String category) {
        try {
            ProductCategory.valueOf(category);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    protected static boolean isOfferAmountLessThenSuggestedPrice(Product product, Double offerAmount) {
        return offerAmount < product.getSuggestedPrice();
    }

    protected static boolean hasBuyerAlreadyMadeAnOffer(Product product, String buyerUsername) {
        return product.getOffers().stream().anyMatch(offer -> buyerUsername.equals(offer.getUsername()));
    }
}
