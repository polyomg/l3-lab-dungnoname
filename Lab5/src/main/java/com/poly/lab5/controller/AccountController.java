package com.poly.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.lab5.service.ParamService;
import com.poly.lab5.service.CookieService;
import com.poly.lab5.service.SessionService;

@Controller
public class AccountController {

    @Autowired
    ParamService paramService;
    @Autowired
    CookieService cookieService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2() {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        if (un.equals("poly") && pw.equals("123")) {
            // Lưu session
            sessionService.set("username", un);

            // Ghi nhớ tài khoản
            if (rm) {
                cookieService.add("user", un, 24 * 10); // 10 ngày
            } else {
                cookieService.remove("user");
            }
            return "/account/success";
        }

        return "/account/login"; // Sai thì quay lại login
    }
}