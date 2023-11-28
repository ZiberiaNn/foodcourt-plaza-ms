package com.pragma.powerup.infrastructure.out.feign.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SmsRequest {
    @Schema(example = "Hello, this is a test message")
    private String message;
    @Schema(example = "+573001234567")
    private String destinationPhoneNumber;
}
