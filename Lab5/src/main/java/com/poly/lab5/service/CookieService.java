package com.poly.lab5.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    private Integer num = 1;

    // ✅ Tạo và gửi cookie về client
    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(hours * 60 * 60); // đổi giờ -> giây
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }

    // ✅ Đọc cookie theo tên
    public Cookie get(String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    // ✅ Đọc giá trị cookie theo tên
    public String getValue(String name) {
        Cookie cookie = get(name);
        return cookie != null ? cookie.getValue() : "";
    }

    // ✅ Xóa cookie khỏi client
    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0); // 0 giây = xóa ngay
        response.addCookie(cookie);
    }

    // ✅ (phụ) dùng để test hoặc đếm số lần gọi
    public Integer getNum() {
        return num++;
    }
}
