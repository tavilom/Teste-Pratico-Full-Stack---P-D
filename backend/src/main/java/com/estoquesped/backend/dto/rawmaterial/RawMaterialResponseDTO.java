package com.estoquesped.backend.dto.rawmaterial;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RawMaterialResponseDTO {

    private Long id;
    private String code;
    private String name;
    private BigDecimal stockQuantity;
    private String unit;
}