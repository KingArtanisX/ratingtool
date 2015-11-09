package mdb.de.rating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by LethmateB on 09.11.2015.
 */
public class ListActivity extends AppCompatActivity{
    ArrayList<Spot> spots;
    ListView productList;
    SpotAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showSpotsInCity();
    }

    private void showSpotsInCity() {
        String city = "Gelsenkirchen";
        this.productList = (ListView) findViewById(R.id.listView);
        RateemDatabaseHelper helper = new RateemDatabaseHelper(this);

        this.spots = helper.getSpotsForCity(city, 0);
        this.arrayAdapter = new SpotAdapter(
                this,
                R.layout.list_items,
                this.spots);

        this.productList.setAdapter(this.arrayAdapter);
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spot spot = (Spot) productList.getItemAtPosition(position);
                Intent intent = new Intent(ListActivity.this, RatingActivity.class);
                intent.putExtra("id", spot.getId());
                startActivity(intent);
            }
        });
    }

    public float calculateDistance(Spot spot) {
        float latitude = spot.getLatitude();
        float longitude = spot.getLongitude();


        return (float) 0.0;
    }



}
