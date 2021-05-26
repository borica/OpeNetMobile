package com.br.opet.openet.activity;

import android.content.Intent;
import android.os.Bundle;

import com.br.opet.openet.R;

public class MainActivity extends NoBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goLoginActivity();
    }

    private void goLoginActivity() {
        Intent loginActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(loginActivityIntent);
    }

}