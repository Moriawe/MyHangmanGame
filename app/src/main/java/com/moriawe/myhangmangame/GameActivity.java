package com.moriawe.myhangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class GameActivity extends AppCompatActivity {

    private ImageView screenImage;
    private EditText guessText;
    private ImageButton playButton;
    private TextView guessedWords;
    private TextView gameField;

    Game newGame;
    private String gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        screenImage = (ImageView) findViewById(R.id.hangman_screen);
        guessText = (EditText) findViewById(R.id.guess_edit_text);
        playButton = (ImageButton) findViewById(R.id.button_play);
        guessedWords = (TextView) findViewById(R.id.view_of_played_letters);
        gameField = (TextView) findViewById(R.id.view_of_the_word);

        // initiate a new game
        initGame();
        makeScreenReady();


    }


    // creates a new instance of Game and assigns it the correct gameMode (easy, medium, hard)
    private void initGame() {

        Intent intent = getIntent();
        gameMode = intent.getStringExtra("gamemode");
        newGame = new Game(gameMode);

        try {
            newGame.challengeLevel(); //TODO Can Game initiate these by itself instead?
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        newGame.choseWord();

    }


    private void makeScreenReady() {

        gameField.setText(newGame.getHiddenWord());

        guessedWords.setText(newGame.getChosenWord());

    }


    //Do a toast notification
    public void makeAToast(Context context, String message) {

        Toast toast = Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT);

        toast.show();
    }


}