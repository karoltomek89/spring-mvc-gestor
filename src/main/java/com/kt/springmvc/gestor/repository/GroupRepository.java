package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
