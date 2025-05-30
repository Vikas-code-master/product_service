package com.vg.productservice.service;

import com.vg.productservice.config.OrderClient;
import com.vg.productservice.config.UserClient;
import com.vg.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceWithFeign {

//    @Autowired
//    private ProductRepository productRepository;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private UserClient userClient;

    public Object getProductWithOrderDetails(Integer productId, Integer orderId) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product product = ProductService.inMemoryDB.get(productId);
        Object orderDetails = orderClient.getOrderDetails(orderId);
        return new Object() {
            public Product productDetails = product;
            public Object order = orderDetails;
        };
    }

    public Object getProductWithUserDetails(Integer productId, Integer userId) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product product = ProductService.inMemoryDB.get(productId);
        Object userDetails = userClient.getUserDetails(userId);
        return new Object() {
            public Product productDetails = product;
            public Object user = userDetails;
        };
    }
}

