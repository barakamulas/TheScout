package com.baraka.thescout.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baraka.thescout.Constants;
import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.baraka.thescout.ui.TeamDetailsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public ImageView mTeamImageView;
    public TextView mTeamNameTextView;

    public FirebaseTeamViewHolder (View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }


    public void bindTeam(Team team) {
        mTeamImageView = (ImageView) mView.findViewById(R.id.teamImageView);
        mTeamNameTextView = (TextView) mView.findViewById(R.id.teamNameTextView);
        ImageView infoView = (ImageView) mView.findViewById(R.id.info);
        Picasso.get().load(team.getCrestUrl()).error(R.drawable.university).into(mTeamImageView);
        mTeamNameTextView.setText(team.getName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Team> teams = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEAMS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    teams.add(snapshot.getValue(Team.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, TeamDetailsActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("teams", Parcels.wrap(teams));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
