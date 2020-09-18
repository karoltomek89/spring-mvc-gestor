package com.kt.springmvc.gestor.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    @NotNull
    private String receiver;

    @NotNull
    private String text;

    private Date dateDeletedSender;

    private Date dateDeletedReceiver;

    @NotNull
    private String topic;

    public Message() {
    }
}