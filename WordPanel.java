package hangman;
import javax.swing.*;
import java.awt.*;

public class WordPanel extends JPanel {
    private JLabel wordLabel;
    private String wordToGuess;
    private StringBuilder displayedWord; // Track revealed letters

    public WordPanel(String word) {
        this.wordToGuess = word;
        displayedWord = new StringBuilder(displayWordWithUnderscores(wordToGuess));
        setupPanel();
    }

    private void setupPanel() {
        setLayout(new FlowLayout());
        wordLabel = new JLabel(displayedWord.toString());
        add(wordLabel);
    }

    private String displayWordWithUnderscores(String word) {
        StringBuilder sb = new StringBuilder();
        for (char letter : word.toCharArray()) {
            sb.append(letter == ' ' ? " " : "_");
        }
        return sb.toString();
    }

    public boolean guess(char letter) {
        boolean found = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                displayedWord.setCharAt(i, letter); // Reveal letter in displayedWord
                found = true;
            }
        }
        return found;
    }

    public void revealLetter(char letter) {
        wordLabel.setText(displayedWord.toString()); // Update displayed word on label
    }

    public boolean isWordGuessed() {
        for (char c : displayedWord.toString().toCharArray()) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public void resetWord(String newWord) {
        wordToGuess = newWord;
        displayedWord = new StringBuilder(displayWordWithUnderscores(wordToGuess));
        wordLabel.setText(displayedWord.toString()); // Update displayed word
    }
}
