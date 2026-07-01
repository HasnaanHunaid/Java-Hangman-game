package hangman;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainWindow extends JFrame {
    private WordPanel wordPanel;
    private HealthPanel healthPanel;
    private ButtonPanel buttonPanel;
    private ArrayList<String> wordList;
    boolean gameOver;

    public MainWindow() {
        setTitle("Hangman Game");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gameOver = false;
        loadWordList(); // Load word list from file

        String randomWord = selectRandomWord();
        wordPanel = new WordPanel(randomWord);
        add(wordPanel, BorderLayout.NORTH);

        healthPanel = new HealthPanel(6); // Pass maximum lives
        add(healthPanel, BorderLayout.CENTER);

        buttonPanel = new ButtonPanel(wordPanel, healthPanel, this); // Pass MainWindow reference
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadWordList() {
        wordList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("nouns.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String selectRandomWord() {
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

    public void gameOver(boolean win) {
        gameOver = true;
        buttonPanel.disableButtons(); // Disable buttons on game over

        String message;
        if (win) {
            message = "Well Done! You guessed the word.";
        } else {
            message = "Try Again! The word was: " + wordPanel.getWordToGuess();
        }

        int response = JOptionPane.showConfirmDialog(this, message + "\nWould you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            dispose(); // Close the game window
        }
    }

    public void resetGame() {
        gameOver = false;
        healthPanel.resetLives(); // Reset health panel
        wordPanel.resetWord(selectRandomWord()); // Reset word panel
        buttonPanel.enableButtons(); // Enable buttons for new game
    }
}