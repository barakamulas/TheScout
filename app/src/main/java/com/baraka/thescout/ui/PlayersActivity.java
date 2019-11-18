package com.baraka.thescout.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baraka.thescout.MyPlayersAdapter;
import com.baraka.thescout.R;
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

public class PlayersActivity extends AppCompatActivity {

    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.playersTextView) TextView mPlayerTextView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private static final String TAG = PlayersActivity.class.getSimpleName();

//    private String[] names = new String[] {"John","James","Jim","Jerome","Jose","Jambi"};
//
//    private String[] positions = new String[] {"Centre Back","Goal Keeper","Full Back","Winger","Holding Mid","Centre Forward"};
//
//    private String[] teams = new String[] {"Arsenal","Manchester United","Manchester City","Liverpool","Chelsea","Leicester City"};

    EplApi client = EplClient.getClient();

    Call<EplResponse> call = client.getTeams();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ButterKnife.bind(this);

//        MyPlayersAdapter adapter = new MyPlayersAdapter(this, android.R.layout.simple_list_item_1, names, positions,teams); //Using custom array adapter
//        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = ((TextView)view).getText().toString();
                Log.v(TAG, "In the onItemClickListener!");
                Toast.makeText(PlayersActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });

        call.enqueue(new Callback<EplResponse>() {

            @Override
            public void onResponse(Call<EplResponse> call, Response<EplResponse> response) {

                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Team> teamsList = response.body().getTeams();
                    String[] teams = new String[teamsList.size()];
//                    String[] categories = new String[teamsList.size()];

                    for (int i = 0; i < teams.length; i++){
                        teams[i] = teamsList.get(i).getName();
                    }

//                    for (int i = 0; i < categories.length; i++) {
//                        Category category = teamsList.get(i).getCategories().get(0);
//                        categories[i] = category.getTitle();
//                    }

                    ArrayAdapter adapter = new MyPlayersAdapter(PlayersActivity.this, android.R.layout.simple_list_item_1, teams);
                    mListView.setAdapter(adapter);

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

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showTeams() {
        mListView.setVisibility(View.VISIBLE);
        mPlayerTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
