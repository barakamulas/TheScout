package com.baraka.thescout.Adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import androidx.recyclerview.widget.RecyclerView;
import android.content.ContentResolver;

import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.squareup.picasso.Picasso;
import com.bumptech.glide.util.Preconditions;

import java.io.InputStream;
import java.util.List;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

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



//            Glide
//                    .with(mContext)
//                    .load(team.getCrestUrl())
//                    .into(mTeamImageView);

            Picasso.get().load(team.getCrestUrl()).into(mTeamImageView);
            mNameTextView.setText(team.getName());


        }
    }
}
