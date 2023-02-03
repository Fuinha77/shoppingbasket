package com.interview.shoppingbasket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromotionType {

    private Long id;
    private String description;

}
