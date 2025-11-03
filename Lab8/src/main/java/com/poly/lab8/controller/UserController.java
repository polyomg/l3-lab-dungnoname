package com.poly.lab8.controller;

import com.poly.lab8.dao.UserDAO;
import com.poly.lab8.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDAO userDAO;

    @GetMapping("/index")
    public String index(Model model) {
        // Chuẩn bị form rỗng
        model.addAttribute("user", new User());

        // Tải danh sách
        List<User> items = userDAO.findAll();
        model.addAttribute("items", items);

        return "/user"; // Trả về user.html
    }

    // Xử lý nút "Create" [cite: 28]
    @PostMapping("/create")
    public String create(User user) {
        userDAO.save(user);
        return "redirect:/user/index";
    }

    // Xử lý nút "Update" [cite: 30]
    @PostMapping("/update")
    public String update(User user) {
        userDAO.save(user);
        return "redirect:/user/index";
    }

    // Xử lý nút "Delete" trong form [cite: 29]
    @PostMapping("/delete")
    public String delete(User user) {
        userDAO.deleteById(user.getId());
        return "redirect:/user/index";
    }

    // Xử lý nút "Reset" [cite: 20]
    @RequestMapping("/reset")
    public String reset() {
        return "redirect:/user/index";
    }

    // Xử lý link "Edit" trên bảng [cite: 29]
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        // Tìm user theo id
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            // Nếu không tìm thấy, quay về form rỗng
            model.addAttribute("user", new User());
        }

        // Tải lại danh sách
        List<User> items = userDAO.findAll();
        model.addAttribute("items", items);

        return "/user"; // Trả về user.html với dữ liệu đã load lên form
    }
}







package com.poly.lab8.controller;

import com.poly.lab8.Entity.User;
import com.poly.lab8.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDAO userDAO;

    /**
     * Hiển thị form và danh sách
     * Cập nhật: Thêm @RequestParam "keywords" để tìm kiếm
     */
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "keywords", required = false) String keywords) {
        // Chuẩn bị form rỗng
        model.addAttribute("user", new User());

        // Tải danh sách (có hoặc không có tìm kiếm)
        this.loadUserList(model, keywords);

        return "user"; // Trả về user.html
    }

    /**
     * Xử lý nút "Create"
     */
    @PostMapping("/create")
    public String create(User user) {
        userDAO.save(user);
        // Chuyển hướng về trang index (sẽ mất keywords)
        return "redirect:/user/index";
    }

    /**
     * Xử lý nút "Update"
     */
    @PostMapping("/update")
    public String update(User user) {
        userDAO.save(user);
        return "redirect:/user/index";
    }

    /**
     * Xử lý nút "Delete"
     */
    @PostMapping("/delete")
    public String delete(User user) {
        userDAO.deleteById(user.getId());
        return "redirect:/user/index";
    }

    /**
     * Xử lý nút "Reset"
     */
    @RequestMapping("/reset")
    public String reset() {
        return "redirect:/user/index";
    }

    /**
     * Xử lý link "Edit" trên bảng
     * Cập nhật: Thêm "keywords" để giữ trạng thái tìm kiếm
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model,
                       @RequestParam(name = "keywords", required = false) String keywords) {

        // 1. Tìm user theo id để đổ lên form
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
        } else {
            model.addAttribute("user", new User());
        }

        // 2. Tải lại danh sách (giữ nguyên kết quả tìm kiếm)
        this.loadUserList(model, keywords);

        return "user"; // Trả về user.html
    }

    /**
     * Phương thức private dùng chung để tải danh sách
     * (có tìm kiếm hoặc toàn bộ)
     */
    private void loadUserList(Model model, String keywords) {
        List<User> items;
        if (keywords != null && !keywords.isEmpty()) {
            // Nếu có keywords, thực hiện tìm kiếm
            items = userDAO.findByIdContainingOrFullnameContaining(keywords, keywords);
            model.addAttribute("keywords", keywords); // Gửi lại keywords ra view
        } else {
            // Nếu không, tải tất cả
            items = userDAO.findAll();
        }
        model.addAttribute("items", items);
    }
}

