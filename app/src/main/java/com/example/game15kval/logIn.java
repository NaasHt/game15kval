package com.example.game15kval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class logIn extends AppCompatActivity {

    EditText editTxtUser,editTxtPsword;
    Button btnLogin,btnRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTxtUser = findViewById(R.id.editTxtUser);
        editTxtPsword = findViewById(R.id.editTxtPsword);
        btnLogin = findViewById(R.id.btnLog);
        btnRegistration = findViewById(R.id.btnRegistration);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });




    }
}