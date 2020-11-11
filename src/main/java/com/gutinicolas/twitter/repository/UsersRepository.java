package com.gutinicolas.twitter.repository;

import com.gutinicolas.twitter.model.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersRepository {

    public UserEntity saveUser(UserEntity userEntity) {
        return userEntity;
    }

    public Optional<UserEntity> find(String id) {
        return Optional.empty();
    }

}
