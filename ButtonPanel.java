package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class ButtonPanel extends JPanel implements ActionListener {
    private JButton[] buttons;
    private WordPanel wordPanel;
    private HealthPanel healthPanel;
    private MainWindow mainWindow;
    private Set<Character> guessedLetters;

    public ButtonPanel(WordPanel wordPanel, HealthPanel healthPanel, MainWindow mainWindow) {
        this.wordPanel = wordPanel;
        this.healthPanel = healthPanel;
        this.mainWindow = mainWindow;
        guessedLetters = new HashSet<>(); // Initialize set to store guessed letters
        setLayout(new GridLayout(2, 13));

        buttons = new JButton[26];
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            buttons[i] = new JButton("" + letter);
            buttons[i].addActionListener(this);
            add(buttons[i]);
            letter++;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (mainWindow.gameOver) {
            return; // Don't accept input during game over
        }

        JButton clickedButton = (JButton) e.getSource();
        char guessedLetter = clickedButton.getText().charAt(0);
        
        if (guessedLetters.contains(guessedLetter)) {
            // Letter already guessed, do nothing
            return;
        }
        
        guessedLetters.add(guessedLetter); // Add guessed letter to set
        boolean correctGuess = wordPanel.guess(guessedLetter); // Call guess method from WordPanel

        if (correctGuess) {
            clickedButton.setEnabled(false); // Disable button for guessed letter
            wordPanel.revealLetter(guessedLetter); // Update WordPanel with guessed letter
            if (wordPanel.isWordGuessed()) {
                mainWindow.gameOver(true); // Win condition
            }
        } else {
            healthPanel.removeLife(); // Corrected line, missing closing curly brace
            if (healthPanel.isGameOver()) {
                mainWindow.gameOver(false); // Lose condition
            }
        }
    }

    public void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public void enableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
        guessedLetters.clear(); // Clear guessed letters set for new game
    }
}
