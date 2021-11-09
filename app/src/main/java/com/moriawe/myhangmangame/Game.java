package com.moriawe.myhangmangame;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    static Random rand = new Random();

    private ArrayList<String> listOfWords;
    private String gameMode;
    private String chosenWord;
    private String hiddenWord;
    private static final int MAXFAILS = 10;

    public Game(String gameMode) {
        this.gameMode = gameMode;
    }


    public void challengeLevel() throws FileNotFoundException { //TODO throws exception? //

        String textFile = "easy.txt"; //TODO an if in case textfile isn't given a value
        listOfWords = new ArrayList<String>();

        if (gameMode.equals("easy")) {

            textFile = "easy.txt";

        } else if (gameMode.equals("medium")) {

            textFile = "medium.txt";

        } else if (gameMode.equals("hard")) {

            textFile = "hard.txt";

        }

        try (BufferedReader bufReader = new BufferedReader(new FileReader(textFile))) {

            String line = bufReader.readLine();
            while (line != null) {
                listOfWords.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void choseWord(){

        if (!listOfWords.isEmpty()) {

            //Selects which of the words we should get

            chosenWord = listOfWords.get(rand.nextInt(listOfWords.size()));

            //int wordNumber = randomInt(0, (listOfWords.size()-1));
            //chosenWord = listOfWords.get(wordNumber);

            hideWord();

            Log.w("choseWord", "Successfully picked a name from the arraylist");

        } else {
            Log.w("choseWord", "Couldn't get a word from Array");
        }

    }

    public void hideWord() {

        for (int i=0; i < chosenWord.length(); i++ ) {
            hiddenWord += " _ ";
        }

    }

    /*
    public static int randomInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    } */



    public ArrayList getListOfWords() {
        return listOfWords;
    }

    public void setListOfWords(ArrayList listOfWords) {
        this.listOfWords = listOfWords;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getChosenWord() {
        return chosenWord;
    }

    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }
}
