package mdb.de.rating;

import android.location.Location;

/**
 * Created by LethmateB on 10.11.2015.
 */
public class CurrentPosition {
    public Location location;
    public String city;
    public int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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


}
