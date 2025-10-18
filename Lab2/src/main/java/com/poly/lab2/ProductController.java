package com.poly.lab2;

import com.poly.lab2.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    // 🟢 Hiển thị form sản phẩm
    @GetMapping("/product/form")
    public String form(Model model) {
        // Sản phẩm mặc định
        Product defaultProduct = new Product("iPhone 30", 5000.0);
        model.addAttribute("p", defaultProduct);

        // Để tránh lỗi null khi binding dữ liệu
        model.addAttribute("product", new Product());

        return "product_form"; // trỏ đến product_form.html
    }

    // 🟢 Lưu sản phẩm khi nhấn nút Save
    @PostMapping("/product/save")
    public String save(Model model, @ModelAttribute("product") Product p) {
        model.addAttribute("product", p);

        // Thêm lại sản phẩm mặc định
        Product defaultProduct = new Product("iPhone 30", 5000.0);
        model.addAttribute("p", defaultProduct);

        // Truyền danh sách sản phẩm (cho phần danh sách hiển thị)
        model.addAttribute("items", getItems());

        return "product_form";
    }

    // 🟢 Danh sách sản phẩm mặc định
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
