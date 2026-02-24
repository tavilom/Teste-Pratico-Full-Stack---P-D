package com.estoquesped.backend.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCompositionDTO {

    @NotNull(message = "rawMaterialId is required")
    private Long rawMaterialId;

    private String rawMaterialName;

    @NotNull(message = "requiredQuantity is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "requiredQuantity must be positive")
    private BigDecimal requiredQuantity;
}