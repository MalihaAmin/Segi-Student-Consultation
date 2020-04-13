package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
private Button button2;
private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
      button2 = (Button)findViewById(R.id.button2);
      button3 = (Button)findViewById(R.id.button3);

      button2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent int1 = new Intent(Choose.this,SuLecturer.class);
          startActivity(int1);
          }
      });
      button3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent int2 = new Intent(Choose.this,SuStudent.class);
              startActivity(int2);
          }
      });
    }

}
