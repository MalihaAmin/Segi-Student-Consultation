package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LgStudent extends AppCompatActivity {
private Button btt3;
private Button btt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lg_student);
        btt3 = (Button)findViewById(R.id.btt3);
        btt4 = (Button)findViewById(R.id.btt4);

       btt3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent1 = new Intent(LgStudent.this,SuStudent.class);
               startActivity(intent1);
           }
       });
        btt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection con= ConnectionDB.getConnection();
                PreparedStatement ps;
                ResultSet rs;
                PreparedStatement ps2;
                ResultSet rs2;

                final EditText email =  (EditText) findViewById(R.id.et3);
                final EditText password =  (EditText) findViewById(R.id.et4);
                ////edit.getText().toString();

                try {
                    ps = con.prepareStatement("SELECT * FROM `student` WHERE `Email` = ? AND `Password` = ?");
                    //ps2 = con.prepareStatement("SELECT * FROM `teacher` WHERE `Teacher ID` = ? AND `Password` = ?");

                    ps.setString(1, email.getText().toString() );
                    ps.setString(2, password.getText().toString());
                    //ps2.setString(1, jTextField1.getText() );
                    //ps2.setString(2, String.valueOf(jPasswordField1.getPassword()));
                    rs = ps.executeQuery();
                   // rs2=ps2.executeQuery();



                    if(rs.next()){

                        Intent intent2 = new Intent(LgStudent.this,ScheduleStudent.class);
                        startActivity(intent2);




                    }else{


                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LgStudent.class.getName()).log(Level.SEVERE, null, ex);

                }


            }
        });

    }
}
