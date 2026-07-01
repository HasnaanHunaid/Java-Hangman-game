package hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanGame {
    private List<String> wordList;

    public HangmanGame() {
        wordList = new ArrayList<>();
        String filePath = "C:\\Users\\hasna\\git\\nouns.txt";
    }

    private void readWordListFromFile(String filePath) {
    	try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    	    String line;
    	    while ((line = reader.readLine()) != null) {
    	        wordList.add(line.trim());
    	    }
    	    if (wordList.isEmpty()) {
    	        throw new IllegalStateException("Word list is empty");
    	    }
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}

    }

    private String selectRandomWord() {
        if (wordList.isEmpty()) {
            throw new IllegalStateException("Word list is empty");
        }
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    public static void main(String[] args) {
        HangmanGame hangmanGame = new HangmanGame();
        String randomWord = hangmanGame.selectRandomWord();
        System.out.println("Random word: " + randomWord);
    }
}
