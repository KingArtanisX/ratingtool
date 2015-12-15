package mdb.de.rating;

/**
 * Created by LethmateB on 10.12.2015.
 */
public class CriterionRating {
    public String name;
    public Float rating; //stars

    public CriterionRating() {
        super();
    }

    public CriterionRating(String name, Float rating) {
        super();
        this.name = name;
        this.rating = rating;
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
