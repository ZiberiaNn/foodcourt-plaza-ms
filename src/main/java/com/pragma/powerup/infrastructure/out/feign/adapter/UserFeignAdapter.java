package com.pragma.powerup.infrastructure.out.feign.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.feign.client.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.feign.response.UserResponse;
import com.pragma.powerup.infrastructure.out.feign.mapper.IUserResponseMapper;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class UserFeignAdapter implements IUserPersistencePort {

    private final IUserFeignClient userFeignClient;
    private final IUserResponseMapper userEntityMapper;
    @Override
    public UserModel getUserByIdentityNumber(Integer identityNumber) {
        UserResponse userResponse = userFeignClient.getUserByIdentityNumber(identityNumber);
        if (Objects.isNull(userResponse)) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toModel(userResponse);
    }

    @Override
    public UserModel getUserByEmail(String email) {
        UserResponse userResponse = userFeignClient.getUserByEmail(email);
        if (Objects.isNull(userResponse)) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toModel(userResponse);
    }
}