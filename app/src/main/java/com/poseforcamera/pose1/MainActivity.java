package com.poseforcamera.pose1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String image_url;
    private ArrayList<String> imageUrls;
    private Toolbar toolbar;
    private Spinner mySpinner;
    public  String categoryResults = "";
    public static int decisionFrontBack=0;
    public static Fragment selectedFragment;
    public static String selectedFrontCameraFragment="front";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mySpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,
                        mySpinner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT)
                        .show();

                String selectedItem = adapterView.getItemAtPosition(i).toString();
                // Log.d("Selected category item",selectedItem);

               /* if(selectedItem.equals("Solo"))
                {  // do your stuff} */
                categoryResults = selectedItem;
                if(selectedFrontCameraFragment=="front"){
                      selectedFragment = new FrontCameraFragment(categoryResults);
                      selectedFrontCameraFragment="front";
                      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                else{
                      selectedFragment = new BackCameraFragment(categoryResults);
                      selectedFrontCameraFragment="back";
                      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
             //  selectedFragment = new FrontCameraFragment();
              // selectedFrontCameraFragment="front";
            //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FrontCameraFragment()).commit();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

             selectedFragment = new FrontCameraFragment(categoryResults);
             BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
             getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
             selectedFrontCameraFragment="front";

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        selectedFragment = new FrontCameraFragment(categoryResults);
                        selectedFrontCameraFragment="front";
                        decisionFrontBack=0;
                        break;
                    case R.id.android:
                        //  selectedFragment=new BackCameraFragment();
                        selectedFragment = new BackCameraFragment(categoryResults);
                        selectedFrontCameraFragment="back";
                        decisionFrontBack=1;

                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favourites) {

            Intent intent = new Intent(this, FavRecyclerFinal.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.profile) {
//            Intent intent=new Intent(this, com.poseforcamera.posemaster.ViewProfile.class);
//            startActivity(intent);
        }
        return true;
    }
}

