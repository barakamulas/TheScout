package com.baraka.thescout.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.baraka.thescout.R;
import com.baraka.thescout.adapters.TeamPagerAdapter;
import com.baraka.thescout.models.Team;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamDetailsActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private TeamPagerAdapter adapterViewPager;
    List<Team> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        ButterKnife.bind(this);

        mTeams = Parcels.unwrap(getIntent().getParcelableExtra("teams"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new TeamPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTeams);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
