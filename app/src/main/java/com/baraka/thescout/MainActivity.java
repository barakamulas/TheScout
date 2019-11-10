package com.baraka.thescout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mAskSignUp = findViewById(R.id.account_question_main);
        String text = "Don't have an account? Sign Up";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan blue = new ForegroundColorSpan(Color.BLUE);
        ss.setSpan(blue,23,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mAskSignUp.setText(ss);

    }
}
