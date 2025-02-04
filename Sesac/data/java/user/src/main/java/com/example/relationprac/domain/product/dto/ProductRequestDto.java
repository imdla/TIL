package com.example.relationprac.domain.product.dto;

import com.example.relationprac.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Getter
@NoArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "제품명은 필수입력입니다.")
    @Length(min = 3, max = 20, message = "제품명은 3자 이상 20자 이하입니다.")
    private String productName;

    public Product toEntity() {
        return Product.builder()
                .productName(this.productName)
                .build();
    }
}
