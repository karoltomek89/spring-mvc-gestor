package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Message;
import com.kt.springmvc.gestor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySender(String sender);

    List<Message> findByReceiver(String receiver);

}
