package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuLecturer extends AppCompatActivity {
private Button button4;
private Button button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_lecturer);
        button4 = (Button)findViewById(R.id.btt3);
        button5 = (Button)findViewById(R.id.btt4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(SuLecturer.this,LgLecturer.class);
                startActivity(int4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(SuLecturer.this,ScheduleLecturer.class);
                startActivity(int5);
            }
        });
    }
}
