package com.pragma.powerup.infrastructure.out.feign.client;

import com.pragma.powerup.infrastructure.out.feign.request.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SMS-API", url = "http://localhost:8083")
public interface ISmsFeignClient {
    @PostMapping("/api/v1/sms/")
    String sendSms(@RequestBody SmsRequest smsRequest, @RequestHeader("Authorization") String authToken);
}