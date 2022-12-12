package com.example.game15kval;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlDatabase extends  SQLiteOpenHelper{

     public static final String DBNAME = "registrate.db";
    public sqlDatabase(Context context) {
        super(context, "registrate.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, email TEXT,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }
    public boolean insertData(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);
        values.put("email", email);



        long result = db.insert("users", null, values);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkMail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and email = ?", new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean checkPassword(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password= ?", new String[] {password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }


}





