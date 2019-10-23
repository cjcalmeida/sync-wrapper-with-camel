package com.cristiano.estudos.sync.wrapper.processor.demo.domain;

import java.util.UUID;

public class Consumer {

    private Integer id;
    private String fullName;
    private Integer yearOld;
    private String messageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getYearOld() {
        return yearOld;
    }

    public void setYearOld(Integer yearOld) {
        this.yearOld = yearOld;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
