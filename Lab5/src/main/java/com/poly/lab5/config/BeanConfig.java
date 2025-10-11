package com.poly.lab5.config;

import com.poly.lab5.service.CookieService2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("CS1")
    public CookieService2 getCookieService2() {
        return new CookieService2();
    }
}
