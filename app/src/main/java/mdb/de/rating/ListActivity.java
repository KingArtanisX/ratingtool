package mdb.de.rating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LethmateB on 09.11.2015.
 */
public class ListActivity extends AppCompatActivity{
    ArrayList<Spot> spots;
    ListView spotList;
    SpotAdapter arrayAdapter;
    CurrentPosition currentPosition;
    TextView city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.currentPosition = new CurrentPosition();
        this.city = (TextView) findViewById(R.id.city);

        this.city.setText(this.currentPosition.getCity());
        showSpotsInCity();
    }

    /**
     * Show a list of spots in a city
     */
    private void showSpotsInCity() {
        String city = this.currentPosition.getCity();
        this.spotList = (ListView) findViewById(R.id.listView);
        RateemDatabaseHelper helper = new RateemDatabaseHelper(this);

        this.spots = helper.getSpotsForCity(city, 0);
        for (Spot spot: this.spots) {
            spot.setDistance(calculateDistance(spot));
        }
        this.arrayAdapter = new SpotAdapter(
                this,
                R.layout.list_items,
                this.spots);

        this.spotList.setAdapter(this.arrayAdapter);
        this.spotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spot spot = (Spot) spotList.getItemAtPosition(position);
                Intent intent = new Intent(ListActivity.this, RatingActivity.class);
                intent.putExtra("id", spot.getId());
                startActivity(intent);
            }
        });
    }

    /**
     *
     * Calculates the distance from the last current position to a spot.
     * @param spot the spot
     * @return the distance from my location to the spot
     */
    public float calculateDistance(Spot spot) {
        CurrentPosition position = new CurrentPosition();
        return spot.location.distanceTo(position.getLocation());
    }
}
