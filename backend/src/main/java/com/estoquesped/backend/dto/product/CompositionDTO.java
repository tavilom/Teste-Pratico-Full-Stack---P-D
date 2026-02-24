package com.estoquesped.backend.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompositionDTO {

    private Long rawMaterialId;
    private BigDecimal requiredQuantity;
}