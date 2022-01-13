package com.example.resourceserver;

import java.util.UUID;

class Message {
    private String id = UUID.randomUUID().toString();
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}