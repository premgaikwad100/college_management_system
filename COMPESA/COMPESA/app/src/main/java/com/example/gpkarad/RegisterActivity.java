package com.example.gpkarad;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    private TextView openLog;
    String key;
    private EditText regName, regEmail, regPassword, regMobile;
    private Button register;
    private ProgressDialog pd;

    private String name, email, password, mobile;
    private FirebaseAuth auth,mAuth;
    private DatabaseReference reference;

    private DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        regName = findViewById(R.id.regname);
        regMobile = findViewById(R.id.regmob);
        regPassword = findViewById(R.id.regpass);
        regEmail = findViewById(R.id.regemail);
        openLog = findViewById(R.id.openLog);

        reference = FirebaseDatabase.getInstance().getReference();
        register = findViewById(R.id.elevatedButton);

        openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateData();
            }
        });


    }


    private void openLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openMain();
        }
    }

    private void openMain() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);
        finish();
    }

    private void validateData() {
        name = regName.getText().toString();
        email = regEmail.getText().toString();
        password = regPassword.getText().toString();
        mobile = regMobile.getText().toString();

        if (name.isEmpty()) {
            regName.setError("Required");
            regName.requestFocus();
        } else if (email.isEmpty()) {
            regEmail.setError("Required");
            regEmail.requestFocus();
        } else if (password.isEmpty()) {
            regPassword.setError("Required");
            regPassword.requestFocus();
        } else if (mobile.isEmpty()) {
            regMobile.setError("Required");
            regMobile.requestFocus();
        } else {
            createUser();
        }

    }

    private void createUser() {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            uploadData();

                        } else {
                            Toast.makeText(RegisterActivity.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(RegisterActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData() {

        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();

        FirebaseUser mUser = mAuth.getCurrentUser();

        String uid = mUser.getUid();

        dbref = reference.child("admin");
         key = dbref.push().getKey();
        SharedPreferences sP = getSharedPreferences("Mypref",MODE_PRIVATE);
        SharedPreferences.Editor ed = sP.edit();
        ed.putString("uniquekey",key);
        ed.apply();


        HashMap<String, String> user = new HashMap<>();
        user.put("key", key);
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);
        user.put("mobile",mobile);
        user.put("uid", uid);


        dbref.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            openMain();
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}