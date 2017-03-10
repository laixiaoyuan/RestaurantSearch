package edu.xlaiscu.chihuo;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Lexie on 3/6/17.
 */

public class Restaurant implements Serializable{
    String name;
    String address;
    String type;
    Bitmap image;
    Bitmap rating;
    String phone;
    String fullAddress;
    boolean curStatus;

    public Restaurant(String name, String address, String type, Bitmap image) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getType() {
        return type;
    }
    public Bitmap getImage() {return image;}
    public void setRating(Bitmap rating) {
        this.rating = rating;
    }
    public Bitmap getRating() {
        return rating;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
    public String getFullAddress() {
        return fullAddress;
    }
    public void setCurStatus(boolean curStatus) {
        this.curStatus = curStatus;
    }
    public boolean getCurStatus() {
        return curStatus;
    }

}
