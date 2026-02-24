package com.estoquesped.backend.dto.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;

@Data
public class ProductRequestDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String code;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull
    private List<ProductCompositionDTO> composition;
}
