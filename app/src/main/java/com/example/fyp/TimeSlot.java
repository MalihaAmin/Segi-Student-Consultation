package com.example.fyp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class TimeSlot extends AppCompatActivity implements Interface.iFirebaseLoadDone {

    SearchableSpinner searchableSpinner;
    DatabaseReference reff;
    Interface.iFirebaseLoadDone iFirebaseLoadDone;
    List<Lecturers> lec= new ArrayList<>();
    boolean isFirstTimeClick=true;
    List<Lecturers> lecs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);
    searchableSpinner = (SearchableSpinner)findViewById(R.id.SSpinner);
        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Fixed frist time click
                if(!isFirstTimeClick){
                    Lecturers lec = lecs.get(i);
                    //Toast.makeText(this,lec.getEmail(),Toast.LENGTH_SHORT).show();
                    Toast.makeText(TimeSlot.this, lec.getEmail(), Toast.LENGTH_SHORT).show();
                }
                else
                {isFirstTimeClick=false;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    reff = FirebaseDatabase.getInstance().getReference("Lecturers");
    iFirebaseLoadDone= this;
    //GetData
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Lecturers> lec= new ArrayList<>();
                for(DataSnapshot lecSnapshot:dataSnapshot.getChildren()){
                    lec.add(lecSnapshot.getValue(Lecturers.class));
                }
                iFirebaseLoadDone.onFirebaseLoadSuccess(lec);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });

    }



    @Override
    public void onFirebaseLoadSuccess(List<Lecturers> lecturerList) {
        lecs= lecturerList;
        //Get all Name
        List<String> name_list= new ArrayList<>();
        for (Lecturers lec:lecturerList)
            name_list.add(lec.getName());
        //Create Adapter and set for Spinner
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,name_list);
        searchableSpinner.setAdapter(adapter);

    }

    @Override
    public void onFirebaseLoadFailed(String message) {

    }


}
