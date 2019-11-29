package com.baraka.thescout.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baraka.thescout.Constants;
import com.baraka.thescout.FirebaseTeamViewHolder;
import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedTeamsActivity extends AppCompatActivity {

    private DatabaseReference mTeamReference;
    private FirebaseRecyclerAdapter<Team, FirebaseTeamViewHolder> mFirebaseAdapter;

    @BindView(R.id.savedRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.teamTextView)TextView mTeamTextviewl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_teams);
        ButterKnife.bind(this);



        mTeamReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEAMS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Team> options =
                new FirebaseRecyclerOptions.Builder<Team>()
                        .setQuery(mTeamReference, Team.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Team, FirebaseTeamViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseTeamViewHolder firebaseTeamViewHolder, int position, @NonNull Team team) {
                firebaseTeamViewHolder.bindTeam(team);
            }

            @NonNull
            @Override
            public FirebaseTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_list_item, parent, false);
                return new FirebaseTeamViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

}
