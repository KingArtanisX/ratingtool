package mdb.de.rating;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by LethmateB on 19.11.2015.
 */
public class FragmentSearch extends Fragment {
    Menu menu;
    AdapterSpot arrayAdapter;
    ListView spotList;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.view_search, container, false);


        final SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchResult(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        getSpotList();
        return this.view;

    }

    public void getSpotList() {
        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());
        ArrayList<Spot> spotArrayList = helper.getAllSpots(0);
        this.arrayAdapter = new AdapterSpot(
                getActivity(),
                R.layout.list_items,
                spotArrayList);
        this.spotList = (ListView) this.view.findViewById(R.id.listViewSearch);
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
    }

    public Boolean getSearchResult(String query) {
        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());
        ArrayList<Spot> spotArrayList = helper.getSpotsForSearch(query);
        if (spotArrayList.size() > 0) {
            this.arrayAdapter = new AdapterSpot(
                    getActivity(),
                    R.layout.list_items,
                    spotArrayList);
            this.spotList = (ListView) this.view.findViewById(R.id.listViewSearch);
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
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.saveBtn:
                getFragmentManager().popBackStack();
                return true;
        }
        return false;
    }
}
