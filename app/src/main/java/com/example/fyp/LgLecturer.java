package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LgLecturer extends AppCompatActivity {
   private Button btt1;
   private Button btt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lg_lecturer);

        btt1 = (Button) findViewById(R.id.btt1);
        btt2 = (Button) findViewById(R.id.btt2);

        btt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LgLecturer.this,SuLecturer.class);
                startActivity(intent1);
            }
        });
        btt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LgLecturer.this,ScheduleLecturer.class);
                startActivity(intent1);
            }
        });

    }
}
