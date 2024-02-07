
package com.example.gpkarad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gpkarad.faculty.UpdateFaculty;
import com.example.gpkarad.notice.DeleteNoticeActivity;
import com.example.gpkarad.notice.UploadNotice;
import com.example.gpkarad.students.StudentsActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    CardView uploadNotice,addGalleryImage,addEbook,faculty,deleteNotice,showStudents;
    FirebaseAuth auth;
    Button signoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uploadNotice = findViewById(R.id.addNotice);
        deleteNotice = findViewById(R.id.deleteNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        showStudents = findViewById(R.id.showStudents);
        auth = FirebaseAuth.getInstance();
        signoutbutton = findViewById(R.id.signoutbutton);

        uploadNotice.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        faculty.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        showStudents.setOnClickListener(this);

        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                openLogin();
            }
        });


    }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() == null)
        {
            openLogin();
        }
    }



    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId())
        {

            case R.id.addNotice:
                intent = new Intent (MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                 intent = new Intent (MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent = new Intent (MainActivity.this,UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent = new Intent (MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent = new Intent (MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.showStudents:
                intent = new Intent (MainActivity.this, StudentsActivity.class);
                startActivity(intent);
                break;
        }
    }
}