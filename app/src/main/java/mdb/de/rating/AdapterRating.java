package mdb.de.rating;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LethmateB on 09.12.2015.
 */
public class AdapterRating extends ArrayAdapter<Rating>{
    Context context;
    Integer layoutResourceId;
    ArrayList<Rating> data = null;

    public AdapterRating(Context context, int layoutResourceId, ArrayList<Rating> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RatingHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RatingHolder();
            holder.text = (TextView) row.findViewById(R.id.textText);
            holder.rating = (RatingBar) row.findViewById(R.id.ratingBar);

            row.setTag(holder);
        } else holder = (RatingHolder) row.getTag();

        Rating rating = data.get(position);

        holder.text.setText(rating.getText());
        holder.rating.setRating(rating.getRating());
        Drawable stars = holder.rating.getProgressDrawable();
        DrawableCompat.setTint(stars, Color.rgb(229, 225, 0));
        return row;
    }

    static class RatingHolder {
        TextView text;
        RatingBar rating;
    }
}
