package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LgLecturer extends AppCompatActivity {
   private Button Continue;
   private Button SignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lg_lecturer);

        Continue = (Button) findViewById(R.id.llg_continue_button);
        SignIn = (Button) findViewById(R.id.llg_SignIn);

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LgLecturer.this,SuLecturer.class);
                startActivity(intent1);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LgLecturer.this,ScheduleLecturer.class);
                startActivity(intent1);
            }
        });

    }
}
