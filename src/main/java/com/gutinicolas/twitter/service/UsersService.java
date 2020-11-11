package com.gutinicolas.twitter.service;

import com.gutinicolas.twitter.exceptions.TwitterException;
import com.gutinicolas.twitter.model.api.RegisterRequest;
import com.gutinicolas.twitter.model.entities.UserEntity;
import com.gutinicolas.twitter.repository.UsersRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public boolean userExists(String userId) {
        return usersRepository.find(userId).isPresent();
    }

    public UserEntity register(RegisterRequest registerRequest) {
        if (userExists(registerRequest.getDesiredUsername())) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(),
                    registerRequest.getDesiredUsername()+" already exists, chose a new one.");
        }

        if (!StringUtils.equalsIgnoreCase(registerRequest.getPassword(), registerRequest.getConfirmPassword())) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), "Passwords not match.");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerRequest.getDesiredUsername());
        newUser.setPassword(registerRequest.getPassword());

        return usersRepository.saveUser(newUser);
    }

    public UserEntity retrieveUser(String username) throws TwitterException {
        Optional<UserEntity> optUser = usersRepository.find(username);

        return optUser.orElseThrow(TwitterException::new);
    }

}
