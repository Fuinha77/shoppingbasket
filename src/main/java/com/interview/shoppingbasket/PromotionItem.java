package com.interview.shoppingbasket;

import lombok.Builder;
import lombok.Data;

@Data
public class PromotionItem {

    private Long id;
    private String productCode;
    private String description;
    private PromotionType promotionType;
    private Double value;

}
