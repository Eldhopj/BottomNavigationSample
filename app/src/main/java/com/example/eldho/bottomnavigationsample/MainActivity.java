package com.example.eldho.bottomnavigationsample;

/**Add design dependency
 * Create a menu for to pass into activity_main -> bottom navigation widget
 * Create a color resource folder for navigation selection and non selection colors
 * Create fragments with xml for each navi buttons (Right click -> new -> fragments)*/

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);//reference to the bottom nav view
        /**Pass the onClick_bottom navi listener (below) to out bottom navigation*/
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) { /**If implementing screen rotation only this check have to be implemented*/
            /**Starting fragment
             * if its not given its just shows a blank page because none of the fragments selected*/
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment())
                    .commit();
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
                    break;
                case R.id.emailNav:
                    selectedFragment=new EmailFragment(); // create email fragment
                    break;
                case R.id.noteNav:
                    selectedFragment=new NoteFragment(); // create note fragment
                    break;
            }
            /**To show the fragments*/
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer,selectedFragment)
                    .commit();
            return true; //true -> show the selected item
        }
    };
}
