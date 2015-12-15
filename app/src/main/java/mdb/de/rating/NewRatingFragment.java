package mdb.de.rating;

/**
 * Created by LethmateB on 02.11.2015.
 */

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
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
public class NewRatingFragment extends Fragment{
    MenuItem saveBtn;
    MenuItem navBtn;
    Menu menu;
    public Integer spotId;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.new_rating_view, container, false);
        setHasOptionsMenu(true);

        FloatingActionButton add = (FloatingActionButton) getActivity().findViewById(R.id.add);
        add.setVisibility(View.GONE);

        Bundle bundle = this.getArguments();
        this.spotId = bundle.getInt("id");


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
            ratingBar.setId(i);
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
        inflater.inflate(R.menu.main, menu);
        this.menu = menu;
        this.saveBtn = menu.findItem(R.id.saveBtn);
        this.navBtn = menu.findItem(R.id.navBtn);
        this.saveBtn.setVisible(true);
        this.navBtn.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean saveRating() {
        this.linearLayout.getChildCount();
        Integer i = 0;
        ArrayList<CriterionRating> criterionRatingArrayList = new ArrayList<>();

        do {
            i++;
            CriterionRating criterionRating = new CriterionRating();

            TextView criterionText = (TextView) this.linearLayout.findViewById(i);
            String text = criterionText.getText().toString();

            RatingBar ratingBar = (RatingBar) this.linearLayout.findViewById(i);
            Float rating = ratingBar.getRating();

            criterionRating.setName(text);
            criterionRating.setRating(rating);
            criterionRatingArrayList.add(criterionRating);
        } while (this.linearLayout.getChildCount() >= i);

        /**
         * TODO:
         * new Rating
         * fill data
         * call insertRatingForSpot() in helper
         * return the return of insertRatingForSpot()
         */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.saveBtn:
                saveRating();
                break;
        }
        return false;
    }
}
