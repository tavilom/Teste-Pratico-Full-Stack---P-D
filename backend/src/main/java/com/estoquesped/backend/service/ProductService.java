package com.estoquesped.backend.service;

import com.estoquesped.backend.model.RawMaterial;
import com.estoquesped.backend.dto.product.ProductCompositionDTO;
import com.estoquesped.backend.dto.product.ProductRequestDTO;
import com.estoquesped.backend.dto.product.ProductResponseDTO;
import com.estoquesped.backend.model.Product;
import com.estoquesped.backend.model.ProductRawMaterial;
import com.estoquesped.backend.repository.RawMaterialRepository;
import com.estoquesped.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductResponseDTO create(ProductRequestDTO dto) {
        try {
            Product product = new Product();
            product.setCode(dto.getCode());
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());

            List<ProductRawMaterial> composition = dto.getComposition().stream()
                    .map(compDto -> {
                        RawMaterial rawMaterial = rawMaterialRepository.findById(compDto.getRawMaterialId())
                                .orElseThrow(() -> new RuntimeException(
                                        "Raw material not found with ID: " + compDto.getRawMaterialId()));

                        ProductRawMaterial pr = new ProductRawMaterial();
                        pr.setProduct(product);
                        pr.setRawMaterial(rawMaterial);
                        pr.setRequiredQuantity(compDto.getRequiredQuantity());
                        return pr;
                    })
                    .collect(Collectors.toList());

            product.setComposition(composition);

            Product saved = productRepository.save(product);
            return toResponseDTO(saved);

        } catch (Exception e) {
            // Log detalhado no console
            e.printStackTrace();
            // Lançar RuntimeException com mensagem clara para o ControllerAdvice capturar
            throw new RuntimeException("Failed to create product: " + e.getMessage());
        }
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return toResponseDTO(product);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        product.getComposition().clear();

        List<ProductRawMaterial> newComposition = dto.getComposition().stream()
                .map(compDto -> {
                    RawMaterial rawMaterial = rawMaterialRepository.findById(compDto.getRawMaterialId())
                            .orElseThrow(() -> new RuntimeException("Raw material not found"));

                    ProductRawMaterial pr = new ProductRawMaterial();
                    pr.setProduct(product);
                    pr.setRawMaterial(rawMaterial);
                    pr.setRequiredQuantity(compDto.getRequiredQuantity());
                    return pr;
                })
                .collect(Collectors.toList());

        product.getComposition().addAll(newComposition);

        Product updated = productRepository.save(product);

        return toResponseDTO(updated);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .composition(
                        product.getComposition().stream()
                                .map(pr -> ProductCompositionDTO.builder()
                                        .rawMaterialId(pr.getRawMaterial().getId())
                                        .rawMaterialName(pr.getRawMaterial().getName())
                                        .requiredQuantity(pr.getRequiredQuantity())
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }
}