package mdb.de.rating;

/**
 * Created by LethmateB on 04.11.2015.
 */

/**
 * A Location
 */
public class Location {

    public int id;
    public String name;
    public String street;
    public String postcode;
    public String city;
    public String country;
    public float latitude;
    public float longitude;
    public boolean visible;

    public Location() {
        super();
    }

    public Location(String name, String street, String postcode, String city, String country, float latitude, float longitude) {
        super();
        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param lat1 the latitude of first position
     * @param long1 the longitude of first position
     * @param lat2 the latitude of second position
     * @param long2 the longitude of second position
     * @return the distance between the two GPS positions
     */
    public float getDistance(float lat1, float long1, float lat2, float long2) {
        double dx = 111.3 * Math.cos(lat1) * (long1 - long2);
        double dy = 111.3 * (lat1 - lat2);
        return (float) Math.sqrt(dx*dx + dy*dy);
    }
    public int getId() {
        return id;
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
