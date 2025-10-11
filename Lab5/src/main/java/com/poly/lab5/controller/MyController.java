//package com.poly.lab5.controller;
//
//import com.poly.lab5.service.CookieService2;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class MyController {
//    @Autowired
//    HttpServletRequest request;
//
////    @Autowired
////    CookieService cookieService;
//
//    @Qualifier("CS2")
//    @Autowired
//    CookieService2 cookieService;
//
//
//    @RequestMapping("/my-url")
//    public String method(Model model) {
//        String uri = request.getParameter("fullname");
//
//        String upcaseFullname = uri.toUpperCase();
//        cookieService.create("name","Poly" + upcaseFullname,60);
//        model.addAttribute("fullname", upcaseFullname);
//        return "/account/page";
//    }
//
//    @GetMapping("/my-url-get")
//    public String methodGet(Model model) {
//        String getcookie = cookieService.getValue("name");
//
//        model.addAttribute("fullname", getcookie + " - Get - " + cookieService.getNum().toString());
//        return "/account/page";
//    }
//}