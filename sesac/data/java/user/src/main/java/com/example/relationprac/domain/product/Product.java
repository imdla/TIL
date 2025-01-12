package com.example.relationprac.domain.product;

import com.example.relationprac.domain.product.dto.ProductRequestDto;
import com.example.relationprac.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String productName;

    @Builder
    public Product(String productName) {
        this.productName = productName;
    }

    public Product update(ProductRequestDto requestDto) {
        this.productName = requestDto.getProductName();
        return this;
    }
}
