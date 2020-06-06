package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Grup;
import com.kt.springmvc.gestor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupRepository extends JpaRepository<Grup, Long> {

    List<Grup> findByUserId(Long id);

}
