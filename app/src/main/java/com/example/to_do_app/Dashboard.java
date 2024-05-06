package com.example.to_do_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if(itemId == R.id.home)
                {
                    loadfragment(new HomeFragment(),false);

                } else if (itemId == R.id.profile) {

                    loadfragment(new ProfileFragment(),false);
                } else if (itemId == R.id.complete) {

                    loadfragment(new CompleteFragment(), false);
                }

                else {
                    loadfragment(new HomeFragment(),false);
                }
                return true;
            }
        });

        loadfragment(new HomeFragment(),true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.about){
            Toast.makeText(this,"You Clicked On About",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DevProfile.class);
            startActivity(intent);
        }

        return  true;
    }

    private void loadfragment(Fragment fragment, boolean isappintialied){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isappintialied)
        {
            Toast.makeText(this,"You Clicked On About",Toast.LENGTH_SHORT).show();
            fragmentTransaction.add(R.id.fragment,fragment);
        }else{
            Toast.makeText(this,"You Clicked On About",Toast.LENGTH_SHORT).show();
            fragmentTransaction.replace(R.id.fragment,fragment);
        }

        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();
    }
}