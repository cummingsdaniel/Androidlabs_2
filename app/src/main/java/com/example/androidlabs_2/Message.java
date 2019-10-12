package com.example.androidlabs_2;

public class Message {
    private String chat;
    private boolean isSent;
    private long id;


    public Message() {

    }
    public Message(long id, String s, boolean isSent) {
        this.chat=s;
        this.isSent =isSent;
        this.id=id;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getChat() {
        return chat;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean isSent) {
        this.isSent = isSent;
    }
    public Long getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Message{" +
                "chat='" + chat + '\'' +
                ", sent=" + isSent +
                '}';
    }
}
