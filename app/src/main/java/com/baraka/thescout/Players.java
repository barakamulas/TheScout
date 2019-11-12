package com.baraka.thescout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Players extends AppCompatActivity {

    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.playersTextView)TextView mLocationTextView;

    private static final String TAG = Players.class.getSimpleName();

    private String[] names = new String[] {"John","James","Jim","Jerome","Jose","Jambi"};

    private String[] positions = new String[] {"Centre Back","Goal Keeper","Full Back","Winger","Holding Mid","Centre Forward"};

    private String[] teams = new String[] {"Arsenal","Manchester United","Manchester City","Liverpool","Chelsea","Leicester City"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        ButterKnife.bind(this);

        MyPlayersAdapter adapter = new MyPlayersAdapter(this, android.R.layout.simple_list_item_1, names, positions,teams); //Using custom array adapter
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = ((TextView)view).getText().toString();
                Log.v(TAG, "In the onItemClickListener!");
                Toast.makeText(Players.this, name, Toast.LENGTH_SHORT).show();
            }
        });



    }


}
