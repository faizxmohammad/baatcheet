package com.example.baatcheet.Models;
import com.example.baatcheet.Models.Message;
public class Status {
    private  String imageUrl;
    private long timeStamp;

    public Status() {
    }

    public Status(String imageUrl, long timeStamp) {
        this.imageUrl = imageUrl;
        this.timeStamp = timeStamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
