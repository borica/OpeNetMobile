
package com.br.opet.openet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.br.opet.openet.R;

public class LoginActivity extends NoBarActivity implements View.OnClickListener {

    //Inputs
    private EditText username;
    private EditText password;

    //Actions
    private Button loginBtn;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instanciateScreenObjects();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.loginButton:
                validateFields();
                break;
            case R.id.signUpTextView:
                redirectToSignUpActivity();
                break;
        }
    }

    private void instanciateScreenObjects() {
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.loginButton);
        signUp   = findViewById(R.id.signUpTextView);

        loginBtn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private void validateFields() {
        boolean valid = true;

        if(username.getText().toString().isEmpty()){
            username.setError("Favor inserir seu nome de usuário");
            valid = false;
        }

        if(password.getText().toString().isEmpty()){
            password.setError("Favor inserir sua senha");
            valid = false;
        }
    }

    //Redirect to SignUp Activity
    private void redirectToSignUpActivity() {
        Intent signUpActivityIntent = new Intent();
    }
}