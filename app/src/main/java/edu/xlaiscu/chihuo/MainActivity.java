package edu.xlaiscu.chihuo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataService dataService;
    ListView restaurantListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantListView = (ListView) findViewById(R.id.listview);
        dataService = new DataService();
    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Restaurant restaurant = (Restaurant) restaurantListView.getItemAtPosition(position);
//        Intent intent = new Intent(MainActivity.this, RestaurantDetails.class);
////        Bundle bundle = new Bundle();
////        bundle.putString("Name", restaurant.getName());
////        bundle.putString("Address", restaurant.getFullAddress());
////        bundle.putString("Phone", restaurant.getPhone());
////        bundle.putString("Type", restaurant.getType());
////        intent.putExtras(bundle);
////        intent.putExtra("Image", restaurant.getImage());
////        intent.putExtra("Rating", restaurant.getRating());
//        intent.putExtra("restaurant", restaurant);
//        startActivityForResult(intent, 1234);
//
//    }



    @Override
    public void onStart() {
        super.onStart();
        refreshRestaurantList(dataService);
    }

    private void refreshRestaurantList(DataService dataService) {
        new GetRestaurantsNearbyAsynTask(this, dataService).execute();
    }

    private class GetRestaurantsNearbyAsynTask extends AsyncTask<Void, Void, List<Restaurant>> {
        private DataService dataService;
        private Context context;

        public GetRestaurantsNearbyAsynTask(Context context, DataService dataService) {
            this.dataService = dataService;
            this.context = context;
        }

        @Override
        protected List<Restaurant> doInBackground(Void... params) {
            return dataService.getRestaurantData();
        }

        @Override
        protected void onPostExecute(List<Restaurant> restaurants) {
            if (restaurants != null) {
                super.onPostExecute(restaurants);
                RestaurantAdapter adapter = new RestaurantAdapter(context, restaurants);
                restaurantListView.setAdapter(adapter);
//                restaurantListView.setOnItemClickListener(MainActivity.this);
                restaurantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, RestaurantDetails.class);
                        Restaurant restaurant = (Restaurant) restaurantListView.getItemAtPosition(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("Name", restaurant.getName());
                        bundle.putString("Address", restaurant.getFullAddress());
                        bundle.putString("Phone", restaurant.getPhone());
                        bundle.putString("Type", restaurant.getType());
                        bundle.putBoolean("CurStatus", restaurant.getCurStatus());
                        intent.putExtras(bundle);
                        intent.putExtra("Image", restaurant.getImage());
                        intent.putExtra("Rating", restaurant.getRating());
                        startActivity(intent);
                    }
                });

            }
            else {
                Toast.makeText(context, "Data service error", Toast.LENGTH_LONG);
            }
        }
    }

}

