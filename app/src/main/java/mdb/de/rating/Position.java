package mdb.de.rating;

import android.location.Location;

import java.security.Timestamp;

/**
 * Created by LethmateB on 04.11.2015.
 */

/**
 * Vorraussetzung für uns: Wir müssen einen Backgroundtask schreiben,
 * der all das folgende im Hintergrund erledigt ohne das die App gestartet sein muss.
 *
 * When this app-function is activated it logs the GPS position of a user over time.
 * By default it logs the position every time the app is started and when the app is used
 * every 10 seconds using WI-FI, GPS and the Cell-ID so save battery.
 * When the app is closed it listens to several apps like google maps
 * or other routing apps for the accurate GPS coordinates.
 * If all these apps are not running the position is tracked depending on the speed.
 * Therefore the distance between the last two positions is calculated and from this
 * the speed is derived.
 * When the speed is 0 or nearly 0 (not moving or moving in a small spot)
 * the frequency is reduced.
 *
 * The position is only written to the database when the user is more than 5 minutes
 * at the same spot so he might visited it.
 */
public class Position {

    public Long timestamp;
    public Location location;

    public Position() {
        super();
    }

    public Position(Long timestamp, double longitude, double latitude) {
        super();
        this.timestamp = timestamp;
        this.location.setLatitude(latitude);
        this.location.setLongitude(longitude);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
