package edu.xlaiscu.chihuo;

import android.graphics.Bitmap;

/**
 * Created by Lexie on 3/6/17.
 */

public class Restaurant {
    String name;
    String address;
    String type;
    Bitmap image;
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
}
