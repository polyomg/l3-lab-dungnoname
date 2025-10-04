package com.poly.lab4;

import com.poly.lab4.entity.Staff2;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StaffValidateController {

    // Hiển thị form
    @GetMapping("/staff/validate/form1")
    public String validateForm(Model model, @ModelAttribute("staff") Staff2 staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "staff-validate"; // đây là file staff-validate.html trong templates
    }

    // Xử lý submit
    @PostMapping("/staff/validate/save1")
    public String validateSave(Model model,
                               @RequestPart("photo_file") MultipartFile photoFile,
                               @Valid @ModelAttribute("staff") Staff2 staff,
                               Errors errors) {

        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename()); // lấy tên file gốc
        }

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        } else {
            model.addAttribute("message", "Dữ liệu đã nhập đúng!");
        }

        return "staff-validate";
    }
}
