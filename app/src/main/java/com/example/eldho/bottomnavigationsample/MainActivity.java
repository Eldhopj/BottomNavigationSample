package com.example.eldho.bottomnavigationsample;

/**Reference : <a href "https://www.androidhive.info/2017/12/android-working-with-bottom-navigation" />
 * Commit 1: Add design dependency
 *          Create a menu for to pass into activity_main -> bottom navigation widget
 *          Create a color resource folder for navigation selection and non selection colors
 *          Create fragments with xml for each navi buttons (Right click -> new -> fragments)
 *
 * Commit 2: Code Optimizations
 *
 * Commit 3: ToolBar name change while navigation through Bottom Navigation
 *          When backButton pressed it came back to HomeFragment*/

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar(); // Initializing toolbar

        bottomNavigationView = findViewById(R.id.bottomNavigation);//reference to the bottom nav view
        /**Pass the onClick_bottom navi listener to out bottom navigation*/
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) { /**If implementing screen rotation only this check have to be implemented*/
        /**Starting fragment
             * if its not given its just shows a blank page because none of the fragments selected*/
            displayFragment(new HomeFragment());
            toolbar.setTitle("Home");
        }
    }

    /**For onClick_bottom navi listener*/
    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) { //item -> gives which item is selected
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.homeNav:
                    selectedFragment=new HomeFragment(); // create home fragment
                    toolbar.setTitle("Home");
                    break;
                case R.id.emailNav:
                    selectedFragment=new EmailFragment(); // create email fragment
                    toolbar.setTitle("Email");
                    break;
                case R.id.noteNav:
                    selectedFragment=new NoteFragment(); // create note fragment
                    toolbar.setTitle("Note");
                    break;
            }
            if (selectedFragment != null){
                displayFragment(selectedFragment);
                return true; //true -> show the selected item
            }
         return false;
        }
    };
    /**To show the fragments*/
    private void displayFragment(Fragment fragment){
        getSupportFragmentManager() //to get FragmentManager object
                .beginTransaction() //to get FragmentTransaction object
                .replace(R.id.fragmentContainer, fragment) // Replacing container with homeFragment at starting
                .commit();
    }

    /**When backButton pressed it came back to HomeFragment <Breaks Material Design Concepts/>*/
    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() != R.id.homeNav) {
            bottomNavigationView.setSelectedItemId(R.id.homeNav);
        } else {
            super.onBackPressed();
        }
    }
}
