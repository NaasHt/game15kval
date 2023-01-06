package com.example.game15kval;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registration extends AppCompatActivity {

    private Button btnReg;
    private TextView btnLog;
    private EditText editTxtUsername, editTxtEmail, editTxtPassword, editTxtPassword2;
    sqlDatabase datb;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnReg = findViewById(R.id.btnReg);
        btnLog = findViewById(R.id.btnLog1);
        editTxtUsername = findViewById(R.id.editTxtUsername);
        editTxtPassword = findViewById(R.id.editTxtPassword);
        editTxtPassword2 = findViewById(R.id.editTxtPassword2);
        datb = new sqlDatabase(this);


        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}
//        btnReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnReg.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                    // Create a new user with a first and last name
////                        Map<String, Object> user = new HashMap<>();
////                        user.put("first", "Ada");
////                        user.put("last", "Lovelace");
////                        user.put("born", 1815);
////
////// Add a new document with a generated ID
////                        db.collection("users")
////                                .add(user)
////                                .addOnSuccessListener(new OnSuccessListener < DocumentReference > () {
////                            @Override
////                            public void onSuccess (DocumentReference documentReference){
////                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
////                            }
////                        })
////                                .addOnFailureListener(new OnFailureListener() {
////                            @Override
////                            public void onFailure(@NonNull Exception e) {
////                                Log.w(TAG, "Error adding document", e);
////                            }
////                        });
////                    }
////                });
//                }
//            }
//        }
//    }



