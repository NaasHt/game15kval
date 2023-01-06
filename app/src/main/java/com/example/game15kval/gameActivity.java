package com.example.game15kval;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class gameActivity extends AppCompatActivity {
    private int emptyX = 3;
    private int emptyY = 3;
    private RelativeLayout group;
    private Button[] [] buttons;
    private TextView txtStep,txtTime;
    private Timer timer;
    private int [] tiles;
    private int steps = 0;
    private int timerCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        loadViews();
        loadNumbers();
        generatePuzzle();
        loadDataToView();
    }

    private void loadDataToView(){
        emptyX = 3;
        emptyY = 3;
        for(int i=0; i<group.getChildCount() -1; i++){
            buttons[i/4][i%4].setText(String.valueOf(tiles[i]));
            buttons[i/4][i%4].setBackgroundResource(android.R.drawable.btn_default);
        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
    }

    private  void generatePuzzle() {
        int n = 15;
        Random random = new Random();
        while (n > 1) {
            int randomNum = random.nextInt(n--);
            int temp = tiles[randomNum];
            tiles[randomNum] = tiles[n];
            tiles[n] = temp;
        }
        if (!isSolvable())
            generatePuzzle();
    }

    private boolean isSolvable(){
        int countInversions = 0;
        for(int i=0; i<15; i++){
            for(int j=0; j<i; j++){
                if(tiles[j]>tiles[i])
                    countInversions++;

            }

        }
        return countInversions%2 ==0;
    }

    private void loadNumbers(){
        tiles = new int[16];
        for (int i =0; i<group.getChildCount() -1; i++){

            tiles[i] = i+1;
        }
    }

    private void loadTime(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerCount++;
                setTime(timerCount);

            }
        }, 1000, 100);
    }
    private void setTime(int timerCount){
        int sec = timerCount %60;
        int hour = timerCount/3600;
        int minute = (timerCount - hour *3600)/60;

        txtTime.setText(String.format("Time: %02d:%02d:%02d", hour, minute, sec));


    }

    private void loadViews(){
        group = findViewById(R.id.group);
        txtStep = findViewById(R.id.txtStep);
        txtTime=findViewById(R.id.txtTime);
        loadTime();
        buttons = new Button[4][4];

        for( int i =0; i< group.getChildCount(); i++){
            buttons[i/4][i%4] = (Button) group.getChildAt(i);
        }
    }


    public void buttononClick(View view){ //moving while clicking
        Button button = (Button) view;
        int x= button.getTag().toString().charAt(0) - '0';
        int y= button.getTag().toString().charAt(1) - '0';
        if ((Math.abs(emptyX-x)==1&&emptyY==y)||(Math.abs(emptyY-y)==1&&emptyX==x)){
            buttons[emptyX][emptyY].setText(button.getText().toString());
            buttons[emptyX][emptyY].setBackgroundResource(android.R.drawable.btn_default);
            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
            emptyX = x;
            emptyY = y;
            steps++;
            txtStep.setText("Steps: " + steps);
            checkWin();
        }
    }
    private  void checkWin(){
        boolean isWin = false;
        if(emptyX==3&&emptyY==3){
            for (int i =0; i<group.getChildCount() - 1; i++){
                if (buttons[i/4][i%4].getText().toString().equals(String.valueOf(i+1))){
                    isWin=true;
                } else{
                    isWin=false;
                    break;
                }
            }
        }

        if(isWin){
            AlertDialog.Builder builder = new AlertDialog.Builder(gameActivity.this);
            builder.setTitle("You win")
                    .setMessage("Congratulations!\n\nYou made"+steps+" Steps")
                    .setCancelable(true);
            builder.create().show();
            for(int i =0; i< group.getChildCount(); i++){
                buttons[i/4][i%4].setClickable(false);
            }
            timer.cancel();
        }
    }
}