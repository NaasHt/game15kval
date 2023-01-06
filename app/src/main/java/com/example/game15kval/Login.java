package com.example.game15kval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText editTxtUser,editTxtPsword;
    View btnForgot,btnRegistration;
    Button btnLogin;
    //sqlDatabase dblogin;
    FirebaseDatabase db;
    DatabaseReference userRef;
    String userName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTxtUser = findViewById(R.id.editTxtUser);
        editTxtPsword = findViewById(R.id.editTxtPsword);
        btnLogin = findViewById(R.id.btnLogin2);
        btnRegistration = findViewById(R.id.btnRegistration);
        db=FirebaseDatabase.getInstance();
        userRef = db.getReference("message");
        userRef.setValue("Hello World!");




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREF", 0);
//                editTxtUser = preferences.getString("userName", "");
//                if(!editTxtUser.equals("")){
//
//                }
//                String user = editTxtUser.getText().toString();
//                String password = editTxtPsword.getText().toString();
//                String email = editTxtUser.getText().toString();
//
//                if(TextUtils.isEmpty(user)){
//                    Toast.makeText(Login.this, "Please enter your Username", Toast.LENGTH_SHORT).show();
//
//                }
//                else if(TextUtils.isEmpty(password)){
//                    Toast.makeText(Login.this, "Please enter Your Password", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    boolean checkUser = dblogin.checkPassword(user,  password);
//                    if(checkUser == true){
//                        Toast.makeText(Login.this, "Welcome, "+user, Toast.LENGTH_SHORT).show();
//                        Intent intetn = new Intent(Login.this, MainActivity.class);
//                        startActivity(intetn);
//                    }
//
//                    else{
//                        Toast.makeText(Login.this, "Sorry, login failed.", Toast.LENGTH_SHORT).show();
//                    }
//                }

            }
        });

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

    }
}