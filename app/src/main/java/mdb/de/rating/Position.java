package mdb.de.rating;

import java.security.Timestamp;

/**
 * Created by LethmateB on 04.11.2015.
 */

/**
 * Vorraussetzung für uns: Wir müssen einen Backgroundtask schreiben,
 * der all das folgende im Hintergrund erledigt ohne das die App gestartet sein muss.
 *
 * When this function is activated it logs the GPS positions of a user over time.
 * By default it logs the position every time the app is started and when the app is used
 * every 10 seconds using WI-FI, GPS and the Cell-ID so save battery.
 * When the app is closed it listens to several apps like google maps
 * or other routing apps for the accurate GPS coordinates.
 * If all these apps are not running the position is tracked depending on the speed.
 * Therefore the distance between the last two positions is calculated and from this
 * the speed is derived.
 * When the speed is 0 or nearly 0 (not moving or moving in a small location)
 * the frequency is reduced.
 *
 * The position is only written to the database when the user is more than 5 minutes
 * at the same location so he might visited it.
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
