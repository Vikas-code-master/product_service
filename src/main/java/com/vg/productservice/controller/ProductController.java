package com.vg.productservice.controller;

import com.vg.productservice.model.Product;
import com.vg.productservice.service.ProductService;
import com.vg.productservice.service.ProductServiceWithFeign;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductServiceWithFeign productServiceWithFeign;

    @PostMapping("/create")
    public Product createProduct(@RequestBody @Valid Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody @Valid Product product) {
        return productService.updateProduct(id, product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}/order/{orderId}")
    public Object getProductWithOrderDetails(@PathVariable String id, @PathVariable String orderId) {
        return productServiceWithFeign.getProductWithOrderDetails(id, orderId);
    }

    @GetMapping("/{id}/user/{userId}")
    public Object getProductWithUserDetails(@PathVariable String id, @PathVariable String userId) {
        return productServiceWithFeign.getProductWithUserDetails(id, userId);
    }


}


