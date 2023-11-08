package com.pragma.powerup.infrastructure.out.feign.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.feign.client.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.feign.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.feign.mapper.IUserEntityMapper;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class UserFeignAdapter implements IUserPersistencePort {

    private final IUserFeignClient userFeignClient;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public UserModel getUserById(Long userId) {
        UserEntity userEntity = userFeignClient.getUserById(userId);
        if (Objects.isNull(userEntity)) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toModel(userEntity);
    }
}