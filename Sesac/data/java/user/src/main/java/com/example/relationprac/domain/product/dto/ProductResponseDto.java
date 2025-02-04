package com.example.relationprac.domain.product.dto;

import com.example.relationprac.domain.product.Product;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductResponseDto {
    private final Long id;
    private final String productName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductResponseDto from(Product entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
