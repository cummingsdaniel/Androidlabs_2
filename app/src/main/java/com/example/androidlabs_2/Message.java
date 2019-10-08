package com.example.androidlabs_2;

public class Message {
    private String chat;
    private boolean isRecieved;
    private long id;


    public Message() {

    }
    public Message(String s, boolean isRecieved) {
        this.chat=s;
        this.isRecieved =isRecieved;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getChat() {
        return chat;
    }

    public boolean isSent() {
        return isRecieved;
    }

    public void setSent(boolean isRecieved) {
        this.isRecieved = isRecieved;
    }

    @Override
    public String toString() {
        return "Message{" +
                "chat='" + chat + '\'' +
                ", sent=" + isRecieved +
                '}';
    }
}
