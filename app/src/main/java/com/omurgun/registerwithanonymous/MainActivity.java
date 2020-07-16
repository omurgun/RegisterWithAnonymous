package com.omurgun.registerwithanonymous;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnAnonymous;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(checkCurrentUser())
        {
            goHome();
        }
        else
        {
            btnAnonymous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goLogin();
                }
            });
        }

    }
    private void init() {
        btnAnonymous = findViewById(R.id.btnAnonymous);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }
    private void goLogin() {
        Toast.makeText(MainActivity.this,"Your account has been successfully created",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this,UserSetNameActivity.class);
        startActivity(intent);
        finish();
    }
    private void goHome() {
        Toast.makeText(MainActivity.this,"Your account has been successfully created",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private boolean checkCurrentUser() {

        boolean result = false;
        if(firebaseUser!= null)
        {
            result =true;
        }

        return result;
    }
}