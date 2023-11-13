package com.pragma.powerup.infrastructure.security.service;

import com.pragma.powerup.domain.model.auth.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@RequiredArgsConstructor
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserPersistencePort userPersistencePort;
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userPersistencePort.getUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(UserModel user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }
}