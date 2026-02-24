package com.estoquesped.backend.dto.production;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductionResultDTO {

    private List<ItemProductionDTO> producedProducts;
    private BigDecimal totalValue;
}