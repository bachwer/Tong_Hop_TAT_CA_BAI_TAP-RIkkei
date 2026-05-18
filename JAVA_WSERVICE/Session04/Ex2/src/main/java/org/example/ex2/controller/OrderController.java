package org.example.ex2.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    static class Order {

        private String orderId;
        private String customerName;
        private String address;
        private String productId;
        private int quantity;

        public Order() {
        }

        public Order(String orderId,
                     String customerName,
                     String address,
                     String productId,
                     int quantity) {

            this.orderId = orderId;
            this.customerName = customerName;
            this.address = address;
            this.productId = productId;
            this.quantity = quantity;
        }

        // Getters
        public String getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getAddress() {
            return address;
        }

        public String getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }

        // Setters
        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    @PostMapping
    public Order createOrder(@RequestBody Order orderRequest) {

        String generatedOrderId = UUID.randomUUID().toString();

        Order newOrder = new Order(
                generatedOrderId,
                orderRequest.getCustomerName(),
                orderRequest.getAddress(),
                orderRequest.getProductId(),
                orderRequest.getQuantity()
        );

        return newOrder;
    }
}