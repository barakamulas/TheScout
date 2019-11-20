package com.baraka.thescout.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamsListAdapter extends RecyclerView.Adapter<TeamsListAdapter.TeamViewHolder> {

    private List<Team> mTeams;
    private Context mContext;

    public TeamsListAdapter(Context context, List<Team> teams) {
        mContext = context;
        mTeams = teams;


        }

    @Override
    public TeamsListAdapter.TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_list_item, parent, false);
        TeamViewHolder viewHolder = new TeamViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TeamsListAdapter.TeamViewHolder holder, int position) {
        holder.bindteam(mTeams.get(position));
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }
    public class TeamViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.teamImageView)ImageView mTeamImageView;
        @BindView(R.id.teamNameTextView)TextView mNameTextView;


        private Context mContext;

        public TeamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindteam(Team team) {
            Picasso.get().load(team.getCrestUrl()).into(mTeamImageView);
            mNameTextView.setText(team.getName());


        }
    }
}
