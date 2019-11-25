package com.baraka.thescout.ui;

import androidx.annotation.NonNull;

import com.baraka.thescout.fragments.HomeFragment;
import com.baraka.thescout.fragments.NotificationsFragment;
import com.baraka.thescout.fragments.ProfileFragment;
import com.baraka.thescout.R;
import com.baraka.thescout.fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mUserTextView;
    private TextView mEmailTextView;





    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        Intent intent = getIntent();

        String user = intent.getStringExtra("user");

        String email = intent.getStringExtra("email");

        View headerView = navigationView.getHeaderView(0);

        mUserTextView = (TextView) headerView.findViewById(R.id.userTextView); // how you get a view on the header of navigation drawer

        mEmailTextView = (TextView) headerView.findViewById(R.id.emailTextView);

        mUserTextView.setText(user);

        mEmailTextView.setText(email);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    public void buttonClick(View v) {
        switch(v.getId()) {
            case R.id.playersBtn:
                Intent myIntent = new Intent(DashboardActivity.this, EplStatsActivity.class); //Click Listener on a button on a Fragment
                startActivity(myIntent);
                break;
            case R.id.profile_pic:
                Intent cameraIntent = new Intent();
                cameraIntent.setAction(android.content.Intent.ACTION_VIEW);
                cameraIntent.setType("image/*");
                cameraIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(cameraIntent);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
