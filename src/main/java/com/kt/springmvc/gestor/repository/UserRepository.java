package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRoleOrderByRole(String role);

    User findByNameOrderByName(String name);

    User findByGrupsOrderByGrups(String name);
}
