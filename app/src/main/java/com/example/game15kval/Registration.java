package com.example.game15kval;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registration extends AppCompatActivity {

    private Button btnReg, btnLog;
    private EditText editTxtUsername,editTxtEmail,editTxtPassword,editTxtPassword2;
    sqlDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnReg = findViewById(R.id.btnReg);
        btnLog = findViewById(R.id.btnLog);
        editTxtUsername=findViewById(R.id.editTxtUsername);
        editTxtEmail=findViewById(R.id.editTxtEmail);
        editTxtPassword=findViewById(R.id.editTxtPassword);
        editTxtPassword2=findViewById(R.id.editTxtPassword2);
        db = new sqlDatabase(this);


        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, logIn.class));
            }
        });


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = editTxtUsername.getText().toString();
                        String password = editTxtPassword.getText().toString();
                        String repassword = editTxtPassword2.getText().toString();
                        String email = editTxtEmail.getText().toString();

                        if(TextUtils.isEmpty(user)){
                            Toast.makeText(Registration.this, "Please, enter Username", Toast.LENGTH_SHORT).show();

                        }if(TextUtils.isEmpty(password)){
                            Toast.makeText(Registration.this, "Please, enter Password", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(password.equals(repassword)){
                                Boolean checkuser = db.checkUser(user);
                                if(checkuser==false){
                                    Boolean insert = db.insertData(user, email,password);
                                    if(checkuser==true){
                                        Toast.makeText(Registration.this, "Welcome"+user, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Registration.this, MainActivity.class));
                                    }

                                }
                            }
                        }

                    }
                });
            }
        });




    }



}