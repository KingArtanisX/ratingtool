package mdb.de.rating;

/**
 * Created by LethmateB on 02.11.2015.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The functionality behind the GUI for a new rating of a spot.
 */
public class FragmentNewRating extends Fragment{
    MenuItem saveBtn;
    Menu menu;
    public Integer spotId = 0;
    public String comment = "";
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.view_new_rating, container, false);
        setHasOptionsMenu(true);

        FloatingActionButton add = (FloatingActionButton) getActivity().findViewById(R.id.add);
        add.setVisibility(View.GONE);

        Bundle bundle = this.getArguments();
        if ( bundle.containsKey("id")) this.spotId = bundle.getInt("id");
        if ( bundle.containsKey("comment")) this.comment = bundle.getString("comment");

        this.linearLayout = (LinearLayout) view.findViewById(R.id.newRating);

        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());
        ArrayList<Criterion> criterionArrayList = helper.getCriteriaForSpot(this.spotId);
        Integer i = 0;
        for(Criterion criterion : criterionArrayList) {
            i++;
            TextView criterionText = new TextView(getActivity());
            criterionText.setText(criterion.getName());
            criterionText.setId(i);
            criterionText.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            RatingBar ratingBar = new RatingBar(getActivity());
            ratingBar.setId(i+100);
            ratingBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            ratingBar.setStepSize(1);
            ratingBar.setNumStars(5);
            Drawable stars = ratingBar.getProgressDrawable();
            DrawableCompat.setTint(stars, Color.rgb(229, 225, 0));
            this.linearLayout.addView(criterionText);
            this.linearLayout.addView(ratingBar);
        }

        EditText comment = new EditText(getActivity());
        comment.setSingleLine(false);
        comment.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        comment.setFilters( new InputFilter[] { new InputFilter.LengthFilter(140) } );
        this.linearLayout.addView(comment);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.menu = menu;
        this.saveBtn = menu.findItem(R.id.saveBtn);
        this.saveBtn.setVisible(true);
    }

    public Boolean saveRating() {
        Integer i = 0;
        ArrayList<CriterionRating> criterionRatingArrayList = new ArrayList<>();
        Rating rating = new Rating();

        Integer rate = 0;
        do {
            CriterionRating criterionRating = new CriterionRating();

            TextView criterionText = (TextView) this.linearLayout.getChildAt(i);
            String text = criterionText.getText().toString();

            i++;
            RatingBar ratingBar = (RatingBar) this.linearLayout.getChildAt(i);
            Integer stars = Math.round(ratingBar.getRating());
            rate += stars;
            criterionRating.setName(text);
            criterionRating.setRating(stars);
            criterionRatingArrayList.add(criterionRating);
            i++;
        } while (i+1 < this.linearLayout.getChildCount());


        final EditText comment = (EditText) this.linearLayout.getChildAt(this.linearLayout.getChildCount()-1);

        if ( rate == 0 ) {
            new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setTitle("Fehler")
                    .setMessage("Sie müssen mindestens ein Kriterium bewerten.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Fragment fragment = new FragmentNewRating();


                            Bundle bundle = new Bundle();
                            bundle.putInt("id", spotId);
                            bundle.putString("comment", comment.getText().toString());
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        }
                    })
                    .show();
            return false;
        }
        rating.setReports(0);
        rating.setSpot_id(this.spotId);
        rating.setUser_id(1); //TODO: load user_id from shared pref.
        rating.setText(comment.getText().toString());

        RateemDatabaseHelper helper = new RateemDatabaseHelper(getActivity());
        return helper.insertRatingForSpot(criterionRatingArrayList, rating);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.saveBtn:
                saveRating();
                getFragmentManager().popBackStack();
                return true;
        }
        return false;
    }
}
