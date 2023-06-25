package com.example.signuppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    EditText username, password;
    Button signin;
    int attempts=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        username=findViewById(R.id.uid);
        password=findViewById(R.id.pwd);
        signin=findViewById(R.id.signin);
    }
    public void signin(View v){
        Bundle b = getIntent().getExtras();
        String uname = b.getString("username");
        String pass = b.getString("password");
        if(username.getText().toString().equals(uname)&&(password.getText().toString().equals(pass))){
            Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
            attempts=0;
            Intent i = new Intent(this,success.class);
            startActivity(i);
        }
        else{
            attempts++;
            Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show();
        }
        if(attempts>=3){
            signin.setEnabled(false);
        }
    }
}