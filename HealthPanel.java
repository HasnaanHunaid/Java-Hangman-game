package hangman;

import javax.swing.*;
import java.awt.*;

public class HealthPanel extends JPanel {
    private JLabel[] healthLabels;
    private int remainingLives;

    public HealthPanel(int maxLives) {
        this.remainingLives = maxLives;
        setupPanel();
    }

    private void setupPanel() {
        setLayout(new FlowLayout());
        healthLabels = new JLabel[remainingLives];

        for (int i = 0; i < remainingLives; i++) {
            healthLabels[i] = new JLabel("❤️");
            add(healthLabels[i]);
        }
    }

    public void removeLife() {
        if (remainingLives > 0) {
            healthLabels[remainingLives - 1].setText("💔");
            remainingLives--;
        }
    }

    public boolean isGameOver() {
        return remainingLives == 0;
    }

    public void resetLives() {
        for (JLabel healthLabel : healthLabels) {
            healthLabel.setText("❤️");
        }
        remainingLives = healthLabels.length;
    }
}