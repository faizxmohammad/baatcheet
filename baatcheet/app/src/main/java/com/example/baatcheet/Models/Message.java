package com.example.baatcheet.Models;
import com.example.baatcheet.Models.Status;
public class Message {
    private String messageId , message , senderId , imageUrl;
    private long timeStamp;
//    private int feeling = -1;


    public Message() {

    }

    public Message(String message, String senderId, long timeStamp) {
        this.message = message;
        this.senderId = senderId;
        this.timeStamp = timeStamp;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

//    public void setFeeling(int feeling) {
//        this.feeling = feeling;
//
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderId() {
        return senderId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

//    public int getFeeling() {
//        return feeling;
//    }


}
