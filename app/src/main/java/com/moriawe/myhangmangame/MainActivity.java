package com.moriawe.myhangmangame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Actionbar
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        ImageView goToHomeScreen = findViewById(R.id.home_icon);
        ImageView backToGame = toolbar.findViewById(R.id.back_icon);
        ImageView infoAboutApp = toolbar.findViewById(R.id.info_icon);
        goToHomeScreen.setVisibility(View.INVISIBLE);
        backToGame.setVisibility(View.INVISIBLE);
        infoAboutApp.setVisibility(View.VISIBLE);

        infoAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        // Activityview
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