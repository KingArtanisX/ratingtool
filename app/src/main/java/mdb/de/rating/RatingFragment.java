package mdb.de.rating;

/**
 * Created by LethmateB on 02.11.2015.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * The functionality behind the GUI to show the average and the last rating for a spot.
 */
public class RatingFragment extends Fragment {
    RatingBar rating;
    TextView nameText;
    TextView streetText;
    TextView cityText;
    TextView countryText;
    RatingAdapter arrayAdapter;
    ListView ratingsListView;
    MenuItem navBtn;
    MenuItem saveBtn;
    Menu menu;

    Spot spot;
    public Integer spotId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.rating_view, container, false);
        setHasOptionsMenu(true);

        FloatingActionButton add = (FloatingActionButton) getActivity().findViewById(R.id.add);
        add.setVisibility(View.VISIBLE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new NewRatingFragment();

                String title = "Neue Bewertung";
                ( (MainActivity) getActivity()).setActionBarTitle(title);

                Bundle bundle = new Bundle();
                bundle.putInt("id", spot.getId());
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            }
        });

        Bundle bundle = this.getArguments();
        this.spotId = bundle.getInt("id");

        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());
        this.spot = helper.getSpotForId(this.spotId);

        this.ratingsListView = (ListView) view.findViewById(R.id.ratingsListView);

        this.nameText = (TextView) view.findViewById(R.id.nameText);
        this.streetText = (TextView) view.findViewById(R.id.streetText);
        this.cityText = (TextView) view.findViewById(R.id.cityText);
        this.countryText = (TextView) view.findViewById(R.id.countryText);
        this.rating = (RatingBar) view.findViewById(R.id.spotRatingBar);

        this.nameText.setText(this.spot.getName());
        this.streetText.setText(this.spot.getStreet());
        this.cityText.setText(this.spot.getPostcode() + " " + this.spot.getCity());
        this.countryText.setText(this.spot.getCountry());
        Log.e("RATING=============", this.spot.getRating().toString());
        this.rating.setRating(this.spot.getRating());
        this.rating.setStepSize(this.spot.getRating());


        ArrayList<Rating> ratings = helper.getRatingsForSpot(this.spotId, 0);

        this.arrayAdapter = new RatingAdapter(
                getActivity(),
                R.layout.rating_list_item,
                ratings);

        this.ratingsListView.setAdapter(this.arrayAdapter);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.navBtn:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=" +
                                this.spot.getStreet() +
                                "+" +
                                this.spot.getPostcode() + "+" + this.spot.getCity()));

                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        this.menu = menu;
        this.saveBtn = menu.findItem(R.id.saveBtn);
        this.navBtn = menu.findItem(R.id.navBtn);
        this.saveBtn.setVisible(false);
        this.navBtn.setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public float calculateDistance(Spot spot) {
        return (float) 0.0;
    }
}
