package mdb.de.rating;


import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * Created by LethmateB on 09.11.2015.
 */
public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LocationListener {
    private boolean viewIsAtHome = true;
    public Integer id;
    MenuItem navBtn;
    MenuItem saveBtn;
    Menu menu;
    EditText email;
    protected EditText password;
    String passwordHash;
    TextView message;
    Integer logincounter;
    LocationManager locationManager;
    String bestProvider;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setContentView(R.layout.view_login);

        this.email = (EditText) findViewById(R.id.email);
        this.password = (EditText) findViewById(R.id.password);
        this.message = (TextView) findViewById(R.id.message);
        this.message.setVisibility(View.GONE);
        this.logincounter = 0;

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);

        this.passwordHash = md5(this.password.getText().toString());
        Log.e("=====PASSWORDHASH=====", this.passwordHash);
        Boolean success = login(this.email.getText().toString(), this.passwordHash);
*/

        Boolean success = true;
        if( success ) {
            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
            add.setVisibility(View.GONE);

            this.locateMe();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            displayView(R.id.nav_close_to_me);
        } else if (this.logincounter > 4){
            this.message.setText("Ihre E-Mail-Adresse oder Ihr Passwort ist falsch. Sie haben noch " + (4 - this.logincounter) + " anmeldeversuche.");
        } else if (this.logincounter < 3) {
            this.message.setText("Sie haben alle anmeldeversuche verbraucht, bitte wenden Sie sich an den Support.");
        }
    }

    public Location locateMe(){
        this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        this.bestProvider = locationManager.getBestProvider(criteria, false);

        return this.location = locationManager.getLastKnownLocation(bestProvider);
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * Calculates the distance from the last current position to a spot.
     * @param spot the spot
     * @return the distance from my location to the spot
     */
    public Float calculateDistance(Spot spot) {
        Location spotLocation = new Location(LOCATION_SERVICE);
        spotLocation.setLatitude(spot.getLatitude());
        spotLocation.setLongitude(spot.getLongitude());

        return spotLocation.distanceTo(this.location);
    }

    public void setViewIsAtHome(Boolean viewIsAtHome) {
        this.viewIsAtHome = viewIsAtHome;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!this.viewIsAtHome) { //if the current view is not the CloseToFragment
                displayView(R.id.nav_close_to_me); //display the CloseToFragment
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Rate'em beenden")
                        .setMessage("Wollen sie Rate'em beenden?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);  //If view is in CloseToFragment, exit application
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        return true;
    }

    public void displayView(Integer viewId) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (viewId) {
            case R.id.nav_close_to_me:
                fragment = new FragmentCloseTo();
                title  = "In meiner Nähe";
                setViewIsAtHome(true);
                break;
            case R.id.nav_search:
                fragment = new FragmentSearch();
                title  = "Suche";
                setViewIsAtHome(false);
                break;
            case R.id.nav_profile:
                fragment = new FragmentProfile();
                title  = "Profil";
                setViewIsAtHome(false);
                break;
            case R.id.nav_settings:
                fragment = new FragmentSettings();
                title  = "Profil";
                setViewIsAtHome(false);
                break;
            case R.id.nav_share:
                title  = "Meinen Freunden berichten";
                setViewIsAtHome(false);
                break;
            case R.id.nav_purchase:
                title  = "Pro Version kaufen";
                setViewIsAtHome(false);
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        setActionBarTitle(title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void setActionBarTitle(String title){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        this.navBtn = this.menu.findItem(R.id.navBtn);
        this.saveBtn = this.menu.findItem(R.id.saveBtn);
        this.navBtn.setVisible(false);
        this.saveBtn.setVisible(false);


        return super.onCreateOptionsMenu(menu);
    }


    public void reportRating(Report report) {
        RateemDatabaseHelper helper = new RateemDatabaseHelper(this);

        helper.reportRating(report);
    }


    public Boolean login(String email, String password){
        RateemDatabaseHelper helper = new RateemDatabaseHelper(this);

        helper.getUserInfoForEmail(email);


        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        locateMe();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e("NEW STATUS", String.valueOf(status));
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.e("PROVIDER", "enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.e("PROVIDER", "disabled");
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.locationManager.removeUpdates(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.locationManager.requestLocationUpdates(this.bestProvider, 20000, 1, this);
    }
}
