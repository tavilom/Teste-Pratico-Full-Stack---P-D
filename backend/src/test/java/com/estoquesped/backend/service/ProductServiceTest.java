package com.estoquesped.backend.service;

import com.estoquesped.backend.dto.product.ProductCompositionDTO;
import com.estoquesped.backend.dto.product.ProductRequestDTO;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {

        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setCode("P001");
        dto.setName("Test Product");
        dto.setPrice(BigDecimal.valueOf(100));

        ProductCompositionDTO compDto = ProductCompositionDTO.builder()
                .rawMaterialId(1L)
                .requiredQuantity(BigDecimal.valueOf(50))
                .build();

        dto.setComposition(Collections.singletonList(compDto));

        RawMaterial rm = RawMaterial.builder()
                .id(1L)
                .name("Material A")
                .stockQuantity(BigDecimal.valueOf(1000))
                .build();

        when(rawMaterialRepository.findById(1L)).thenReturn(Optional.of(rm));


        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setCode(dto.getCode());
        savedProduct.setName(dto.getName());
        savedProduct.setPrice(dto.getPrice());


        ProductRawMaterial pr = new ProductRawMaterial();
        pr.setProduct(savedProduct);
        pr.setRawMaterial(rm);
        pr.setRequiredQuantity(compDto.getRequiredQuantity());
        savedProduct.setComposition(Collections.singletonList(pr));

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);


        var response = productService.create(dto);

        assertNotNull(response);
        assertEquals(dto.getCode(), response.getCode());
        assertEquals(dto.getName(), response.getName());
        assertEquals(dto.getPrice(), response.getPrice());
        assertEquals(1, response.getComposition().size());
        assertEquals(rm.getId(), response.getComposition().get(0).getRawMaterialId());
        assertEquals(rm.getName(), response.getComposition().get(0).getRawMaterialName());
        assertEquals(compDto.getRequiredQuantity(), response.getComposition().get(0).getRequiredQuantity());

        verify(productRepository, times(1)).save(any(Product.class));
    }
}