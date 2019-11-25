package com.baraka.thescout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baraka.thescout.R;
import com.baraka.thescout.models.Scorer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScorersListAdapter extends RecyclerView.Adapter<ScorersListAdapter.ScorerViewHolder> {

    public static int teamId;

    private List<Scorer> mScorers;

    private Context mContext;

    public ScorersListAdapter(Context context, List<Scorer> scorers) {
        mContext = context;
        mScorers = scorers;
    }

    @Override
    public ScorersListAdapter.ScorerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scorers_list_item, parent, false);
        ScorersListAdapter.ScorerViewHolder scorerViewHolder = new ScorersListAdapter.ScorerViewHolder(view);
        return scorerViewHolder;
    }

    @Override
    public void onBindViewHolder(ScorersListAdapter.ScorerViewHolder holder, int position) {
        holder.bindscorer(mScorers.get(position));
    }

    @Override
    public int getItemCount() {
        return mScorers.size();
    }
    public class ScorerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.scorerTeamImageView)ImageView mScorerTeamImageView;
        @BindView(R.id.scorerNameTextView)TextView mScorerNameTextView;
        @BindView(R.id.goalsTextView)TextView mGoalsTextView;


        private Context mContext;

        public ScorerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindscorer(Scorer scorer) {

            teamId = scorer.getTeam().getId();
//            Team team = scorer.getTeam();
//            Picasso.get().load(team.getId()).into(mScorerTeamImageView);
            mScorerNameTextView.setText(scorer.getPlayer().getName());
            mGoalsTextView.setText(scorer.getNumberOfGoals().toString());
        }
    }
}
