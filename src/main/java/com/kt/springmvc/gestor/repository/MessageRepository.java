package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
