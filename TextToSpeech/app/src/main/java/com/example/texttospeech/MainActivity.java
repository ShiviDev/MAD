package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText pitchrate, speechrate, text;
    TextToSpeech t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pitchrate=findViewById(R.id.pitch);
        speechrate=findViewById(R.id.speechrate);
        text=findViewById(R.id.texttospeak);
        t = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status){
                if(status==TextToSpeech.SUCCESS){
                    t.setLanguage(Locale.ENGLISH);
                }
                else{
                    Toast.makeText(MainActivity.this,"initialization failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void speak(View v){
        float pr, sr;
        if(pitchrate.getText().toString().equals("")){
            pr=1.0f;
        }
        else
        {
            pr=Float.parseFloat(pitchrate.getText().toString());
        }
        if(speechrate.getText().toString().equals(""))
        {
            sr=1.0f;
        }
        else
        {
            sr=Float.parseFloat(speechrate.getText().toString());
        }
        t.setPitch(pr);
        t.setSpeechRate(sr);
        t.speak(text.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
    }
}