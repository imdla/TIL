package com.example.myproject.mysite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductApi {
    List<Product> products = new ArrayList<>();
    private Long id = 0L;

    {
        products.add(new Product(++id, "initialProductName", 10000));
    }

    // create (url / 내용 / post)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product newProduct) {
        String productName = newProduct.getProductName();
        Integer price = newProduct.getPrice();

        Product product = new Product(++id, productName, price);
        products.add(product);
        return product;
    }

    // read - 전체 조회
    @GetMapping
    public List<Product> readProducts() {
        return products;
    }

    // read - 단일 조회
    @GetMapping("/{id}")
    public Product readProductById(@PathVariable Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // update
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                String updateProductName = updateProduct.getProductName();
                Integer updatePrice = updateProduct.getPrice();

                product.setProductName(updateProductName);
                product.setPrice(updatePrice);
                return product;
            }
        }
        return null;
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        Product removedProduct = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                removedProduct = product;
            }
        }
        products.remove(removedProduct);

    }

    @GetMapping("/paged")
    public List<Product> getPagedProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        // 1. 페이지네이션을 위한 더미데이터 추가
        for (int i = 1; i <= 20; i++) {
            String productName = "제목 " + i;
            Integer price = 100 + i*100;
            Product product = new Product(++id, productName, price);
            products.add(product);
        }

        // 2. 시작 인덱스와 끝 인덱스 계산
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, products.size());

        // 3. 페이지에 해당하는 데이터만 추출
        return products.subList(startIndex, endIndex);
    }
}
