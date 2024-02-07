package com.example.compesastudents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UploadImage extends AppCompatActivity {
    private ImageView updateStudentImage;
    private EditText regEnroll,regRoll,regAddress;
    private Button updateProfile;
    private final int REQ =1;
    private Bitmap bitmap = null;
    private String enroll,roll,address;
    private StorageReference storageReference;
    private DatabaseReference reference;
    private String downloadUrl,category,uniqueKey;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);


        pd = new ProgressDialog( this);

        SharedPreferences sP = getSharedPreferences("Mypref",MODE_PRIVATE);
        uniqueKey = sP.getString("uniquekey","no value");



        reference = FirebaseDatabase.getInstance().getReference().child("users");
        storageReference = FirebaseStorage.getInstance().getReference();

        updateStudentImage = findViewById(R.id.updateStudentImage);
        regEnroll= findViewById(R.id.regEnroll);
        regRoll = findViewById(R.id.regRollno);
        regAddress = findViewById(R.id.regAddress);
        updateProfile = findViewById(R.id.uploadProfile);




        updateStudentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enroll = regEnroll.getText().toString();
                roll = regRoll.getText().toString();
                address = regAddress.getText().toString();


                checkValidation();
            }
        });


    }

    private void checkValidation() {

        if(enroll.isEmpty())
        {
            regEnroll.setError("Empty");
            regEnroll.requestFocus();
        } else if (roll.isEmpty()) {
            regRoll.setError("Empty");
            regRoll.requestFocus();

        }
        else if (address.isEmpty()) {
            regAddress.setError("Empty");
            regAddress.requestFocus();

        }
        else if(bitmap == null)
        {
            Toast.makeText(this, "Please Select image", Toast.LENGTH_SHORT).show();
        }
        else{
            uploadImage();
        }

    }


    private void uploadImage() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Teachers").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UploadImage.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    downloadUrl = String.valueOf(uri);
                                    updateData(downloadUrl);

                                }
                            });
                        }
                    });
                }
                else{

                    Toast.makeText(UploadImage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void updateData(String s) {
        pd.setMessage("Uploading...");
        pd.show();

        HashMap hp = new HashMap();
        hp.put("enroll",enroll);
        hp.put("roll",roll);
        hp.put("address",address);
        hp.put("image",s);


        reference.child(uniqueKey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                pd.dismiss();
                Toast.makeText(UploadImage.this, "Your data updated successfully.", Toast.LENGTH_SHORT).show();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadImage.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK)
        {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateStudentImage.setImageBitmap(bitmap);
        }
    }
}