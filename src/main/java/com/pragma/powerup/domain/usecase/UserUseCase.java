package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    @Override
    public UserModel getUser(Long userId) {
        return userPersistencePort.getUserById(userId);
    }
}
