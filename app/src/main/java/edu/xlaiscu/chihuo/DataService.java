package edu.xlaiscu.chihuo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexie on 3/6/17.
 */

public class DataService {
//    List<Restaurant> restaurants;
    Context mContext;

    public DataService() {
//        mContext = context;
//        restaurants = new ArrayList<Restaurant>();
    }

    public List<Restaurant> getRestaurantData() {
//        List<Restaurant> restaurants = new ArrayList<Restaurant>();
//        for (int i = 0; i < 10; i++) {
//            restaurants.add(new Restaurant("Cai Xiang Gen", "San Francisco", "Chinese", null));
//        }
        YelpApi yelp = new YelpApi();
        String jsonResponse = yelp.searchForBusinessesByLocation("dinner", "San Francisco, CA", 20);
        return parseResponse(jsonResponse);
    }
    private List<Restaurant> parseResponse(String jsonResponse) {
        try{
            JSONObject json = new JSONObject(jsonResponse);
            JSONArray businesses = json.getJSONArray("businesses");
            List<Restaurant> restaurants = new ArrayList<Restaurant>();
            for (int i = 0; i < businesses.length(); i++) {
                JSONObject business = businesses.getJSONObject(i);
                if (business != null) {
                    String name = business.getString("name");
                    String type = ((JSONArray) business.get("categories")).getJSONArray(0).get(0).toString();
                    JSONObject location = (JSONObject) business.get("location");
//                    JSONObject coordinate = (JSONObject) location.get("coordinate");
//                    double lat = coordinate.getDouble("latitude");
//                    double lng = coordinate.getDouble("longitude");
                    String address = ((JSONArray) location.get("display_address")).get(0).toString();

                    Bitmap thumbnail = getBitmapFromURL(business.getString("image_url"));
                    restaurants.add(new Restaurant(name, address, type, thumbnail));
                }
            }
            return restaurants;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap getBitmapFromURL(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        }
        catch (IOException e) {
            e.printStackTrace();;
            Log.e("Error: ", e.getMessage().toString());
        }

        return bitmap;

    }
}
