package com.example.fyp;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuStudent extends AppCompatActivity {
private Button Continue_button;
private TextView Click;
private EditText Email, Password, Confirm_Password, Name;
private ProgressDialog loadingBar;

private Students students;

private FirebaseAuth mAuth;
private DatabaseReference reff;

private long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_student);

        //initialize firebase
        mAuth = FirebaseAuth.getInstance();

        reff = FirebaseDatabase.getInstance().getReference().child("Students");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        students = new Students();


        Continue_button = (Button)findViewById(R.id.ssu_continue_button);
        Click = (TextView) findViewById(R.id.ssu_click);
        Name = (EditText)findViewById(R.id.ssu_Name);
        Email = (EditText)findViewById(R.id.ssu_email);
        Password = (EditText)findViewById(R.id.ssu_password);
        Confirm_Password = (EditText)findViewById(R.id.ssu_confirm_password);
        loadingBar = new ProgressDialog(this);

        Continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students.setName(Name.getText().toString().trim());
                students.setEmail(Email.getText().toString().trim());
                reff.child(String.valueOf(maxid+1)).setValue(students);
                Toast.makeText(SuStudent.this,"Data inserted successfully",Toast.LENGTH_LONG).show();
                CreateNewAccount();
            }
        });

        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int7 = new Intent(SuStudent.this,LgStudent.class);
                startActivity(int7);
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
        } else if (password.length() < 6){
            Toast.makeText(this,"Password must be 6 or more digits",Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(confirmPassword) ) {
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

                                Toast.makeText(SuStudent.this, "Account is created successfully!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(SuStudent.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
    }
}

    private void SendUserToScheduleLecturer() {
        Intent setupIntent = new Intent(SuStudent.this,ScheduleStudent .class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
    }}
