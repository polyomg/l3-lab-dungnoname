package com.poly.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ctrl")
public class OkController {

    @RequestMapping("/ok")
    public String ok() {
        return "ok"; // trỏ tới ok.html
    }

    @PostMapping("/ok")
    public String m1(Model model) {
        model.addAttribute("message", "OK 1 được gọi (m1)");
        return "ok";
    }

    @GetMapping("/ok")
    public String m2(Model model) {
        model.addAttribute("message", "OK 2 được gọi (m2)");
        return "ok";
    }

    @RequestMapping(value = "/ok", params = "x")
    public String m3(Model model) {
        model.addAttribute("message", "OK 3 được gọi (m3)");
        return "ok";
    }
}
