package com.baraka.thescout.Fragments;

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

import com.baraka.thescout.Adapters.ScorersListAdapter;
import com.baraka.thescout.Adapters.TeamsListAdapter;
import com.baraka.thescout.R;
import com.baraka.thescout.models.EplResponse;
import com.baraka.thescout.models.EplScorersSearch;
import com.baraka.thescout.models.Scorer;
import com.baraka.thescout.models.Team;
import com.baraka.thescout.network.EplApi;
import com.baraka.thescout.network.EplClient;
import com.baraka.thescout.network.EplTopScorersApi;
import com.baraka.thescout.network.EplTopScorersClient;
import com.baraka.thescout.ui.EplStatsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavScorersFragment extends Fragment {

    private TextView mScorersErrorTextView;

    private RecyclerView mScorersRecyclerView;

    private ProgressBar mScorersProgressBar;


    private ScorersListAdapter mAdapter;

    public List<Scorer> scorers;

    private static final String TAG = EplStatsActivity.class.getSimpleName();

    EplTopScorersApi client = EplTopScorersClient.getScorersClient();

    Call<EplScorersSearch> call = client.getTopScorers();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_scorers_fragment, container, false);

        mScorersProgressBar = (ProgressBar) view.findViewById(R.id.scorersProgressBar);

        mScorersErrorTextView = (TextView) view.findViewById(R.id.scorersErrorTextView);

        mScorersRecyclerView = (RecyclerView) view.findViewById(R.id.scorersRecyclerView);

        call.enqueue(new Callback<EplScorersSearch>() {

            @Override
            public void onResponse(Call<EplScorersSearch> call, Response<EplScorersSearch> response) {

                hideProgressBar();

                if (response.isSuccessful()) {

                    scorers = response.body().getScorers();
                    mAdapter = new ScorersListAdapter(getContext(), scorers);
                    mScorersRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(getContext());
                    mScorersRecyclerView.setLayoutManager(layoutManager);
                    mScorersRecyclerView.setHasFixedSize(true);

                    showScorers();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<EplScorersSearch> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }
        });


        return view;

    }

    @SuppressLint("SetTextI18n")
    private void showFailureMessage() {
        mScorersErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mScorersErrorTextView.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void showUnsuccessfulMessage() {
        mScorersErrorTextView.setText("Something went wrong. Please try again later");
        mScorersErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showScorers() {
        mScorersRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mScorersProgressBar.setVisibility(View.GONE);
    }
}
