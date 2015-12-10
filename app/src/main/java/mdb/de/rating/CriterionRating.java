package mdb.de.rating;

/**
 * Created by LethmateB on 10.12.2015.
 */
public class CriterionRating {
    public Integer spotid;
    public String name;
    public Float rating;

    public CriterionRating() {
        super();
    }

    public CriterionRating(Integer spotid, String name, Float rating) {
        super();
        this.spotid = spotid;
        this.name = name;
        this.rating = rating;
    }

    public Integer getSpotid() {
        return spotid;
    }

    public void setSpotid(Integer spotid) {
        this.spotid = spotid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
