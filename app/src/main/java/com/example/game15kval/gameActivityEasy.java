package com.example.game15kval;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class gameActivityEasy extends AppCompatActivity {
    private int emptyX = 2;
    private int emptyY = 2;
    private RelativeLayout group;
    private Button[] [] buttons;
    private int [] tiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_easy);

        loadViews();
        loadNumbers();
        generateNumbers();
        loadDataToView();//mistake
    }

    private void loadDataToView(){
        emptyX = 2;
        emptyY = 2;
        for(int i=0; i<group.getChildCount() -1; i++){
            buttons[i/2][i%2].setText(String.valueOf(tiles[i]));
            buttons[i/2][i%2].setBackgroundResource(android.R.drawable.btn_default);
        }
        buttons[emptyX][emptyY].setText(""); //mistake
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
    }

    private  void generateNumbers() {
        int n = 4;
        Random random = new Random();
        while (n > 1) {
            int randomNum = random.nextInt(n--);
            int temp = tiles[randomNum];
            tiles[randomNum] = tiles[n];
            tiles[n] = temp;
        }
        if (!isSolvable())
            generateNumbers();
    }

    private boolean isSolvable(){
        int countInversions = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<i; j++){
                if(tiles[j]>tiles[i])
                    countInversions++;

            }

        }
        return countInversions%2 ==0;
    }

    private void loadNumbers(){
        tiles = new int[4];
        for (int i =0; i<group.getChildCount() -1; i++){

            tiles[i] = i+1;
        }
    }

    private void loadViews(){
        group = findViewById(R.id.group);
        buttons = new Button[2][2];

        for( int i =0; i< group.getChildCount(); i++){
            buttons[i/2][i%2] = (Button) group.getChildAt(i);
        }
    }
    public void buttononClick(View view){
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
            checkWin();
        }
    }
    private  void checkWin(){
        boolean isWin = false;
        if(emptyX==2&&emptyY==2){
            for (int i =0; i<group.getChildCount() - 1; i++){
                if (buttons[i/2][i%2].getText().toString().equals(String.valueOf(i+1))){
                    isWin=true;
                } else{
                    isWin=false;
                    break;
                }
            }
        }

        if(isWin){
            AlertDialog.Builder builder = new AlertDialog.Builder(gameActivityEasy.this);
            builder.setTitle("You win")
                    .setMessage("Congratulation!")
                    .setCancelable(true);
            builder.create().show();
            for(int i =0; i< group.getChildCount(); i++){
                buttons[i/2][i%2].setClickable(false);
            }
        }
    }
}