package com.baraka.thescout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.account_question_main) TextView mAskSignUp;
    @BindView(R.id.main_login_button) Button mMainLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String text = "Don't have an account? Sign Up";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                Toast.makeText(MainActivity.this,"Sign Up", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan,23,30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mAskSignUp.setText(spannableString);
        mAskSignUp.setMovementMethod(LinkMovementMethod.getInstance());

        mMainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Log In",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
