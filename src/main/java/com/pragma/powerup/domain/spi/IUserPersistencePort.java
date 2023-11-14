package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.auth.UserModel;

public interface IUserPersistencePort {

    UserModel getUserByIdentityNumber(Integer identityNumber);

    UserModel getUserByEmail(String email);
}
