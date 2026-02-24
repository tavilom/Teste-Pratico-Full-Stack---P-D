package com.estoquesped.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "raw_materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(name = "stockQuantity", nullable = false)
    private BigDecimal stockQuantity;

    @Column(nullable = false)
    private String unit;

    @OneToMany(mappedBy = "rawMaterial")
    private List<ProductRawMaterial> products;
}