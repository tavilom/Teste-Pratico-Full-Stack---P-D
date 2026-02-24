package com.estoquesped.backend.service;

import com.estoquesped.backend.dto.production.ItemProductionDTO;
import com.estoquesped.backend.dto.production.ProductionResultDTO;
import com.estoquesped.backend.model.Product;
import com.estoquesped.backend.model.ProductRawMaterial;
import com.estoquesped.backend.model.RawMaterial;
import com.estoquesped.backend.repository.ProductRepository;
import com.estoquesped.backend.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductionResultDTO optimizeProduction() {

        List<Product> products = productRepository.findAllByOrderByPriceDesc();
        List<RawMaterial> rawMaterials = rawMaterialRepository.findAll();


        Map<Long, BigDecimal> availableStock = new HashMap<>();
        for (RawMaterial rm : rawMaterials) {
            availableStock.put(rm.getId(), rm.getStockQuantity());
        }

        List<ItemProductionDTO> productionPlan = new ArrayList<>();
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Product product : products) {

            int producibleQuantity = calculateProducibleQuantity(product, availableStock);

            if (producibleQuantity > 0) {

                updateStock(product, availableStock, producibleQuantity);

                BigDecimal productValue = product.getPrice().multiply(BigDecimal.valueOf(producibleQuantity));
                totalValue = totalValue.add(productValue);

                productionPlan.add(ItemProductionDTO.builder()
                        .productId(product.getId())
                        .productName(product.getName())
                        .producedQuantity(producibleQuantity)
                        .totalProductValue(productValue)
                        .build());
            }
        }

        return ProductionResultDTO.builder()
                .producedProducts(productionPlan)
                .totalValue(totalValue)
                .build();
    }

    private int calculateProducibleQuantity(Product product, Map<Long, BigDecimal> availableStock) {

        int maxQuantity = Integer.MAX_VALUE;

        for (ProductRawMaterial comp : product.getComposition()) {

            BigDecimal stock = availableStock.get(comp.getRawMaterial().getId());
            BigDecimal required = comp.getRequiredQuantity();

            int possible = stock.divide(required, 0, RoundingMode.DOWN).intValue();
            maxQuantity = Math.min(maxQuantity, possible);
        }

        return maxQuantity;
    }

    private void updateStock(Product product, Map<Long, BigDecimal> availableStock, int quantityProduced) {

        for (ProductRawMaterial comp : product.getComposition()) {
            Long rawId = comp.getRawMaterial().getId();
            BigDecimal currentStock = availableStock.get(rawId);
            BigDecimal consumption = comp.getRequiredQuantity().multiply(BigDecimal.valueOf(quantityProduced));
            availableStock.put(rawId, currentStock.subtract(consumption));
        }
    }
}