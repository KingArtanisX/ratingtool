package mdb.de.rating;


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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * Created by LethmateB on 09.11.2015.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private boolean viewIsAtHome;
    public Integer id;
    MenuItem navBtn;
    MenuItem saveBtn;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_close_to_me);
    }


    /**
     *
     * Calculates the distance from the last current position to a spot.
     * @param spot the spot
     * @return the distance from my location to the spot
     */
    public Float calculateDistance(Spot spot) {
        CurrentPosition position = new CurrentPosition();
        //TODO: calc distance
        return 0.0F;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.nav_close_to_me); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
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
                fragment = new CloseToFragment();
                title  = "In meiner Nähe";
                this.viewIsAtHome = true;
                break;
            case R.id.nav_search:
                fragment = new SearchFragment();
                title  = "Suche";
                this.viewIsAtHome = false;
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                title  = "Profil";
                this.viewIsAtHome = false;
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                title  = "Profil";
                this.viewIsAtHome = false;
                break;
            case R.id.nav_share:
                title  = "Meinen Freunden berichten";
                this.viewIsAtHome = false;
                break;
            case R.id.nav_purchase:
                title  = "Pro Version kaufen";
                this.viewIsAtHome = false;
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
}
