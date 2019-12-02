package com.baraka.thescout.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.baraka.thescout.util.ItemTouchHelperAdapter;
import com.baraka.thescout.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class FirebaseTeamListAdapter extends FirebaseRecyclerAdapter<Team, FirebaseTeamViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;



    public FirebaseTeamListAdapter(FirebaseRecyclerOptions<Team> options,
                                   DatabaseReference ref,
                                   OnStartDragListener onStartDragListener,
                                   Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

//    @Override
//    protected void onBindViewHolder(@NonNull FirebaseTeamViewHolder firebaseTeamViewHolder, int position, @NonNull Team team) {
//        firebaseTeamViewHolder.bindTeam(team);
//    }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseTeamViewHolder firebaseTeamViewHolder, int position, @NonNull Team team) {
        firebaseTeamViewHolder.bindTeam(team);
        firebaseTeamViewHolder.mTeamImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(firebaseTeamViewHolder);
                }
                return false;
            }
        });
    }



//    @Override
//    protected void onBindViewHolder(@NonNull final FirebaseTeamViewHolder firebaseTeamViewHolder, int position, @NonNull Team team) {
//        firebaseTeamViewHolder.bindTeam(team);
//        firebaseTeamViewHolder.mTeamImageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
//                    mOnStartDragListener.onStartDrag(firebaseTeamViewHolder);
//                }
//                return false;
//            }
//        });
//    }

//    @NonNull
//    @Override
//    public FirebaseProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_items_drag, parent, false);
//        return  new FirebaseProjectViewHolder(view);
//    }


    @NonNull
    @Override
    public FirebaseTeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_list_item_drag, parent, false);
        return new FirebaseTeamViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        getRef(position).removeValue();
    }


}
