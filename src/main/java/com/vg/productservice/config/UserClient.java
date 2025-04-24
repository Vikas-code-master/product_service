package com.vg.productservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://13.60.9.168:8080/api/users")
public interface UserClient {
    @GetMapping("/{userId}")
    Object getUserDetails(@PathVariable Integer userId);
}

