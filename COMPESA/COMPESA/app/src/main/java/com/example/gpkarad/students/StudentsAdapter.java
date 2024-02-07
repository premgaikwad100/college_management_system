package com.example.gpkarad.students;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gpkarad.R;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewAdapter> {

    private List<StudentsData> list;
    private Context context;

    public StudentsAdapter(List<StudentsData> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public StudentsViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.students_item_layout, parent, false);


        return new StudentsViewAdapter(view);


    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewAdapter holder, int position) {

        final StudentsData item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.mobile.setText(item.getMobile());
        holder.year.setText(item.getYear());
        holder.enroll.setText("En :"+item.getEnroll());
        holder.roll.setText("Rn :"+item.getRoll());
        holder.address.setText("Ad :"+item.getAddress());
        try {
            Glide.with(context).load(item.getImage()).placeholder(R.drawable.man).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class StudentsViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, email, mobile, year,enroll,roll,address;

        private ImageView imageView;

        public StudentsViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentName);
            mobile = itemView.findViewById(R.id.studentPhone);
            email = itemView.findViewById(R.id.studentEmail);
            imageView = itemView.findViewById(R.id.studentsImage);
            year = itemView.findViewById(R.id.studentYear);
            enroll = itemView.findViewById(R.id.studentEnroll);
            roll = itemView.findViewById(R.id.studentRoll);
            address = itemView.findViewById(R.id.studentAddress);



        }
    }
}

