package com.mdskrumi.howsmartareyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView numberOne,
            numberTwo,
            signTV;
    private EditText resultET;
    private Button submitButton;

    private String key , resultString;

    private Random rand = new Random();

    private int signs[] = {0 , 1 , 2 , 3 , 4 , 5 , 6 ,7 , 8 , 9};
    /*
    * 0 For +
    * 1 For -
    * 2 For *
    * 3 For /
    * 4 For %
    * 5 For +
    * 6 For *
    * 7 For -
    * 8 For +
    * 9 For /
    * */

    private int aNum , bNum , cSign , result , userResult , score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = getIntent().getStringExtra("Key");
        if(!key.equals("xYz")){
            onBackPressed();
        }

        numberOne = findViewById(R.id.numberOneTV);
        numberTwo = findViewById(R.id.numberTwoTV);
        signTV = findViewById(R.id.signTV);
        submitButton = findViewById(R.id.submitButton);
        resultET = findViewById(R.id.resultET);
        score = 0;

        reset();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
            }
        });
        resultET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    playGame();
                    return true;
                }
                return false;
            }
        });

    }

    private void getNumbers(int range){
        aNum = rand.nextInt(range) + 1;
        bNum = rand.nextInt(range) + 1;
    }
    private void adjust(){
        int temp = Math.max(aNum,bNum);
        bNum = Math.min(aNum,bNum);
        aNum = temp;
    }
    private void playGame(){
        resultString = resultET.getText().toString();
        if(resultString.isEmpty()){
            resultET.setError("Please Enter Something !!!");
            return;
        }
        userResult = Integer.parseInt(resultString);

        if(userResult == result){
            Toast.makeText(MainActivity.this , "Good Job" , Toast.LENGTH_SHORT).show();
            score++;
            reset();
        }else{
            Toast.makeText(MainActivity.this , "Sorry, Your Scored: " + score  , Toast.LENGTH_SHORT).show();
            score = 0;
            //startActivity(new Intent(MainActivity.this , HomeActivity.class));
        }
        resultET.setText("");
    }
    private void reset(){
        cSign = rand.nextInt(10);

        if(cSign == 0){
            getNumbers(200);
            adjust();
            result = aNum + bNum;
            signTV.setText("+");
        }
        else if(cSign == 1){
            getNumbers(200);
            adjust();
            result = aNum - bNum;
            signTV.setText("-");
        }
        else if(cSign == 2){
            getNumbers(20);
            adjust();
            result = aNum * bNum;
            signTV.setText("*");
        }
        else if(cSign == 3){
            getNumbers(200);
            adjust();
            result = aNum / bNum;
            signTV.setText("/");
        }
        else if(cSign == 4){
            getNumbers(200);
            adjust();
            result = aNum % bNum;
            signTV.setText("%");
        }
        else if(cSign == 5){
            getNumbers(200);
            adjust();
            result = aNum + bNum;
            signTV.setText("+");
        }
        else if(cSign == 6){
            getNumbers(20);
            adjust();
            result = aNum * bNum;
            signTV.setText("*");
        }
        else if(cSign == 7){
            getNumbers(200);
            adjust();
            result = aNum - bNum;
            signTV.setText("-");
        }
        else if(cSign == 8){
            getNumbers(200);
            adjust();
            result = aNum + bNum;
            signTV.setText("+");
        }
        else if(cSign == 9){
            getNumbers(200);
            adjust();
            result = aNum / bNum;
            signTV.setText("/");
        }

        numberOne.setText(String.valueOf(aNum));
        numberTwo.setText(String.valueOf(bNum));

    }
}
