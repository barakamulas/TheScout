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

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.account_question_sign_up) TextView mSignUpAccountQuestion;
    @BindView(R.id.sign_up_button) Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        String text = "Already have an account? Log In";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                Toast.makeText(SignUpActivity.this,"Log In", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan,25,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSignUpAccountQuestion.setText(spannableString);
        mSignUpAccountQuestion.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
