package mdb.de.rating;

import org.w3c.dom.Text;

/**
 * Created by LethmateB on 04.11.2015.
 */

/**
 * A single Rating for a location.
 */
public class Rating {
    public Text text;
    public int user_id;
    public int location_id;
    public int reports;

    public Rating() {
        super();
    }

    public Rating(Text text, int user_id, int location_id, int reports) {
        super();
        this.text = text;
        this.user_id = user_id;
        this.location_id = location_id;
        this.reports = reports;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }
}
