package com.poly.lab8.dao;

import com.poly.lab8.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, String> {

    List<User> findByIdContainingOrFullnameContaining(String id, String fullname);
}