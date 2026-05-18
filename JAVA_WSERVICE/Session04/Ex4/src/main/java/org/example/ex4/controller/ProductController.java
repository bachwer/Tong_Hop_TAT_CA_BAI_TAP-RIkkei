package org.example.ex4.controller;

import org.example.ex4.model.Product;
import org.example.ex4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // UPDATE PRODUCT
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable String productId,
            @RequestBody Product updatedProduct) {

        Product existingProduct = productService.findById(productId);

        // Kiểm tra ID tồn tại
        if (existingProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + productId + " not found");
        }

        // Cập nhật toàn bộ thông tin
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        Product savedProduct = productService.save(existingProduct);

        return ResponseEntity.ok(savedProduct);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable String productId) {

        Product existingProduct = productService.findById(productId);

        // Kiểm tra ID tồn tại
        if (existingProduct == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + productId + " not found");
        }

        productService.delete(productId);

        // Trả về thông báo xác nhận
        return ResponseEntity.ok(
                "Product with ID " + productId + " deleted successfully");
    }
}