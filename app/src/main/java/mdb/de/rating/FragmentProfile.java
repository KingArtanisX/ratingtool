package mdb.de.rating;

/**
 * Created by LethmateB on 02.11.2015.
 */

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The methods for the users profile
 */
public class FragmentProfile extends Fragment{
    TextView usernameText;
    TextView rankText;
    ImageView profilepicture;
    ListView listViewFavourite;

    ArrayList<Spot> spots = new ArrayList<Spot>();

    Spot spot;
    public Integer spotId;
    public Integer userid = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.view_rating, container, false);
        setHasOptionsMenu(true);

        /*FloatingActionButton add = (FloatingActionButton) getActivity().findViewById(R.id.add);
        add.setVisibility(View.GONE);
        */


        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());
        this.spot = helper.getFavouriteSpotForUser(this.userid);
        this.spots.add(this.spot);

        this.usernameText = (TextView) view.findViewById(R.id.usernameText);
        this.profilepicture = (ImageView) view.findViewById(R.id.profilepicture);
        this.rankText = (TextView) view.findViewById(R.id.rankText);
        //TODO: Add Favourite Spot Data

        return view;
    }

    public Spot getFavouriteSpotForUser(Integer userid) {
        Spot spot = new Spot();



        return spot;
    }
}
