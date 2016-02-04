package mdb.de.rating;

/**
 * Created by LethmateB on 04.11.2015.
 */


/**
 * A Spot
 */
public class Spot {

    public Integer id;
    public String name;
    public String street;
    public String postcode;
    public String city;
    public String country;
    public Boolean visible;
    public Long latitude;
    public Long longitude;
    public Float distance = 0.0F;
    public Float rating;

    public Spot() {
        super();
    }

    public Spot(String name, String street, String postcode, String city, String country, Long latitude, Long longitude, Float distance, Float rating) {
        super();
        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.latitude = longitude;
        this.longitude = latitude;
        this.distance = distance;
        this.rating = rating;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Long getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
