package com.poly.lab5.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieService3 {

    private Integer num = 1;
    private final HttpServletResponse response;
    private final HttpServletRequest request;

    public CookieService3(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    // ✅ Tạo cookie mới
    public Cookie create(String name, String value, int expirySeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expirySeconds);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }

    // ✅ Đọc giá trị cookie
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

    // ✅ Xóa cookie
    public void delete(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    // ✅ Demo đếm số lần gọi (tùy chọn)
    public Integer getNum() {
        return num++;
    }
}
