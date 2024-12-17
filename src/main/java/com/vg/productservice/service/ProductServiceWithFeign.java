package com.vg.productservice.service;

import com.vg.productservice.config.OrderClient;
import com.vg.productservice.config.UserClient;
import com.vg.productservice.dao.ProductRepository;
import com.vg.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceWithFeign {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private UserClient userClient;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Object getProductWithOrderDetails(String productId, String orderId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Object orderDetails = orderClient.getOrderDetails(orderId);
        return new Object() {
            public Product productDetails = product;
            public Object order = orderDetails;
        };
    }

    public Object getProductWithUserDetails(String productId, String userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Object userDetails = userClient.getUserDetails(userId);
        return new Object() {
            public Product productDetails = product;
            public Object user = userDetails;
        };
    }
}

