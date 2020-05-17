package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
