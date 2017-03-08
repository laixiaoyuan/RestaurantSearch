package edu.xlaiscu.chihuo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lexie on 3/6/17.
 */

public class RestaurantAdapter extends BaseAdapter {
    Context context;
    List<Restaurant> restaurantData;

    public RestaurantAdapter(Context context, List<Restaurant> restaurantData) {
        this.context = context;
        this.restaurantData = restaurantData;
    }
    @Override
    public int getCount() {
        return restaurantData.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.each_row, parent, false);
        }
        TextView restaurantName = (TextView) convertView.findViewById(R.id.restaurant_name);
        TextView restaurantType = (TextView) convertView.findViewById(R.id.restaurant_type);
        TextView restaurantAddress = (TextView) convertView.findViewById(R.id.restaurant_address);
        ImageView restaurantThumbnail = (ImageView) convertView.findViewById(R.id.restaurant_thumbnail);
        Restaurant r = restaurantData.get(position);
        restaurantName.setText(r.getName());
        restaurantType.setText(r.getType());
        restaurantAddress.setText(r.getAddress());
        restaurantThumbnail.setImageBitmap(r.getImage());
        return convertView;
    }


}
