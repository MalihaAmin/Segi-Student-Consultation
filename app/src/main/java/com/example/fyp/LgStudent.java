package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class LgStudent extends AppCompatActivity {
private Button Continue;
private Button SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lg_student);
        Continue = (Button)findViewById(R.id.slg_continue_button);
        SignIn = (Button)findViewById(R.id.slg_SignIn);

       Continue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent1 = new Intent(LgStudent.this,SuStudent.class);
               startActivity(intent1);
           }
       });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LgStudent.this,ScheduleLecturer.class);
                startActivity(intent1);
            }
        });

    }
}
