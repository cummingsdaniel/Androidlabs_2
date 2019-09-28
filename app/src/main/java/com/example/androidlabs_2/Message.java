package com.example.androidlabs_2;

public class Message {
    private String chat;
    private boolean sent;
    public Message() {

    }
    public Message(String s, boolean sent) {
        this.chat=s;
        this.sent=sent;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getChat() {
        return chat;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "chat='" + chat + '\'' +
                ", sent=" + sent +
                '}';
    }
}
