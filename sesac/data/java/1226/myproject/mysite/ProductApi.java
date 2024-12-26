package com.example.myproject.mysite;

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
    public Product createProduct() {
        Product product = new Product(++id, "productName", 10000);
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
    public Product updateProduct(@PathVariable Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setProductName("New productName");
                product.setPrice(50000);
            }
        }
        return null;
    }

    // delete
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        Product removedProduct = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                removedProduct = product;
            }
        }
        products.remove(removedProduct);

    }
}
