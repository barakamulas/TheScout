package com.baraka.thescout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account_question_login)TextView mAccountQuestionLogin;
    @BindView(R.id.login_button)Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        String text = "Don't have an account? Sign Up";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                Toast.makeText(LoginActivity.this,"Sign Up", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan,23,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mAccountQuestionLogin.setText(spannableString);
        mAccountQuestionLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
