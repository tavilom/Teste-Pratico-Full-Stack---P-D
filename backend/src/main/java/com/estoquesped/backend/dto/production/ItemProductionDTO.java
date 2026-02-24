package com.estoquesped.backend.dto.production;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemProductionDTO {

    private Long productId;
    private String productName;
    private Integer producedQuantity;
    private BigDecimal totalProductValue;
}