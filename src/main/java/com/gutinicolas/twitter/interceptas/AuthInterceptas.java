package com.gutinicolas.twitter.interceptas;

import com.gutinicolas.twitter.exceptions.TwitterException;
import com.gutinicolas.twitter.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptas implements HandlerInterceptor {

    private static final String AUTH_HEADER = "Authorization";
    private static final String TEST_HEADER = "TestHeader";

    AuthService authService;

    public AuthInterceptas(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws TwitterException {
        if ( request.getHeader(TEST_HEADER) != null ) { return true; }
        String authorization = request.getHeader(AUTH_HEADER);
        if (StringUtils.isBlank(authorization)) {
            throw new TwitterException(HttpStatus.UNAUTHORIZED.value(), "No Auth provided and Login is needed");
        }
        this.authService.performAuth(authorization);

        return true;
    }
}
