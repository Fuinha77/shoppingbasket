package com.interview.shoppingbasket;

import java.util.List;

interface PromotionsService {
    List<PromotionItem> getPromotions(final Basket basket);
}
