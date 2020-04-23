package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LgLecturer extends AppCompatActivity {
   private Button Continue;
   private TextView Click;
    private EditText Email, Password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lg_lecturer);

        Continue = (Button) findViewById(R.id.llg_continue_button);
        Click = (TextView)findViewById(R.id.llg_click) ;
        Email = (EditText)findViewById(R.id.slg_email);
        Password = (EditText)findViewById(R.id.slg_password);
        mAuth = FirebaseAuth.getInstance();

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    Password.setError("Password Must be >= 6 Characters");
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LgLecturer.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ScheduleStudent.class));
                        } else {
                            Toast.makeText(LgLecturer.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
            }
        });
            }
        });
        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LgLecturer.this,SuLecturer.class);
                startActivity(intent1);
            }
        });

    }
}
