package com.interview.shoppingbasket;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {
    @Test
    void emptyBasket() {
        Basket basket = new Basket();
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 0);
    }

    @Test
    void createBasketFullConstructor() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 1);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
    }

    @Test
    void createBasketWithMultipleProducts() {
        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode3", "myProduct3", 10);

        List<BasketItem> basketSize = basket.getItems();

        assertEquals(basketSize.size(), 3);
        assertEquals(basketSize.get(0).getProductCode(), "productCode");
        assertEquals(basketSize.get(0).getProductName(), "myProduct");
        assertEquals(basketSize.get(0).getQuantity(), 10);
        assertEquals(basketSize.get(1).getProductCode(), "productCode2");
        assertEquals(basketSize.get(1).getProductName(), "myProduct2");
        assertEquals(basketSize.get(1).getQuantity(), 10);
        assertEquals(basketSize.get(2).getProductCode(), "productCode3");
        assertEquals(basketSize.get(2).getProductName(), "myProduct3");
        assertEquals(basketSize.get(2).getQuantity(), 10);
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

        final var consolidatedItems = basket.consolidateItems();

        assertEquals(consolidatedItems.size(), 3);
        assertEquals(consolidatedItems.get(0).getProductCode(), "productCode");
        assertEquals(consolidatedItems.get(0).getProductName(), "myProduct");
        assertEquals(consolidatedItems.get(0).getQuantity(), 30);
        assertEquals(consolidatedItems.get(1).getProductCode(), "productCode2");
        assertEquals(consolidatedItems.get(1).getProductName(), "myProduct2");
        assertEquals(consolidatedItems.get(1).getQuantity(), 10);
        assertEquals(consolidatedItems.get(2).getProductCode(), "productCode3");
        assertEquals(consolidatedItems.get(2).getProductName(), "myProduct3");
        assertEquals(consolidatedItems.get(2).getQuantity(), 20);

    }
}
