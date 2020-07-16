package com.omurgun.registerwithanonymous;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView username;
    private String userName;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        translucentStatusBarFlag();
        init();
        sharedPreferences = this.getSharedPreferences("com.omurgun.registerwithanonymous",Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("userName", "null");
        username.setText("anonymous :"+userName);
    }
    private void init() {
        username = findViewById(R.id.username);
    }
    private void translucentStatusBarFlag() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}