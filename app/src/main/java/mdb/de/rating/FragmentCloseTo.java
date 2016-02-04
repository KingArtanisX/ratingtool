package mdb.de.rating;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

/**
 * Created by LethmateB on 08.12.2015.
 */
public class FragmentCloseTo extends Fragment {
    ArrayList<Spot> spots;
    ListView spotList;
    AdapterSpot arrayAdapter;
    CurrentPosition currentPosition;
    TextView city;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.view_close_to_me, container, false);

        this.currentPosition = new CurrentPosition();
        this.currentPosition.setLocation( ( (ActivityMain) getActivity()).locateMe());
        this.city = (TextView) view.findViewById(R.id.city);

        //TODO: locate me
        Log.e("=====POSITION====", ( ( (ActivityMain) getActivity()).locateMe()).toString());
        if(this.currentPosition != null) {
            this.city.setText(this.currentPosition.getCity());
        }

        Location location = ( (ActivityMain) getActivity()).locateMe();
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(addresses.size() > 0) {
            Log.e("ADRESSE", addresses.toString());
        }
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
        this.arrayAdapter = new AdapterSpot(
                getActivity(),
                R.layout.list_items,
                this.spots);

        this.spotList.setAdapter(this.arrayAdapter);
        this.spotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spot spot = (Spot) spotList.getItemAtPosition(position);

                ((ActivityMain) getActivity()).setViewIsAtHome(false);
                Fragment fragment = new FragmentRating();
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

