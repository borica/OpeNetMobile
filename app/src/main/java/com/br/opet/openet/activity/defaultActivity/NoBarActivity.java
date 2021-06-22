package com.br.opet.openet.activity.defaultActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NoBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
    }

}