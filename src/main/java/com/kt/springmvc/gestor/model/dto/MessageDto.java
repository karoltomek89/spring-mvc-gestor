package com.kt.springmvc.gestor.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private Long id;
    private String sender;
    private String receiver;
    private String text;
    private Date dateDeletedSender;
    private Date dateDeletedReceiver;
    private String topic;

}
