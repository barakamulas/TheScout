package com.baraka.thescout.adapters;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.baraka.thescout.fragments.TeamDetailsFragment;
import com.baraka.thescout.models.Team;

import java.util.List;


public class TeamPagerAdapter extends FragmentPagerAdapter {

    List<Team> mTeams;

    public TeamPagerAdapter(FragmentManager fm, int behavior, List<Team> teams) {
        super(fm, behavior);
        mTeams = teams;
    }

    @Override
    public Fragment getItem(int position) {
        return TeamDetailsFragment.newInstance(mTeams.get(position));
    }

    @Override
    public int getCount() {
        return mTeams.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTeams.get(position).getName();
    }
}

