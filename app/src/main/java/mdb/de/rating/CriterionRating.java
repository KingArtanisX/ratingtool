package mdb.de.rating;

/**
 * Created by LethmateB on 10.12.2015.
 */
public class CriterionRating {
    public String name;
    public Integer rating; //stars

    public CriterionRating() {
        super();
    }

    public CriterionRating(String name, Integer rating) {
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
