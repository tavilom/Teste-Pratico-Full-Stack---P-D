package com.estoquesped.backend.controller;

import com.estoquesped.backend.dto.production.ProductionResultDTO;
import com.estoquesped.backend.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping("/optimize")
    public ResponseEntity<ProductionResultDTO> optimize() {
        return ResponseEntity.ok(productionService.optimizeProduction());
    }
}