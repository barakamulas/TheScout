package com.baraka.thescout.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baraka.thescout.Constants;
import com.baraka.thescout.R;
import com.baraka.thescout.models.Team;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamDetailsFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.teamDetailsImageView) ImageView mImageLabel;
    @BindView(R.id.teamDetailsNameTextView) TextView mNameLabel;
    @BindView(R.id.teamWebsiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.teamPhoneTextView) TextView mPhoneLabel;
    @BindView(R.id.teamAddressTextView) TextView mAddressLabel;
    @BindView(R.id.teamEmailTextView) TextView mEmailLabel;
    @BindView(R.id.save_button)Button mSaveButton;

   
    private Team mTeam;

    public TeamDetailsFragment() {

    }

    public static TeamDetailsFragment newInstance(Team team) {
        TeamDetailsFragment teamDetailFragment = new TeamDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("team", Parcels.wrap(team));
        teamDetailFragment.setArguments(args);
        return teamDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTeam = Parcels.unwrap(getArguments().getParcelable("team"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_details, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mTeam.getCrestUrl()).error(R.drawable.university).into(mImageLabel);


        mWebsiteLabel.setText(mTeam.getWebsite());
        mNameLabel.setText(mTeam.getName());
        mPhoneLabel.setText(mTeam.getPhone());
        mAddressLabel.setText(mTeam.getAddress().toString());
        mEmailLabel.setText(mTeam.getEmail());

        mWebsiteLabel.setOnClickListener(this);
        mEmailLabel.setOnClickListener(this);
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mTeam.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mTeam.getPhone()));
            startActivity(phoneIntent);
        }if(v == mEmailLabel){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",mTeam.getEmail(),null));
            emailIntent.putExtra(Intent.EXTRA_EMAIL,mTeam.getEmail());
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"");
            startActivity(Intent.createChooser(emailIntent,"Choose an Email Client"));

        }
        if (v == mSaveButton){
            DatabaseReference teamRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_TEAMS);
            teamRef.push().setValue(mTeam);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}