package com.baraka.thescout.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baraka.thescout.R;
import com.baraka.thescout.adapters.TeamsListAdapter;
import com.baraka.thescout.models.EplResponse;
import com.baraka.thescout.models.Team;
import com.baraka.thescout.network.EplApi;
import com.baraka.thescout.network.EplClient;
import com.baraka.thescout.ui.EplStatsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavHomeFragment extends Fragment {


    private TextView mErrorTextView;

    private RecyclerView mRecyclerView;

    private ProgressBar mProgressBar;


    private TeamsListAdapter mAdapter;

    public List<Team> teams;

    private static final String TAG = EplStatsActivity.class.getSimpleName();

    EplApi client = EplClient.getClient();

    Call<EplResponse> call = client.getTeams();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_home_fragment, container, false);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mErrorTextView = (TextView) view.findViewById(R.id.errorTextView);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        call.enqueue(new Callback<EplResponse>() {

            @Override
            public void onResponse(Call<EplResponse> call, Response<EplResponse> response) {

                hideProgressBar();

                if (response.isSuccessful()) {

                    teams = response.body().getTeams();
                    mAdapter = new TeamsListAdapter(getContext(), teams);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showTeams();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<EplResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }
        });


        return view;

    }

    @SuppressLint("SetTextI18n")
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showTeams() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
