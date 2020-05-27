package com.kt.springmvc.gestor.model.dto;

import java.util.Date;

public class MessageDto {
    private Long id;
    private String sender;
    private String receiver;
    private String text;
    private Date dateDeletedSender;
    private Date dateDeletedReceiver;
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateDeletedSender() {
        return dateDeletedSender;
    }

    public void setDateDeletedSender(Date dateDeletedSender) {
        this.dateDeletedSender = dateDeletedSender;
    }

    public Date getDateDeletedReceiver() {
        return dateDeletedReceiver;
    }

    public void setDateDeletedReceiver(Date dateDeletedReceiver) {
        this.dateDeletedReceiver = dateDeletedReceiver;
    }
}
