package com.poly.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @Column(length = 30)
    private String username;

    private String password;
    private String fullname;
    private String email;
    private String photo;
    private boolean activated;
    private boolean admin;

    @OneToMany(mappedBy = "account")
    List<Order> orders;
}
