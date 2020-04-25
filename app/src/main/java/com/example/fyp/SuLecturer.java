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


    public class SuLecturer extends AppCompatActivity {
        private Button Continue;
        private EditText Name, Email, Password, Confirm_Password;
        private TextView Click;
        private ProgressDialog loadingbar;

        private Lecturers lecturers;
        private TimeTable ts;
        private FirebaseAuth mAuth;
        private DatabaseReference reff;
        private DatabaseReference reff_tt;

        private long maxid = 0;
        private long maxid_tt=0;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_su_lecturer);

            //initialize firebase
            mAuth = FirebaseAuth.getInstance();
            reff = FirebaseDatabase.getInstance().getReference().child("Lecturers");
            reff_tt= FirebaseDatabase.getInstance().getReference().child("TimeTable");

            reff_tt.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                        maxid_tt = dataSnapshot.getChildrenCount();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

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

            lecturers = new Lecturers();
            Continue = (Button)findViewById(R.id.lsu_continue_button);
            Click = (TextView) findViewById(R.id.lsu_click);
            Name = (EditText)findViewById(R.id.lsu_Name);
            Email = (EditText)findViewById(R.id.lsu_email);
            Password = (EditText)findViewById(R.id.lsu_password);
            Confirm_Password = (EditText)findViewById(R.id.lsu_confirm_password);
            loadingbar = new  ProgressDialog(this);

            Continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lecturers.setName(Name.getText().toString().trim());
                    lecturers.setEmail(Email.getText().toString().trim());
                    reff.child(String.valueOf(maxid+1)).setValue(lecturers);


                    Toast.makeText(SuLecturer.this,"Data inserted successfully",Toast.LENGTH_LONG).show();
                    CreateNewAccount();

                    //Create Sunday Table
                    ts=new TimeTable();
                    ts.setLec_email(Email.getText().toString().trim());
                    ts.setWeekday("Sun");
                    reff_tt.child(String.valueOf(maxid_tt+1)).setValue(ts);
                    ts.setWeekday("Mon");
                    reff_tt.child(String.valueOf(maxid_tt+2)).setValue(ts);
                    ts.setWeekday("Tue");
                    reff_tt.child(String.valueOf(maxid_tt+3)).setValue(ts);
                    ts.setWeekday("Wed");
                    reff_tt.child(String.valueOf(maxid_tt+4)).setValue(ts);
                    ts.setWeekday("Thu");
                    reff_tt.child(String.valueOf(maxid_tt+5)).setValue(ts);
                    ts.setWeekday("Fri");
                    reff_tt.child(String.valueOf(maxid_tt+6)).setValue(ts);

                }
            });



            Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent int7 = new Intent(SuLecturer.this,LgStudent.class);
                    startActivity(int7);
                }
            });
    }
        private void CreateTimeTable(){


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
                loadingbar.setTitle("Creating New Account");
                loadingbar.setMessage("Wait! Creating New Account");
                loadingbar.show();
                loadingbar.setCanceledOnTouchOutside(true);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    SendUserToScheduleLecturer();

                                    Toast.makeText(SuLecturer.this, "Account is created successfully!", Toast.LENGTH_SHORT).show();
                                    loadingbar.dismiss();
                                } else {
                                    String message = task.getException().getMessage();
                                    Toast.makeText(SuLecturer.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                                    loadingbar.dismiss();
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
        }}
