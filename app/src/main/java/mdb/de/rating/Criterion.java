package mdb.de.rating;

/**
 * Created by LethmateB on 10.12.2015.
 */
public class Criterion {
    public Integer id;
    public String name;

    public Criterion() {
        super();
    }

    public Criterion(String name) {
        super();
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
