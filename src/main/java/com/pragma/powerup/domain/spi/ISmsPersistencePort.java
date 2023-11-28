package com.pragma.powerup.domain.spi;

import com.pragma.powerup.infrastructure.out.feign.request.SmsRequest;

public interface ISmsPersistencePort {
    String sendSms(SmsRequest smsRequest, String authToken);
}
