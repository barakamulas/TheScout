package com.baraka.thescout.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baraka.thescout.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity{

    @BindView(R.id.account_question_sign_up) TextView mSignUpAccountQuestion;
    @BindView(R.id.userNameEditText)EditText mUserNameEditText;
    @BindView(R.id.email)EditText mEmailEditText;
    @BindView(R.id.password)EditText mPassword;
    @BindView(R.id.confirm_password)EditText mConfirmPasswordEditText;
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
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan,25,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSignUpAccountQuestion.setText(spannableString);
        mSignUpAccountQuestion.setMovementMethod(LinkMovementMethod.getInstance());


        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUserNameEditText.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmailEditText.getText().toString();
                String confirmPassword = mConfirmPasswordEditText.getText().toString();

                if(TextUtils.isEmpty(user)){
                    Toast.makeText(SignUpActivity.this,"User Name Required",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this,"Email Required",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this,"Password Required",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(SignUpActivity.this,"Please Confirm Your Password",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(confirmPassword)){
                    Toast.makeText(SignUpActivity.this,"Make Sure Your Passwords Match",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUpActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, Dashboard.class);
                    intent.putExtra("user",user);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }

            }
        });

    }
}
