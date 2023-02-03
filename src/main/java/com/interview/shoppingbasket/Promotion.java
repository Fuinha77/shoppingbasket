package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

public class Promotion implements PromotionsService {

    private List<PromotionItem> promotionList = new ArrayList<>();

    public void add(final Long id,
                    final String productCode,
                    final String promotionDescription,
                    final PromotionType promotionType,
                    final double value) {
        PromotionItem promotion = new PromotionItem();
        promotion.setId(id);
        promotion.setProductCode(productCode);
        promotion.setDescription(promotionDescription);
        promotion.setPromotionType(promotionType);
        promotion.setValue(value);

        this.promotionList.add(promotion);
    }

    @Override
    public List<PromotionItem> getPromotions(final Basket basket) {
        final List<PromotionItem> existingPromotionList = new ArrayList<>();
        basket.getItems().stream()
                .distinct()
                .forEach(basketItem -> {
            this.promotionList.stream()
                    .filter(promotionItem -> basketItem.getProductCode().equals(promotionItem.getProductCode()))
                    .findFirst()
                    .ifPresent(existingPromotionList::add);
        });

        return existingPromotionList;
    }

}
