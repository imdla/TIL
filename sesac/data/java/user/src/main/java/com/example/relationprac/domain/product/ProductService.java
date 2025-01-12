package com.example.relationprac.domain.product;

import com.example.relationprac.domain.product.dto.ProductRequestDto;
import com.example.relationprac.domain.product.dto.ProductResponseDto;
import com.example.relationprac.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    // CREATED
    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        Product product = requestDto.toEntity();
        return ProductResponseDto.from(productRepository.save(product));
    }

    // UPDATE
    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);
        product.update(requestDto);

        return ProductResponseDto.from(product);
    }

    // DELETE
    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);
        productRepository.delete(product);
    }
}
