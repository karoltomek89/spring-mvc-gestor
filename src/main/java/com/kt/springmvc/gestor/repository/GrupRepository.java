package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Grup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupRepository extends JpaRepository<Grup, Long> {
}
