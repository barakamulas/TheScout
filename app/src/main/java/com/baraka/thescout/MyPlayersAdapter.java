package com.baraka.thescout;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyPlayersAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mNames;
    private String[] mPositions;
    private String[] mTeams;

    public MyPlayersAdapter(Context mContext, int resource, String[] mNames, String[] mPositions, String[] mTeams){
        super(mContext, resource);
        this.mContext = mContext;
        this.mNames = mNames;
        this.mPositions = mPositions;
        this.mTeams = mTeams;
    }

    @Override
    public Object getItem(int position) {
        String name = mNames[position];
        String playerPosition = mPositions[position];
        String team = mTeams[position];
        return String.format("%s \nPlays as: %s for %s" , name, playerPosition, team);
    }

    @Override
    public int getCount() {
        return mNames.length;
    }
}
