package com.poly.lab5.config;

import com.poly.lab5.service.CookieService2;
import com.poly.lab5.service.CookieService3;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // Bean 1: CookieService2
    @Bean("CS1")
    public CookieService2 getCookieService2() {
        return new CookieService2();
    }

    // Bean 2: CookieService3
    @Bean("CS2")
    public CookieService2 getCookieService3(HttpServletRequest request, HttpServletResponse response) {
        return new CookieService2(request, response);
    }
}
