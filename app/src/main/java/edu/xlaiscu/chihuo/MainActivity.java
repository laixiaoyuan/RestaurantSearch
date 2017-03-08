package edu.xlaiscu.chihuo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataService dataService;
    List<Restaurant> restaurants;
    ListView restaurantListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantListView = (ListView) findViewById(R.id.listview);
        dataService = new DataService();

//        List<Restaurant> restaurants = dataService.getRestaurantData();
//        for (int i = 0; i < 10; i++) {
//            restaurants.add(new Restaurant("Cai Xiang Gen", "San Francisco", "Chinese", null));
//        }
//        RestaurantAdapter adapter = new RestaurantAdapter(this, restaurants);
//        restaurantListView.setAdapter(adapter);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                YelpApi yelp = new YelpApi();
                yelp.searchForBusinessesByLocation("dinner", "San Francisco, CA", 20);
                return null;
            }
        }.execute();
    }

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
            }
            else {
                Toast.makeText(context, "Data service error", Toast.LENGTH_LONG);
            }
        }
    }

}

