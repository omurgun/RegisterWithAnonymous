package com.omurgun.registerwithanonymous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserSetNameActivity extends AppCompatActivity {

    private Button btnUsernameSave;
    private EditText txtUserName;
    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPreferences;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set_name);
        translucentStatusBarFlag();
        init();
        sharedPreferences = this.getSharedPreferences("com.omurgun.registerwithanonymous", Context.MODE_PRIVATE);
        btnUsernameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUsername();
            }
        });
    }
    private void SaveUsername() {
        username = txtUserName.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"Username Cannot Be Empty!",Toast.LENGTH_LONG).show();
        }
        else
        {
            System.out.println("username :\t"+username);

            firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        System.out.println("Signed in Anonymously");
                        sharedPreferences.edit().putString("userName", username).apply();
                        goHome();

                    } else {
                        System.out.println("There was an error signing in");
                    }

                }
            });


        }
    }
    private void init() {
        btnUsernameSave = findViewById(R.id.btnUsernameSave);
        txtUserName = findViewById(R.id.usernametxt);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void goHome() {
        Toast.makeText(UserSetNameActivity.this,"Your account has been successfully created",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UserSetNameActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void translucentStatusBarFlag() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}