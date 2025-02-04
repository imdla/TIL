package com.example.relationprac.domain.product;

import com.example.relationprac.domain.product.dto.ProductRequestDto;
import com.example.relationprac.domain.product.dto.ProductResponseDto;
import com.example.relationprac.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // CREATED
    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> addProduct(@Valid @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        "제품이 성공적으로 등록되었습니다.",
                        "CREATED",
                        productService.addProduct(requestDto)
                ));
    }

    // UPDATE
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@RequestParam Long productId, @Valid @RequestBody ProductRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.ok(
                productService.updateProduct(productId, requestDto)
        ));
    }

    // DELETE
    @DeleteMapping("/{productId}")
    public void deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        ResponseEntity.ok(ApiResponse.ok(
                "제품이 성공적으로 삭제되었습니다."
                ));
    }
}
