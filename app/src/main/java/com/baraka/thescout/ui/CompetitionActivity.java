package com.baraka.thescout.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baraka.thescout.R;
import com.baraka.thescout.TeamsListAdapter;
import com.baraka.thescout.models.EplResponse;
import com.baraka.thescout.models.Team;
import com.baraka.thescout.network.EplApi;
import com.baraka.thescout.network.EplClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionActivity extends AppCompatActivity {


    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    private TeamsListAdapter mAdapter;

    public List<Team> teams;

    private static final String TAG = CompetitionActivity.class.getSimpleName();

    EplApi client = EplClient.getClient();

    Call<EplResponse> call = client.getTeams();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ButterKnife.bind(this);


        call.enqueue(new Callback<EplResponse>() {

            @Override
            public void onResponse(Call<EplResponse> call, Response<EplResponse> response) {

                hideProgressBar();

                if (response.isSuccessful()) {

                    teams = response.body().getTeams();
                    mAdapter = new TeamsListAdapter(CompetitionActivity.this, teams);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(CompetitionActivity.this);
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
