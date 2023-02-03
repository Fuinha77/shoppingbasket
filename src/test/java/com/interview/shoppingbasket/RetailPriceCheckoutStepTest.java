package com.interview.shoppingbasket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class RetailPriceCheckoutStepTest {

    PricingService pricingService;
    CheckoutContext checkoutContext;
    Basket basket;
    Promotion promotion;

    @BeforeEach
    void setup() {
        pricingService = Mockito.mock(PricingService.class);
        checkoutContext = Mockito.mock(CheckoutContext.class);
        basket = new Basket();
        promotion = new Promotion();

        when(checkoutContext.getBasket()).thenReturn(basket);
        when(checkoutContext.getPromotion()).thenReturn(promotion);
    }

    @Test
    void setPriceZeroForEmptyBasket() {

        RetailPriceCheckoutStep retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService);

        retailPriceCheckoutStep.execute(checkoutContext);

        Mockito.verify(checkoutContext).setRetailPriceTotal(0.0);
    }

    @Test
    void setPriceOfProductToBasketItem() {

        basket.add("product1", "myProduct1", 10);

        Promotion promotion = new Promotion();
        PromotionType promotionTypeMockPercentage = PromotionType.builder()
                .id(1L)
                .description("Percentage")
                .build();
        promotion.add(1L,"product1", "myProduct1", promotionTypeMockPercentage, 50);
        List<PromotionItem> promotionItemSize = promotion.getPromotions(basket);

        when(pricingService.getPrice("product1")).thenReturn(3.99);
        RetailPriceCheckoutStep retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService);

        retailPriceCheckoutStep.execute(checkoutContext);
        Mockito.verify(checkoutContext).setRetailPriceTotal((3.99 - (3.99 * (50 / 100))* 10));
    }

}
