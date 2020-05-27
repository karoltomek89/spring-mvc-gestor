package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(String role);

    User findByName(String name);
}
