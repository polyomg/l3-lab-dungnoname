package com.poly.lab2;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    // Trang form mặc định
    @GetMapping("/product/form")
    public String form(Model model) {
        // Product mặc định
        Product p = new Product("iPhone 30", 5000.0);
        model.addAttribute("p", p);

        // Để tránh lỗi null khi gọi ${product.name}
        model.addAttribute("product", new Product());

        return "product_form"; // trỏ tới file product_form.html
    }

    // Lưu sản phẩm khi submit form
    @PostMapping("/product/save")
    public String save(Model model, @ModelAttribute("product") Product p) {
        model.addAttribute("product", p);
        return "product_form";
    }

    // Danh sách sản phẩm mặc định
    @ModelAttribute("items")
    public List<Product> getItems() {
        return Arrays.asList(
                new Product("A", 1.0),
                new Product("B", 12.0),
                new Product("C", 20.0),
                new Product("D", 100.0)
        );
    }
}
