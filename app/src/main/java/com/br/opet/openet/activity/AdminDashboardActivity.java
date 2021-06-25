package com.br.opet.openet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.br.opet.openet.R;
import com.br.opet.openet.activity.defaultActivity.NoBarActivity;
import com.br.opet.openet.application.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminDashboardActivity extends NoBarActivity {

    private ApplicationContext applicationContext;
    private TextView loginTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        instantiateObjects();
        setStatusBarToAdministratorColor();
    }

    private void instantiateObjects() {
        applicationContext = (ApplicationContext) this.getApplicationContext();
        loginTimeTextView = findViewById(R.id.loginTimeTextView);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM");
        loginTimeTextView.setText("Login: "+ sdf.format(new Date()));
    }

    private void setStatusBarToAdministratorColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.administrator_main_color));
        }
    }
}