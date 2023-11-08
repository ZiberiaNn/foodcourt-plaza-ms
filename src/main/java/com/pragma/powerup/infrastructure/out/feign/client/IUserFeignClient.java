package com.pragma.powerup.infrastructure.out.feign.client;

import com.pragma.powerup.infrastructure.out.feign.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-API", url = "http://localhost:8081")
public interface IUserFeignClient {
    @GetMapping("/api/v1/users/{userId}")
    UserResponse getUserById(@PathVariable Long userId);
}