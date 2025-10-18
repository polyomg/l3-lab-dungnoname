package com.poly.lab6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {

    @Id
    @NotBlank(message = "ID không được để trống!")
    @Size(max = 4, message = "ID chỉ được tối đa 4 ký tự!")
    private String id;

    @NotBlank(message = "Tên không được để trống!")
    private String name;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
