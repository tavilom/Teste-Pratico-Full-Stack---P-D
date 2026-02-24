package com.estoquesped.backend.service;

import com.estoquesped.backend.dto.rawmaterial.RawMaterialRequestDTO;
import com.estoquesped.backend.dto.rawmaterial.RawMaterialResponseDTO;
import com.estoquesped.backend.model.RawMaterial;
import com.estoquesped.backend.repository.RawMaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository repository;

    public RawMaterialResponseDTO create(RawMaterialRequestDTO dto) {

        if (repository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("A raw material with this code already exists.");
        }

        RawMaterial rawMaterial = RawMaterial.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .stockQuantity(dto.getStockQuantity())
                .unit(dto.getUnit())
                .build();

        RawMaterial saved = repository.save(rawMaterial);

        return toResponseDTO(saved);
    }

    public List<RawMaterialResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RawMaterialResponseDTO findById(Long id) {
        RawMaterial rawMaterial = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Raw material not found"));

        return toResponseDTO(rawMaterial);
    }

    public RawMaterialResponseDTO update(Long id, RawMaterialRequestDTO dto) {

        RawMaterial rawMaterial = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Raw material not found"));

        rawMaterial.setCode(dto.getCode());
        rawMaterial.setName(dto.getName());
        rawMaterial.setStockQuantity(dto.getStockQuantity());
        rawMaterial.setUnit(dto.getUnit());

        return toResponseDTO(repository.save(rawMaterial));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Raw material not found");
        }
        repository.deleteById(id);
    }

    private RawMaterialResponseDTO toResponseDTO(RawMaterial rawMaterial) {
        return RawMaterialResponseDTO.builder()
                .id(rawMaterial.getId())
                .code(rawMaterial.getCode())
                .name(rawMaterial.getName())
                .stockQuantity(rawMaterial.getStockQuantity())
                .unit(rawMaterial.getUnit())
                .build();
    }
}