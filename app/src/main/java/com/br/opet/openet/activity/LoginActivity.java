
package com.br.opet.openet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.br.opet.openet.R;
import com.br.opet.openet.application.ApplicationContext;
import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.service.impl.UserServiceImpl;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends NoBarActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getName();

    //Global ApplicationContext
    private ApplicationContext applicationContext;

    //Inputs
    private TextInputEditText username;
    private TextInputEditText password;

    //Actions
    private Button loginBtn;
    private TextView signUpTextView;
    private TextView loginErrorMessageTextView;

    private UserServiceImpl userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instanciateScreenObjects();
        if(applicationContext.getLoggedUser() != null){
            redirectToDashboardActivity();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.loginButton:
                requestAuth();
                break;
            case R.id.signUpTextView:
                redirectToSignUpActivity();
                break;
        }
    }

    private void requestAuth() {
        if(validateFields()){
            //New request DTO
            RequestUserAuthDTO requestUserAuth = new RequestUserAuthDTO(username.getText().toString(), password.getText().toString());
            try {
                userService.authenticate(this, requestUserAuth, new UserServiceResponseListener() {
                    @Override
                    public void onError(String message) {
                        loginErrorEvent();
                    }
                    @Override
                    public void onResponse(UserModel userModelResponse) {
                        //Sets successfully retrieved user to global application context
                        applicationContext.setLoggedUser(userModelResponse);
                        if(applicationContext.getLoggedUser() != null) {
                            redirectToDashboardActivity();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Erro ao consultar usuário:\n" + e.getMessage());
            }
        }
    }

    private void instanciateScreenObjects() {

        applicationContext = (ApplicationContext) this.getApplicationContext();

        username = findViewById(R.id.usernameTextInputEditText);
        password = findViewById(R.id.passwordTextInputEditText);
        loginBtn = findViewById(R.id.loginButton);
        signUpTextView   = findViewById(R.id.signUpTextView);
        loginErrorMessageTextView = findViewById(R.id.loginErrorMessageTextView);

        loginBtn.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);

        userService = new UserServiceImpl();
    }

    private Boolean validateFields() {
        boolean valid = true;

        if(username.getText().toString().isEmpty()){
            username.setError("Favor inserir seu nome de usuário");
            valid = false;
        }

        if(password.getText().toString().isEmpty()){
            password.setError("Favor inserir sua senha");
            valid = false;
        }

        return valid;
    }

    private void loginErrorEvent() {

        loginErrorMessageTextView.setVisibility(View.VISIBLE);
        username.setText("");
        password.setText("");
        username.setError(null);
        password.setError(null);

    }

    //Redirect to SignUp Activity
    private void redirectToSignUpActivity() {
        Intent signUpActivityIntent = new Intent();
    }

    private void redirectToDashboardActivity() {
        Intent dashboardActivityIntent = new Intent(this, DashboardActivity.class);
        startActivity(dashboardActivityIntent);
        finish();
    }
}