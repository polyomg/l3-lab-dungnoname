package com.poly.lab3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaffController {
    //  http://localhost:8080/staff/detail
    @RequestMapping("/staff/detail")
    public String detail(Model model) {
        Staff staff = Staff.builder()
                .id("user@gmail.com")
                .fullname("nguyễn văn user")
                .level(2)
                .build();

        model.addAttribute("staff", staff);
        return "staff-detail"; // trỏ đến file staff-detail.html trong templates
    }
}
