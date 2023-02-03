package com.interview.shoppingbasket;

import java.util.List;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;

    private static Long QUANTITY = 1L;
    private static Long PERCENTAGE = 2L;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        Promotion promotion = checkoutContext.getPromotion();
        List<PromotionItem> existingPromotionItemList = promotion.getPromotions(basket);

        retailTotal = 0.0;

        for (BasketItem basketItem : basket.getItems()) {
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            retailTotal += applyFinalPrice(existingPromotionItemList, basketItem, price);
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    private double applyFinalPrice(final List<PromotionItem> existingPromotionItemList, final BasketItem item, double price) {
        final var optPromotion = existingPromotionItemList.stream()
                .filter(promotionItem -> item.getProductCode().equals(promotionItem.getProductCode()))
                .findFirst();

        if (optPromotion.isPresent()) {
            final var promotionItem = optPromotion.get();

            if (QUANTITY.equals(promotionItem.getPromotionType().getId())) {
                price = price * (Math.round(item.getQuantity() / promotionItem.getValue()));
            } else if (PERCENTAGE.equals(promotionItem.getPromotionType().getId())) {
                price = price - (price * (promotionItem.getValue() / 100)) * item.getQuantity();
            }
        }

        return price;
    }
}
