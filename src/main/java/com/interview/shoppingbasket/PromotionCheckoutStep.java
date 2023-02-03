package com.interview.shoppingbasket;

public class PromotionCheckoutStep implements CheckoutStep{

    private PromotionsService promotionsService;
    public PromotionCheckoutStep (final PromotionsService promotion) {
        this.promotionsService = promotionsService;
    }
    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        this.promotionsService.getPromotions(basket);
    }
}
