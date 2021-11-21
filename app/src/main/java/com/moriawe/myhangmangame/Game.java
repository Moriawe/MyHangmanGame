package com.moriawe.myhangmangame;

import android.util.Log;
import java.util.ArrayList;
import java.util.Random;



public class Game {

    static Random rand = new Random();

    public ArrayList<String> listOfWords;
    public ArrayList<Character> guessedCharacters = new ArrayList<>();

    private String gameMode;
    private String chosenWord;
    private String displayWord;

    private int timesFailed;
    private static final int MAXFAILS = 8;

    String textFile;


    public Game(String gameMode) {
        this.gameMode = gameMode;
    }


    // [1] Runs to set up a list of words appropriate to the games ChallengeLevel
    public void makeUpWords() {
        listOfWords = new ArrayList<String>();
        listOfWords.add("Doll");
        listOfWords.add("Yellow");
        listOfWords.add("Camel");
        listOfWords.add("Friend");
        listOfWords.add("Pony");
        listOfWords.add("Apple");
        listOfWords.add("Castle");
        listOfWords.add("Boat");
        listOfWords.add("Lunch");
        listOfWords.add("Honey");
        listOfWords.add("Toy");

    }

    public void challengeLevel() {

        switch(gameMode) {
            case "easy":
                textFile = "easy.txt";
                break;
            case "hard":
                textFile = "hard.txt";
                break;
            default:
                textFile = "easy.txt"; //TODO an if in case textfile isn't given a value
                break;
        }
        //readFile(textFile);
    }





    // [2] Randomly chooses a word from the list
    public void choseWord(){

        if (!listOfWords.isEmpty()) {

            //gets a word and turns it to UPPERCASE
            chosenWord = listOfWords.get(rand.nextInt(listOfWords.size())).toUpperCase();

            hideTheDisplayWord();

            Log.w("choseWord", "Successfully picked a name from the arraylist");

        } else {
            Log.w("choseWord", "Couldn't get a word from Array");
        }

    }


    // [3] Takes the correct word and turns it into "_" for every char in it
    public void hideTheDisplayWord() {

        displayWord = "";

        for (int i=0; i < chosenWord.length(); i++ ) {
            displayWord += "_";
        }

    }


    // Checks if the guessed char is in the word
    public boolean checkIfGuessIsCorrect(char guess) {

        boolean guessWasRight = false;
        guessedCharacters.add(guess);

            for (int i = 0; i < chosenWord.length(); i++) {
                if (guess == chosenWord.charAt(i)) {
                    displayWord = replaceCharUsingCharArray(displayWord, chosenWord.charAt(i), i);
                    guessWasRight = true;
                }
            }

    return guessWasRight;

    }


    // Replaces the "_" in the String with the correct char
    public String replaceCharUsingCharArray(String str, char ch, int index) {
        char[] chars = str.toCharArray();
        chars[index] = ch;
        return String.valueOf(chars);
    }


    public boolean haveITriedThisOneBefore(char guess) {

        boolean yesYouHave = false;

        for (int i = 0; i < guessedCharacters.size(); i++) {

            if (guess == guessedCharacters.get(i)) {
                yesYouHave = true;
            }
        }
        return yesYouHave;
    }

    // Checks to see if the user have the full word
    public boolean didTheUserWin() {

        if (chosenWord.equals(displayWord)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean didTheUserLoose() {

        if (timesFailed == MAXFAILS) {
            return true;
        } else {
            return false;
        }
    }


    public String getChosenWord() {
        return chosenWord;
    }

    public String getDisplayWord() {
        return displayWord;
    }

    public int getTimesFailed() {
        return timesFailed;
    }

    public void setTimesFailed(int timesFailed) {
        this.timesFailed = timesFailed;
    }

    public int getMAXFAILS() { return MAXFAILS; }

    // Me trying to read a file into an Array

    /*
    public void readFile(String path) {

        listOfWords = new ArrayList<String>();

        AssetManager am = GameActivity.getAssets();

        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
                listOfWords.add(line);
        } catch (IOException e) {
                e.printStackTrace();
        }

    } */



    /*
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(textFile), "string"));

            //do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) !=null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } */

    /*
        Scanner sc = new Scanner(new File(textFile));
        while (sc.hasNextLine())
            listOfWords.add(sc.nextLine());
    */
    /*
        try (BufferedReader bufReader = new BufferedReader(new FileReader(textFile))) {

            String line = bufReader.readLine();
            while (line != null) {
                listOfWords.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } */


}
