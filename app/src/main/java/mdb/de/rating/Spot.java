package mdb.de.rating;

/**
 * Created by LethmateB on 04.11.2015.
 */

import android.location.Location;

/**
 * A Spot
 */
public class Spot {

    public int id;
    public String name;
    public String street;
    public String postcode;
    public String city;
    public String country;
    public boolean visible;
    public Location location;
    public float distance;

    public Spot() {
        super();
    }

    public Spot(String name, String street, String postcode, String city, String country, double latitude, double longitude, float distance) {
        super();
        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.location.setLongitude(longitude);
        this.location.setLatitude(latitude);
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return this.location.getLatitude();
    }

    public void setLatitude(double latitude) {
        this.location.setLatitude(latitude);
    }

    public double getLongitude() {
        return this.location.getLongitude();
    }

    public void setLongitude(double longitude) {
        this.location.setLongitude(longitude);
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
