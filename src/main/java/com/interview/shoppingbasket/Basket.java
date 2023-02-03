package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        final var distinctItems = this.getItems().stream()
                .distinct()
                .collect(Collectors.toList());

        distinctItems.forEach(consolidatedItem ->
                consolidatedItem.setQuantity(this.sumBasketItemQuantity(consolidatedItem)));

        items = distinctItems;
    }

    private int sumBasketItemQuantity(final BasketItem consolidatedItem) {
        return items.stream()
                .filter(basketItem -> consolidatedItem.getProductCode().equals(basketItem.getProductCode()))
                .mapToInt(BasketItem::getQuantity).sum();
    }
}
