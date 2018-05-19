package com.example.navigationmenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    /** The top-level container for the drawer views */
    private DrawerLayout mDrawerLayout;
    /** Ties together the functionality of DrawerLayout and the framework ActionBar */
    private ActionBarDrawerToggle mToggle;
    /** The toolbar to be used as the ActionBar */
    private Toolbar mToolbar;

    /** API for performing a set of Fragment operations */
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the toolbar
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        // Set the toolbar to act as the ActionBar
        setSupportActionBar(mToolbar);
        // Find the DrawerLayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        // Add the specified listener
        mDrawerLayout.addDrawerListener(mToggle);
        // Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout
        mToggle.syncState();
        // Display the home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Add HomeFragment to the layout
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();

        // Set the action bar's title to "Home fragment...."
        getSupportActionBar().setTitle("Home fragment....");

        // Find the NavigationView
        navigationView = (NavigationView)findViewById(R.id.navigation_menu);
        // Set a listener that will be notified when a menu item is selected
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check the id of the selected item
                switch (item.getItemId()){
                    case R.id.nav_home_fragment:
                        // Replace an existing fragment with HomeFragment
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new HomeFragment());
                        fragmentTransaction.commit();
                        // Set the action bar's title to "Home fragment"
                        getSupportActionBar().setTitle("Home fragment");
                        // Display a check mark for the selected item
                        item.setChecked(true);
                        // Close the drawer view
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_email_fragment:
                        // Replace an existing fragment with EmailFragment
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new EmailFragment());
                        fragmentTransaction.commit();
                        // Set the action bar's title to "Email fragment"
                        getSupportActionBar().setTitle("Email fragment");
                        // Display a check mark for the selected item
                        item.setChecked(true);
                        // Close the drawer view
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_calendar_fragment:
                        // Replace an existing fragment with CalendarFragment
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new CalendarFragment());
                        fragmentTransaction.commit();
                        // Set the action bar's title to "Calendar fragment"
                        getSupportActionBar().setTitle("Calendar fragment");
                        item.setChecked(true);
                        // Close the drawer view
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }
    // Activate the drawer toggle
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
