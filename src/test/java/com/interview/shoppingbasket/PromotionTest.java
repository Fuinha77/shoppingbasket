package com.interview.shoppingbasket;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionTest {
    @Test
    void givenBasketWhenCreatePromotion() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        Promotion promotion = new Promotion();
        PromotionType promotionTypeMock = PromotionType.builder()
                .id(1L)
                .description("Quantity")
                .build();
        promotion.add(1L,"productCode", "myProduct", promotionTypeMock, 2);
        List<PromotionItem> promotionItemSize = promotion.getPromotions(basket);

        assertEquals(promotionItemSize.size(), 1);
        assertEquals(promotionItemSize.get(0).getId(), 1L);
        assertEquals(promotionItemSize.get(0).getProductCode(), "productCode");
        assertEquals(promotionItemSize.get(0).getDescription(), "myProduct");
        assertEquals(promotionItemSize.get(0).getPromotionType(), promotionTypeMock);
        assertEquals(promotionItemSize.get(0).getValue(), 2);
    }


    @Test
    void consolidateBasketTest() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode3", "myProduct3", 10);
        basket.add("productCode3", "myProduct3", 10);

        Promotion promotion = new Promotion();
        PromotionType promotionTypeMockQuantity = PromotionType.builder()
                .id(1L)
                .description("Quantity")
                .build();

        PromotionType promotionTypeMockPercentage = PromotionType.builder()
                .id(2L)
                .description("Percentage")
                .build();
        promotion.add(1L,"productCode", "myProduct", promotionTypeMockQuantity, 2);
        promotion.add(2L,"productCode2", "myProduct2", promotionTypeMockPercentage, 50);
        promotion.add(2L,"productCode3", "myProduct3", promotionTypeMockPercentage, 10);

        final var promotionItemList = promotion.getPromotions(basket);

        assertEquals(promotionItemList.size(), 3);
        assertEquals(promotionItemList.get(0).getId(), 1L);
        assertEquals(promotionItemList.get(0).getProductCode(), "productCode");
        assertEquals(promotionItemList.get(0).getDescription(), "myProduct");
        assertEquals(promotionItemList.get(0).getPromotionType(), promotionTypeMockQuantity);
        assertEquals(promotionItemList.get(0).getValue(), 2);

        assertEquals(promotionItemList.get(1).getId(), 2L);
        assertEquals(promotionItemList.get(1).getProductCode(), "productCode2");
        assertEquals(promotionItemList.get(1).getDescription(), "myProduct2");
        assertEquals(promotionItemList.get(1).getPromotionType(), promotionTypeMockPercentage);
        assertEquals(promotionItemList.get(1).getValue(), 50);

        assertEquals(promotionItemList.get(2).getId(), 2L);
        assertEquals(promotionItemList.get(2).getProductCode(), "productCode3");
        assertEquals(promotionItemList.get(2).getDescription(), "myProduct3");
        assertEquals(promotionItemList.get(2).getPromotionType(), promotionTypeMockPercentage);
        assertEquals(promotionItemList.get(2).getValue(), 10);

    }
}
