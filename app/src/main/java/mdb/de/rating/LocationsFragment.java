package mdb.de.rating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LethmateB on 19.11.2015.
 */
public class LocationsFragment extends Fragment {

    ArrayList<Spot> spots;
    ListView spotList;
    SpotAdapter arrayAdapter;
    CurrentPosition currentPosition;
    TextView city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.activity_main); // Inflate the layout for this fragment

        this.currentPosition = new CurrentPosition();
        this.city = (TextView) getActivity().findViewById(R.id.city);

        //TODO: locate me
        if(this.currentPosition.getCity() != null) {
            this.city.setText(this.currentPosition.getCity());
        }

        showSpotsInCity();
        return inflater.inflate(R.layout.location_view, container, false);
    }

    /**
     * Show a list of spots in a city
     */
    private void showSpotsInCity() {
        String city = this.currentPosition.getCity();
        this.spotList = (ListView) getActivity().findViewById(R.id.listView);
        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());

        this.spots = helper.getSpotsForCity(city, 0);
        for (Spot spot: this.spots) {
            spot.setDistance(calculateDistance(spot));
        }
        this.arrayAdapter = new SpotAdapter(
                getActivity(),
                R.layout.list_items,
                this.spots);

        this.spotList.setAdapter(this.arrayAdapter);
        this.spotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spot spot = (Spot) spotList.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), RatingActivity.class);
                intent.putExtra("id", spot.getId());
                startActivity(intent);
            }
        });
    }

    public float calculateDistance(Spot spot) {
        return (float) 0.0;
    }
}
