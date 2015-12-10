package mdb.de.rating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LethmateB on 08.12.2015.
 */
public class CloseToFragment extends Fragment {
    ArrayList<Spot> spots;
    ListView spotList;
    SpotAdapter arrayAdapter;
    CurrentPosition currentPosition;
    TextView city;
    MenuItem navBtn;
    MenuItem saveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.close_to_me_view, container, false);


        this.currentPosition = new CurrentPosition();
        this.city = (TextView) view.findViewById(R.id.city);

        //TODO: locate me
        if(this.currentPosition.getCity() != null) {
            this.city.setText(this.currentPosition.getCity());
        }

        String city = this.currentPosition.getCity();
        this.spotList = (ListView) view.findViewById(R.id.listView);
        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());

        this.spots = helper.getAllSpots(0);

        ArrayList<Spot> closeSpots = new ArrayList<>();
        for (Spot spot: this.spots) {
            spot.setDistance(calculateDistance(spot));
            if (spot.getDistance() < 5) {
                closeSpots.add(spot);
            }
        }
        this.spots = closeSpots;
        this.arrayAdapter = new SpotAdapter(
                getActivity(),
                R.layout.list_items,
                this.spots);

        this.spotList.setAdapter(this.arrayAdapter);
        this.spotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spot spot = (Spot) spotList.getItemAtPosition(position);

                Fragment fragment = new RatingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", spot.getId());
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        return view;
    }


    public float calculateDistance(Spot spot) {
        return (float) 0.0;
    }
}

