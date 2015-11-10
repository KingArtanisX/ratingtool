package mdb.de.rating;

/**
 * Created by LethmateB on 10.11.2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetCurrentLocation extends Activity implements OnClickListener {

    private LocationManager locationManager = null;
    private TextView showLocation = null;

    Button btnGetLocation = null;
    Boolean flag = false;
    LocationListener locationListener = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.showLocation = (TextView) findViewById(R.id.city);
        this.btnGetLocation = (Button) findViewById(R.id.find_me);
        this.btnGetLocation.setOnClickListener(this);
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Onclick function for the btn to load the new location
     * @param v the View
     */
    @Override
    public void onClick(View v) {
        this.flag = displayGpsStatus();
        if (this.flag && this.locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER)) {
            this.showLocation.setText(R.string.search);
            this.locationListener = new MyLocationListener();
            try {
                this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this.locationListener);
            }catch (SecurityException e) {
                e.printStackTrace();
            }
        } else {
            alertbox();
        }
    }

    private Boolean displayGpsStatus() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * Show a alertbox if there is a problem with the GPS
     */
    protected void alertbox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is disabled")
                .setCancelable(false)
                .setTitle("Gps Status")
                .setPositiveButton("enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent myIntent = new Intent(
                                        Settings.ACTION_SECURITY_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Listener for location changes
     */
    private class MyLocationListener implements LocationListener {

        /**
         * Is called every time the location is changed.
         * @param loc the new location
         */
        @Override
        public void onLocationChanged(Location loc) {
            Toast.makeText(getBaseContext(), "Location changed : Lat: " + loc.getLatitude() + " Long: " + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            CurrentPosition position = new CurrentPosition();
            if( position.getLatitude() == loc.getLatitude() && position.getLongitude() == loc.getLongitude()) {
                position.setCounter(position.getCounter() + 1);
                if(position.getCounter() > 4) {
                    Position pos = new Position();
                    Long tsLong = System.currentTimeMillis()/1000;
                    pos.setTimestamp(tsLong);
                    pos.location.setLatitude(loc.getLatitude());
                    pos.location.setLongitude(loc.getLongitude());
                }
            } else {
                position.setLatitude(loc.getLatitude());
                position.setLongitude(loc.getLongitude());
            }
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (addresses.size() > 0)
                cityName = addresses.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }
            position.setCity(cityName);
            showLocation.setText(cityName);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}
