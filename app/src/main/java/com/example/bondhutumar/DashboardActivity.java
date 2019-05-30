package com.example.bondhutumar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bondhutumar.utils.MySharedPref;

public class DashboardActivity extends AppCompatActivity {
    private LinearLayout card1, card3;
    private MySharedPref sharedPref;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ActionBar actionBar;
    private MySharedPref mySharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //findviews by id
        findViews();

        //initialization
        init();

        navMenuToggle();


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, Depression.class);
                startActivity(intent);

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Insomnia.class));
            }
        });


    }

    private void init() {
        sharedPref = new MySharedPref(getApplicationContext());
        actionBar = getSupportActionBar();

        mySharedPref = new MySharedPref(this);

    }

    private void updateNavBar() {
        View view = navigationView.getHeaderView(0);
        TextView uName = view.findViewById(R.id.nav_header_tv_username);
        if (sharedPref.retreiveLoggedStatus()) {
            uName.setText(sharedPref.retrieveName());

            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_logout).setVisible(true);
            menu.findItem(R.id.nav_login).setVisible(false);

        } else {
            uName.setText("Guest");

            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_logout).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(true);
        }

    }

    private void findViews() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        card1 = findViewById(R.id.card1);
        card3 = findViewById(R.id.card3);

    }

    private void navMenuToggle() {
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        updateNavBar();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.nav_login) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                if (id == R.id.nav_logout) {
                    mySharedPref.saveLogStatus(false);

                    updateNavBar();
                    drawerLayout.closeDrawers();

                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navigationView.bringToFront();
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
