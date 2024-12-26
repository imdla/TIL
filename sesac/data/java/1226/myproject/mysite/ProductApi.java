package com.example.myproject.mysite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductApi {
    List<Product> products = new ArrayList<>();
    private Long id = 0L;

    {
        products.add(new Product(++id, "initialProductName", 10000));
    }

    // create (url / 내용 / post)
    @GetMapping("/products/create")
    public Product createProduct() {
        Product product = new Product(++id, "productName", 10000);
        products.add(product);
        return product;
    }

    // read - 전체 조회
    @GetMapping("/products")
    public List<Product> readProducts() {
        return products;
    }

    // read - 단일 조회
    @GetMapping("/products/{id}")
    public Product readProduct(@PathVariable Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // update
    @GetMapping("/products/{id}/update")
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
    @GetMapping("/products/{id}/delete")
    public void deleteProduct(@PathVariable Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                products.remove(product);
            }
        }
    }
}
