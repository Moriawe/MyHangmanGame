package com.moriawe.myhangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logo = (ImageView) findViewById(R.id.imageHangMan);
        TextView header = (TextView) findViewById(R.id.header);
        Button buttonEasy = (Button) findViewById(R.id.button_easy);
        //Button buttonMedium = (Button) findViewById(R.id.button_medium);
        Button buttonHard = (Button) findViewById(R.id.button_hard);

    }

    public void startEasyGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gamemode", "easy");
        startActivity(intent);
    }

    public void startMediumGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gamemode", "medium");
        startActivity(intent);
    }

    public void startHardGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gamemode", "hard");
        startActivity(intent);
    }
}