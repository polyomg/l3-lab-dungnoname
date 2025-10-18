package com.poly.lab6.controller;

import com.poly.lab6.dao.CategoryDAO;
import com.poly.lab6.entity.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryDAO dao;

    // ======= INDEX =======
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("item", new Category());
        model.addAttribute("items", dao.findAll());
        return "category/index";
    }

    // ======= CREATE =======
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("item") Category item,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("items", dao.findAll());
            model.addAttribute("message", "Vui lòng sửa các lỗi bên dưới!");
            return "category/index";
        }

        try {
            dao.save(item);
            model.addAttribute("message", "Thêm mới thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi thêm: " + e.getMessage());
        }

        model.addAttribute("item", new Category());
        model.addAttribute("items", dao.findAll());
        return "category/index";
    }

    // ======= EDIT =======
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("item", dao.findById(id).orElse(new Category()));
        model.addAttribute("items", dao.findAll());
        return "category/index";
    }

    // ======= UPDATE =======
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") Category item,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("items", dao.findAll());
            model.addAttribute("message", "Vui lòng sửa các lỗi bên dưới!");
            return "category/index";
        }

        try {
            dao.save(item);
            model.addAttribute("message", "Cập nhật thành công!");
        } catch (Exception e) {
            model.addAttribute("message", "Lỗi khi cập nhật: " + e.getMessage());
        }

        model.addAttribute("item", new Category());
        model.addAttribute("items", dao.findAll());
        return "category/index";
    }

    // ======= DELETE =======
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            dao.deleteById(id);
            model.addAttribute("message", "Xóa thành công!");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message",
                    "Không thể xóa Category này vì có sản phẩm hoặc đơn hàng đang sử dụng!");
        } catch (Exception e) {
            model.addAttribute("message", "Xóa thất bại: " + e.getMessage());
        }

        model.addAttribute("item", new Category());
        model.addAttribute("items", dao.findAll());
        return "category/index";
    }
}
