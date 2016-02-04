package mdb.de.rating;

/**
 * Created by LethmateB on 04.11.2015.
 */

/**
 * A single Rating for a spot.
 */
public class Rating {
    public Integer id;
    public String text = "";
    public Integer user_id = 0;
    public Integer spot_id = 0;
    public Integer reports = 0;
    public Float rating = 0F;

    public Rating() {
        super();
    }

    public Rating(Integer id, String text, Integer user_id, Integer spot_id, Integer reports) {
        super();
        this.id = id;
        this.text = text;
        this.user_id = user_id;
        this.spot_id = spot_id;
        this.reports = reports;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(Integer spot_id) {
        this.spot_id = spot_id;
    }

    public Integer getReports() {
        return reports;
    }

    public void setReports(Integer reports) {
        this.reports = reports;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
