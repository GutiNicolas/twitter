package com.gutinicolas.twitter.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gutinicolas.twitter.exceptions.TwitterException;
import com.gutinicolas.twitter.model.api.LoginRequest;
import com.gutinicolas.twitter.model.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
public class AuthService {

    private static final String SECRET_KEY = "super_secret_key";
    private static final String AUTH_PREFIX = "Bearer";
    private static final String EXP_PREFIX = "exp";

    private long ttl = 900000;

    private  Algorithm algorithm;

    @Autowired
    UsersService usersService;

    JWTVerifier jwtVerifier;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(SECRET_KEY);
        jwtVerifier = JWT.require(algorithm).build();
    }

    public void performAuth(String authorization) {
        String token = authorization.replace(AUTH_PREFIX, "").strip();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Date exp = decodedJWT.getClaim(EXP_PREFIX).asDate();

        if (exp == null) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), String.format("Missing payload exp"));
        }

        if (exp.before(new Date(System.currentTimeMillis()))) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), String.format("Expired token, login required"));
        }
    }

    public Map<String, Object> login(LoginRequest loginRequest) {
        try {
            UserEntity userEntity = usersService.retrieveUser(loginRequest.getUsername());
            if ( !userEntity.getPassword().equalsIgnoreCase(loginRequest.getPassword()) ) {
                return Map.of("status", "error", "message", "Wrong username or password");
            }

            String token = generateToken(loginRequest.getUsername());
            return Map.of("status", "ok", "auth", "Bearer "+token);
        } catch ( TwitterException e) {
            return Map.of("status", "error", "message", "Wrong username or password");
        }
    }

    private String generateToken(String username) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + ttl))
                .withClaim("user", username)
                .sign(algorithm);
    }
}
