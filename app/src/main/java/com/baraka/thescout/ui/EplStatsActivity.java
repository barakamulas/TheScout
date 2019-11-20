package com.baraka.thescout.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.baraka.thescout.Fragments.NavHomeFragment;
import com.baraka.thescout.Fragments.NavScorersFragment;
import com.baraka.thescout.Fragments.NavStandingsFragment;
import com.baraka.thescout.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class EplStatsActivity extends AppCompatActivity {

    private static final String TAG = EplStatsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epl_stats);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(navListener);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NavHomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new NavHomeFragment();
                            break;
                        case R.id.nav_scorers:
                            selectedFragment = new NavScorersFragment();
                            break;
                        case R.id.nav_standings:
                            selectedFragment = new NavStandingsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
