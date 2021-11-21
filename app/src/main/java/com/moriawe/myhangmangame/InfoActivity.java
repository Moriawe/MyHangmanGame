package com.moriawe.myhangmangame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView rules;
    private TextView madeBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        rules = findViewById(R.id.tv_rules);
        madeBy = findViewById(R.id.tv_made_by);

        // Actionbar
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        ImageView goToHomeScreen = findViewById(R.id.home_icon);
        ImageView backToGame = findViewById(R.id.back_icon);
        ImageView infoAboutApp = findViewById(R.id.info_icon);
        goToHomeScreen.setVisibility(View.VISIBLE);
        backToGame.setVisibility(View.VISIBLE);
        infoAboutApp.setVisibility(View.INVISIBLE);

        goToHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}