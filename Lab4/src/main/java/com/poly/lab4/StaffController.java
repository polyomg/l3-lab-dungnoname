package com.poly.lab4;

import com.poly.java.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StaffController {

    @RequestMapping("/staff/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "staff-create";
    }

    @RequestMapping("/staff/create/save")
    public String createSave(Model model,
                             @ModelAttribute("staff") Staff staff,
                             @RequestPart("photo_file") MultipartFile photoFile) {
        // Nếu có upload file thì thay tên file cho thuộc tính photo
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }
        model.addAttribute("message", "Xin chào " + staff.getFullname());
        return "staff-create";
    }
}
