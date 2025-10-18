package com.poly.lab5.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    /**
     * Đọc chuỗi giá trị của tham số
     */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? value : defaultValue;
    }

    /**
     * Đọc số nguyên giá trị của tham số
     */
    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Đọc số thực giá trị của tham số
     */
    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null) ? Double.parseDouble(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Đọc giá trị boolean của tham số
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value == null) return defaultValue;
        return value.equalsIgnoreCase("true") || value.equals("1");
    }

    /**
     * Đọc giá trị thời gian của tham số
     */
    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value == null) return null;
        try {
            return new SimpleDateFormat(pattern).parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Sai định dạng thời gian: " + pattern);
        }
    }

    /**
     * Lưu file upload vào thư mục
     */
    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) return null;
        try {
            // Lấy đường dẫn tuyệt đối từ webroot
            File dir = new File(request.getServletContext().getRealPath(path));
            if (!dir.exists()) {
                dir.mkdirs(); // tạo thư mục nếu chưa tồn tại
            }
            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}