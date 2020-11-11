package com.gutinicolas.twitter.repository;

import com.gutinicolas.twitter.model.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public UserEntity saveUser(UserEntity userEntity) {
        mongoTemplate.save(userEntity);
        return userEntity;
    }

    public Optional<UserEntity> find(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, UserEntity.class));
    }

}
