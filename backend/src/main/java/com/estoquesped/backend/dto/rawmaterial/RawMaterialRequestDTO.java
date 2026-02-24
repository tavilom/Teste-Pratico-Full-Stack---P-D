package com.estoquesped.backend.dto.rawmaterial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RawMaterialRequestDTO {

    @NotNull(message = "code is required")
    @Size(min = 1, max = 50)
    private String code;

    @NotNull(message = "name is required")
    @Size(min = 1, max = 100)
    private String name;

    @NotNull(message = "stockQuantity is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "stockQuantity must be positive")
    private BigDecimal stockQuantity;

    @NotNull(message = "unit is required")
    @Size(min = 1, max = 10)
    private String unit;
}