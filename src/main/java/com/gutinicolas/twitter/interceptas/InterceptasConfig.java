package com.gutinicolas.twitter.interceptas;

import com.gutinicolas.twitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InterceptasConfig implements WebMvcConfigurer {

    @Autowired
    AuthService authService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptas(authService))
                .order(Ordered.HIGHEST_PRECEDENCE)
                .addPathPatterns("/tweets/**");
    }
}
