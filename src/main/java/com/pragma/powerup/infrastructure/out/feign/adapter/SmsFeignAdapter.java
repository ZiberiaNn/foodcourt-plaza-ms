package com.pragma.powerup.infrastructure.out.feign.adapter;

import com.pragma.powerup.domain.spi.ISmsPersistencePort;
import com.pragma.powerup.infrastructure.out.feign.client.ISmsFeignClient;
import com.pragma.powerup.infrastructure.out.feign.request.SmsRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SmsFeignAdapter implements ISmsPersistencePort {
    private final ISmsFeignClient smsFeignClient;

    @Override
    public String sendSms(SmsRequest smsRequest, String authToken) {
        return smsFeignClient.sendSms(smsRequest, authToken);
    }
}
