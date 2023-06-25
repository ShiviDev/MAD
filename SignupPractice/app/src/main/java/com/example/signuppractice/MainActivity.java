package com.example.signuppractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText pswd, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pswd=findViewById(R.id.password);
        username=findViewById(R.id.username);
    }
    public void signup(View v){
        String usr=username.getText().toString();
        String pass=pswd.getText().toString();

        if(pass.length()>=8&&validatepassword(pass)){
            Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,signin.class);
            Bundle b = new Bundle();
            b.putString("username",usr);
            b.putString("password",pass);
            i.putExtras(b);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"enter password with 8 or more characters",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validatepassword(String pass){
        Pattern ptrn;
        Matcher mat;
        String password = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[<>/@#$%])(?=\\S+$).{8,}$";
        ptrn = Pattern.compile(password);
        mat=ptrn.matcher(pass);
        return mat.matches();
    }
}