package mdb.de.rating;

import java.security.Timestamp;

/**
 * Created by LethmateB on 04.11.2015.
 */
public class Position {

    public Timestamp timestamp;
    public float longitude;
    public float latitude;

    public Position() {
        super();
    }

    public Position(Timestamp timestamp, float longitude, float latitude) {
        super();
        this.timestamp = timestamp;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}