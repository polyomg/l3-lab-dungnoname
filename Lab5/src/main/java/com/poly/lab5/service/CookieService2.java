package com.poly.lab5.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class CookieService2 {
    private Integer num = 1;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    public Cookie create(String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }

    public Cookie add(String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expiry);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }

    public String getValue(String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public void delete(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public Integer getNum() {
        return num++;
    }
}
