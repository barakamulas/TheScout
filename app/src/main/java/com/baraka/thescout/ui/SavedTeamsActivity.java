package com.baraka.thescout.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baraka.thescout.Constants;
import com.baraka.thescout.adapters.FirebaseTeamListAdapter;
import com.baraka.thescout.adapters.FirebaseTeamViewHolder;
import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.baraka.thescout.util.OnStartDragListener;
import com.baraka.thescout.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedTeamsActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mTeamReference;
//    private FirebaseTeamListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private FirebaseRecyclerAdapter<Team, FirebaseTeamViewHolder> mFirebaseAdapter;

    @BindView(R.id.savedRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.teamTextView)TextView mTeamTextviewl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Slidr.attach(this);

        setContentView(R.layout.activity_saved_teams);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String uid = user.getUid();
        mTeamReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEAMS).child(uid);


        mTeamReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEAMS);
        setUpFirebaseAdapter();
    }


//    private void setUpFirebaseAdapter(){
//
//        FirebaseRecyclerOptions<Team> options =
//                new FirebaseRecyclerOptions.Builder<Team>()
//                        .setQuery(mTeamReference, Team.class)
//                        .build();
//
//        mFirebaseAdapter = new FirebaseTeamListAdapter(options, mTeamReference, this, this);
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//        mRecyclerView.setHasFixedSize(true);
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//    }
//


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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_list_item_drag, parent, false);
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

    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }

}
