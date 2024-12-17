package com.vg.productservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "http://localhost:8082/api/orders")
public interface OrderClient {
    @GetMapping("/{orderId}")
    Object getOrderDetails(@PathVariable String orderId);
}
