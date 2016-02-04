package mdb.de.rating;

/**
 * Created by LethmateB on 02.11.2015.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * The functionality behind the GUI to show the average and the last rating for a spot.
 */
public class FragmentRating extends Fragment {
    RatingBar rating;
    TextView nameText;
    TextView streetText;
    TextView cityText;
    TextView countryText;
    AdapterRating arrayAdapter;
    ListView ratingsListView;
    MenuItem navBtn;
    Menu menu;

    Spot spot;
    public Integer spotId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.view_rating, container, false);
        setHasOptionsMenu(true);

        FloatingActionButton add = (FloatingActionButton) getActivity().findViewById(R.id.add);
        add.setVisibility(View.VISIBLE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentNewRating();

                String title = "Neue Bewertung";
                ( (ActivityMain) getActivity()).setActionBarTitle(title);

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
        this.rating.setRating(this.spot.getRating());
        Drawable stars = this.rating.getProgressDrawable();
        DrawableCompat.setTint(stars, Color.rgb(229, 225, 0));



        ArrayList<Rating> ratings = helper.getRatingsForSpot(this.spotId, 0);

        this.arrayAdapter = new AdapterRating(
                getActivity(),
                R.layout.rating_list_item,
                ratings);

        this.ratingsListView.setAdapter(this.arrayAdapter);
        this.ratingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Rating rating = (Rating) ratingsListView.getItemAtPosition(position);

                ((ActivityMain) getActivity()).setViewIsAtHome(false);
                Fragment fragment = new FragmentSingleRating();
                Bundle bundle = new Bundle();
                bundle.putInt("id", rating.getId());
                fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

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
        super.onCreateOptionsMenu(menu, inflater);
        this.menu = menu;
        this.navBtn = menu.findItem(R.id.navBtn);
        this.navBtn.setVisible(true);
    }

    public float calculateDistance(Spot spot) {
        return (float) 0.0;
    }
}
