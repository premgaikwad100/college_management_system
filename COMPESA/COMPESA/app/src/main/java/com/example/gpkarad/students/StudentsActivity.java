package com.example.gpkarad.students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gpkarad.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {
    private RecyclerView allStudents;
    private LinearLayout noData;
    private List<StudentsData> list1;
    private StudentsAdapter adapter;
    private DatabaseReference reference,dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        allStudents = findViewById(R.id.allStudents);
        noData = findViewById(R.id.noData);
        reference = FirebaseDatabase.getInstance().getReference().child("users");


        showData();
    }

    private void showData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();

                if(!snapshot.exists())
                {
                    noData.setVisibility(View.VISIBLE);
                    allStudents.setVisibility(View.GONE);
                }
                else{
                    noData.setVisibility(View.GONE);
                    allStudents.setVisibility(View.VISIBLE);
                    for(DataSnapshot datasnapshot :snapshot.getChildren())
                    {
                        StudentsData data =datasnapshot.getValue(StudentsData.class);
                        list1.add(data);
                    }
                    allStudents.setHasFixedSize(true);
                    allStudents.setLayoutManager(new LinearLayoutManager(StudentsActivity.this));
                    adapter = new StudentsAdapter(list1,StudentsActivity.this);
                    allStudents.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}