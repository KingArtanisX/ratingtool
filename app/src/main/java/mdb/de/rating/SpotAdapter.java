package mdb.de.rating;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LethmateB on 09.11.2015.
 */
public class SpotAdapter extends ArrayAdapter<Spot>{
    Context context;
    int layoutResourceId;
    ArrayList<Spot> data = null;

    public SpotAdapter(Context context, int layoutResourceId, ArrayList<Spot> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SpotHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new SpotHolder();
            holder.name = (TextView) row.findViewById(R.id.spotName);
            holder.distance = (TextView) row.findViewById(R.id.distance);
            holder.street = (TextView) row.findViewById(R.id.street);
            holder.city = (TextView) row.findViewById(R.id.city);

            row.setTag(holder);
        } else holder = (SpotHolder) row.getTag();

        Spot spot = data.get(position);

        holder.name.setText(spot.getName());
        holder.distance.setText(Float.toString(spot.getDistance()));
        holder.street.setText(spot.getStreet());
        holder.city.setText(spot.getPostcode() + " " + spot.getCity());
        return row;
    }

    static class SpotHolder {
        TextView name;
        TextView distance;
        TextView street;
        TextView city;
    }
}
