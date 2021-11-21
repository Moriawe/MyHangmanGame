package com.moriawe.myhangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {

    private ImageView screenImage;
    private EditText guessText;
    private ImageButton playButton;
    private TextView playedLetters;
    private TextView gameField;

    //private TextView chosenWordView;

    Game newGame;
    private String gameMode;
    private String allLettersPlayed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        screenImage = (ImageView) findViewById(R.id.hangman_screen);
        guessText = (EditText) findViewById(R.id.guess_edit_text);
        playButton = (ImageButton) findViewById(R.id.button_play);
        playedLetters = (TextView) findViewById(R.id.view_of_played_letters);
        gameField = (TextView) findViewById(R.id.view_of_the_word);

        //chosenWordView = (TextView) findViewById(R.id.view_of_chosen_word); [For testing purposes only]

        // initiate a new game
        initGame();
        makeScreenReady();


    }


    // [1] Creates a new instance of Game and assigns it the correct gameMode (easy, medium, hard)
    private void initGame() {

        Intent intent = getIntent();
        gameMode = intent.getStringExtra("gamemode");
        newGame = new Game(gameMode);


        newGame.makeUpWords(); //TODO Hardcoded words for the arraylist until I get filereader to work
        //newGame.challengeLevel(); //TODO Sets upp challengelevel and choses the right textfile.
        //getRandomWord(); //TODO Funkar inte heller :(

        newGame.choseWord();

    }

    // [2] Makes the screen show the right text in the views after every guess
    private void makeScreenReady() {

        // The hidden word is picked apart into char and written out with a space in between every char.
        String showWord = "";

        char[] displayWordCharacters = newGame.getDisplayWord().toCharArray();
        for (int i = 0; i < displayWordCharacters.length; i++) {
            showWord += displayWordCharacters[i] + " ";
        }
        gameField.setText(showWord);

        playedLetters.setText(allLettersPlayed);

        guessText.setText("");

        changePicture();

        //chosenWordView.setText(newGame.getChosenWord());

    }


    // [3] Checks so that the user types in a letter and that it is only ONE
    public void checkLetter(View view) {

        closeKeyboard();

        String guess = guessText.getText().toString();
        guess = guess.toUpperCase();

        // checks so there is only ONE letter
        if (guess.length() != 1) {
            guessText.setError("Try again!");
            makeAToast("Type in ONE letter");
        } else {

            char letterUserGuessed = (guess.charAt(0));

            // checks if the user have guessed this letter before
            if (newGame.haveITriedThisOneBefore(letterUserGuessed)) {
                guessText.setError("Try again!");
                makeAToast("You have already tried this letter");
            } else {
                guessText.setError(null);

                    // checks if the letter is used in the word
                    if (newGame.checkIfGuessIsCorrect(letterUserGuessed)) {
                        makeAToast("Yay! You guessed right!");
                    } else {
                        makeAToast("Oh no!");
                        newGame.setTimesFailed(newGame.getTimesFailed()+1);
                        allLettersPlayed += (letterUserGuessed + ", ");
                    }
                makeScreenReady();
                winOrLoose();
            }
        }

    }


    // Checks if the user wins or loose and displays the correct dialoginfo
    private void winOrLoose() {

        String message;
        String results;

        if (newGame.didTheUserWin()) {

                message = "You won! Congratulations!";
                results = "The word was " + newGame.getChosenWord() +
                        "\n + You had " + (newGame.getMAXFAILS() - newGame.getTimesFailed()) + " tries left.";
                GameOverDialog dialog = new GameOverDialog(message, results);
                dialog.show(getSupportFragmentManager(), "Game Over Dialog Call");

        } else if (newGame.didTheUserLoose()) {

            message = "You lost!";
            results = "The word was " + newGame.getChosenWord() +
                    "\n + You had " + (newGame.getMAXFAILS() - newGame.getTimesFailed()) + " tries left.";
            GameOverDialog dialog = new GameOverDialog(message, results);
            dialog.show(getSupportFragmentManager(), "Game Over Dialog Call");
        }
    }

    // Changes the imageView everytime the user guesses wrong.
    private void changePicture() {

        // filename
        String picture = "@drawable/pic";

        //filename changes for every fail
        picture += (newGame.getTimesFailed() +1);

        //make filenamn into resourceInt
        int imageResource = getResources().getIdentifier(picture, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);

        //set new image
        screenImage.setImageDrawable(res);

    }


    // Closes the keyboard evertime you press the "?" Button
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    //Do a toast notification
    public void makeAToast(String message) {

        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }


    // Tobbe hjälpte mig hitta en metod som korrekt kunde läsa in en txt fil.

    // Reads the correct txt file for the challengelevel and turns its words into an arraylist of words.
    public void getRandomWord() {

        InputStream myInputStream = null;
        Scanner in = null;

        String aWord;
        try {
            myInputStream = getAssets().open("easy.txt");
            in = new Scanner(myInputStream);
            while (in.hasNext()) {
                aWord = in.next();
                newGame.listOfWords.add(aWord);
            }
        } catch (IOException e) {
            Toast.makeText(GameActivity.this, e.getClass().getSimpleName() + ": " + e.getMessage() , Toast.LENGTH_SHORT).show();
            // e.printStackTrace();
        } finally {
            //close scanner
            if(in != null) {
                in.close();
            }
            //close inputstream
            try {
                if(myInputStream != null) {
                    myInputStream.close();
                }
            } catch (IOException e) {
                Toast.makeText(GameActivity.this, e.getClass().getSimpleName() + ": " + e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        }
    }


}