package com.example.compesastudents.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.compesastudents.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {
    private RecyclerView ceDepartment,mechanicalDepartment,plasticDepartment,civilDepartment;
    private LinearLayout ceNoData,mechNoData,plasticNoData,civilNoData;
    private List<TeacherData> list1,list2,list3,list4;
    private TeacherAdapter adapter;
    private DatabaseReference reference, dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        ceNoData = view.findViewById(R.id.ceNoData);
        mechNoData = view.findViewById(R.id.mechNoData);
        plasticNoData = view.findViewById(R.id.plasticNoData);
        civilNoData = view.findViewById(R.id.civilNoData);
        ceDepartment = view.findViewById(R.id.ceDepartment);
        mechanicalDepartment = view.findViewById(R.id.mechanicalDepartment);
        plasticDepartment = view.findViewById(R.id.plasticDepartment);
        civilDepartment = view.findViewById(R.id.civilDepartment);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        ceDepartment();
        mechanicalDepartment();
        plasticDepartment();
        civilDepartment();


        return view;
    }

    private void ceDepartment() {
        dbRef = reference.child("Computer Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();

                if(!snapshot.exists())
                {
                    ceNoData.setVisibility(View.VISIBLE);
                    ceDepartment.setVisibility(View.GONE);
                }
                else{
                    ceNoData.setVisibility(View.GONE);
                    ceDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot datasnapshot :snapshot.getChildren())
                    {
                        TeacherData data = datasnapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    ceDepartment.setHasFixedSize(true);
                    ceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1,getContext());
                    ceDepartment.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void mechanicalDepartment() {
        dbRef = reference.child("Mechanical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();

                if(!snapshot.exists())
                {
                    mechNoData.setVisibility(View.VISIBLE);
                    mechanicalDepartment.setVisibility(View.GONE);
                }
                else{
                    mechNoData.setVisibility(View.GONE);
                    mechanicalDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot datasnapshot :snapshot.getChildren())
                    {
                        TeacherData data = datasnapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    mechanicalDepartment.setHasFixedSize(true);
                    mechanicalDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2,getContext());
                    mechanicalDepartment.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void plasticDepartment() {
        dbRef = reference.child("Plastic Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();

                if(!snapshot.exists())
                {
                    plasticNoData.setVisibility(View.VISIBLE);
                    plasticDepartment.setVisibility(View.GONE);
                }
                else{
                    plasticNoData.setVisibility(View.GONE);
                    plasticDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot datasnapshot :snapshot.getChildren())
                    {
                        TeacherData data = datasnapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    plasticDepartment.setHasFixedSize(true);
                    plasticDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3,getContext());
                    plasticDepartment.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void civilDepartment() {
        dbRef = reference.child("Civil Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();

                if(!snapshot.exists())
                {
                    civilNoData.setVisibility(View.VISIBLE);
                    civilDepartment.setVisibility(View.GONE);
                }
                else{
                    civilNoData.setVisibility(View.GONE);
                    civilDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot datasnapshot :snapshot.getChildren())
                    {
                        TeacherData data = datasnapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    civilDepartment.setHasFixedSize(true);
                    civilDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4,getContext());
                    civilDepartment.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}