package org.example.ex4.service;

import jakarta.annotation.PostConstruct;
import org.example.ex4.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {

    private final Map<String, Product> products = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        save(new Product("P001", "Keyboard", 45.0, 12));
        save(new Product("P002", "Mouse", 25.0, 30));
    }

    public Product findById(String productId) {
        return products.get(productId);
    }

    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public void delete(String productId) {
        products.remove(productId);
    }

    public Collection<Product> findAll() {
        return products.values();
    }
}

