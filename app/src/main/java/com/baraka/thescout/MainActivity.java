package com.baraka.thescout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mAskSignUp = findViewById(R.id.account_question_main);
        String text = "Don't have an account? Sign Up";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this,"Sign Up Clicked", Toast.LENGTH_SHORT).show();
            }
        };

        ss.setSpan(clickableSpan,23,30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mAskSignUp.setText(ss);
        mAskSignUp.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
