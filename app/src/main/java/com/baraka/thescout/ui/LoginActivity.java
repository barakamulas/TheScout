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

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account_question_login)TextView mAccountQuestionLogin;
    @BindView(R.id.login_button)Button mLoginButton;
    @BindView(R.id.userNameEditText)EditText mUserName;
    @BindView(R.id.userPassword)EditText mUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        String[] users = {"baraka","frankline"};
        String[] passwords = {"android","mc21"};
        String[] emails = {"ozil@gmail.com","auba@gmail.com"};

        List<String> passwordList = Arrays.asList(passwords);
        List<String> userList = Arrays.asList(users);
        List<String> emailList = Arrays.asList(emails);


        String text = "Don't have an account? Sign Up";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan,23,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mAccountQuestionLogin.setText(spannableString);
        mAccountQuestionLogin.setMovementMethod(LinkMovementMethod.getInstance());

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUserName.getText().toString();
                String password = mUserPassword.getText().toString();

                if(TextUtils.isEmpty(user)){
                    Toast.makeText(LoginActivity.this,"Username Required", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Password Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(userList.contains(user)){
                        int index = userList.indexOf(user);
                        if(passwordList.get(index).equals(password)){
                            Toast.makeText(LoginActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.putExtra("user",user);
                            intent.putExtra("email",emailList.get(index));
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this,"Wrong Username or password",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(LoginActivity.this,"Wrong username or password",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}
