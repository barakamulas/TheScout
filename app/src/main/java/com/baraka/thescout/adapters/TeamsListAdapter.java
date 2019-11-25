package com.baraka.thescout.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.baraka.thescout.ui.TeamDetailsActivity;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;
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

    public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.teamImageView)ImageView mTeamImageView;
        @BindView(R.id.teamNameTextView)TextView mNameTextView;
        @BindView(R.id.info) ImageView mInfo;


        private Context mContext;
//        private Activity EplStatsActivity;


        public TeamViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mInfo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, TeamDetailsActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("teams", Parcels.wrap(mTeams));
            mContext.startActivity(intent);
        }

        public void bindteam(Team team) {


//            GlideToVectorYou.justLoadImage((, Uri.parse(team.getCrestUrl()), mTeamImageView);



//
//            Glide
//                    .with(mContext)
//                    .load(team.getCrestUrl())
//                    .error(R.drawable.university)//in case no image is loaded
//                    .into(mTeamImageView);

            Picasso.get().load(team.getCrestUrl()).error(R.drawable.university).into(mTeamImageView);
            mNameTextView.setText(team.getName());


        }
    }
}
