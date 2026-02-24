package com.estoquesped.backend.service;

import com.estoquesped.backend.dto.production.ProductionResultDTO;
import com.estoquesped.backend.model.Product;
import com.estoquesped.backend.model.ProductRawMaterial;
import com.estoquesped.backend.model.RawMaterial;
import com.estoquesped.backend.repository.ProductRepository;
import com.estoquesped.backend.repository.RawMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductionServiceTest {

    @InjectMocks
    private ProductionService productionService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOptimizeProduction() {

        RawMaterial rm1 = RawMaterial.builder()
                .id(1L)
                .code("RM001")
                .name("Material 1")
                .stockQuantity(BigDecimal.valueOf(1000))
                .unit("g")
                .build();


        Product p1 = Product.builder()
                .id(1L)
                .code("P001")
                .name("Product A")
                .price(BigDecimal.valueOf(50))
                .composition(new ArrayList<>())
                .build();


        ProductRawMaterial pr1 = ProductRawMaterial.builder()
                .product(p1)
                .rawMaterial(rm1)
                .requiredQuantity(BigDecimal.valueOf(20))
                .build();

        p1.getComposition().add(pr1);


        when(productRepository.findAllByOrderByPriceDesc()).thenReturn(List.of(p1));
        when(rawMaterialRepository.findAll()).thenReturn(List.of(rm1));


        ProductionResultDTO result = productionService.optimizeProduction();

        assertNotNull(result);
        assertEquals(1, result.getProducedProducts().size());
        assertEquals(50, result.getProducedProducts().get(0).getProducedQuantity());
        assertEquals(BigDecimal.valueOf(2500), result.getProducedProducts().get(0).getTotalProductValue());
        assertEquals(BigDecimal.valueOf(2500), result.getTotalValue());
    }
}