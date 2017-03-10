package edu.xlaiscu.chihuo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantDetails extends AppCompatActivity {
    TextView name;
    TextView address;
    ImageView rating;
    TextView type;
    ImageView image;
    TextView phone;
    TextView curStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        name = (TextView) findViewById(R.id.name);
        String rName = bundle.getString("Name");
        name.setText(rName);

        address = (TextView) findViewById(R.id.address);
        String rAddress = bundle.getString("Address");
        address.setText(rAddress);

        rating = (ImageView) findViewById(R.id.rate);
        Bitmap rRating = (Bitmap) intent.getParcelableExtra("Rating");
        rating.setImageBitmap(rRating);

        type = (TextView) findViewById(R.id.type);
        String rType = bundle.getString("Type");
        type.setText(rType);

        image = (ImageView) findViewById(R.id.picture);
        Bitmap rPicture = (Bitmap) intent.getParcelableExtra("Image");
        image.setImageBitmap(rPicture);

        phone = (TextView) findViewById(R.id.phone);
        String rPhone = bundle.getString("Phone");
        phone.setText(rPhone);

        curStatus = (TextView) findViewById(R.id.current_status);
        Boolean rCurStatus = bundle.getBoolean("CurStatus");
        if (rCurStatus) {
            curStatus.setText("Open");
            curStatus.setTextColor(Color.GREEN);
        }
        else {
            curStatus.setText("Closed");
            curStatus.setTextColor(Color.RED);
        }
    }
}
