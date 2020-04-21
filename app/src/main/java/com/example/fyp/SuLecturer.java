package com.example.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SuLecturer extends AppCompatActivity {
private Button Continue_button;
private Button Login_button;
private EditText Email, Password, Confirm_Password;
private ProgressDialog loadingBar;

private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_lecturer);

//initialize firebase
        mAuth = FirebaseAuth.getInstance();

        Continue_button = (Button)findViewById(R.id.lsu_continue_button);
        Login_button = (Button)findViewById(R.id.lsu_login_button);
        Email = (EditText)findViewById(R.id.lsu_email);
        Password = (EditText)findViewById(R.id.lsu_password);
        Confirm_Password = (EditText)findViewById(R.id.lsu_confirm_password);
        loadingBar = new ProgressDialog(this);


        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(SuLecturer.this,LgLecturer.class);
                startActivity(int4);
            }
        });
        Continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent int5 = new Intent(SuLecturer.this,ScheduleLecturer.class);
                //startActivity(int5);
                CreateNewAccount();
            }
        });
    }
    private void CreateNewAccount() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String confirmPassword = Confirm_Password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword) ) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Wait! Creating New Account");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SendUserToScheduleLecturer();

                                Toast.makeText(SuLecturer.this, "Account is created successfully!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(SuLecturer.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });

        }


    }

    private void SendUserToScheduleLecturer() {
        Intent setupIntent = new Intent(SuLecturer.this,ScheduleLecturer .class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
    }
}
