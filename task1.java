import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class task1 extends JFrame {
    private JLabel roundLabel;
    private JLabel guessLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JButton playAgainButton;

    private int score;
    private int rounds;
    private int targetNumber;
    private int attempts;

    public task1() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        roundLabel = new JLabel("Round 1");
        roundLabel.setBounds(20, 20, 100, 20);
        add(roundLabel);

        guessLabel = new JLabel("Enter your guess:");
        guessLabel.setBounds(20, 50, 200, 20);
        add(guessLabel);

        guessTextField = new JTextField();
        guessTextField.setBounds(220, 50, 50, 20);
        add(guessTextField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(280, 50, 80, 20);
        add(guessButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(20, 80, 200, 20);
        add(resultLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(20, 110, 100, 20);
        add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(150, 140, 100, 30);
        playAgainButton.setVisible(false);
        add(playAgainButton);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (attempts < 5) {
                    int guess = Integer.parseInt(guessTextField.getText());
                    attempts++;

                    if (guess == targetNumber) {
                        resultLabel.setText("Congratulations! You guessed the correct number!");
                        score += 10 - attempts;
                        guessButton.setEnabled(false);
                        playAgainButton.setVisible(true);
                    } else if (guess < targetNumber) {
                        resultLabel.setText("Too low! Try again.");
                    } else {
                        resultLabel.setText("Too high! Try again.");
                    }
                    scoreLabel.setText("Score: " + score);
                }

                if (attempts == 3) {
                    resultLabel.setText("Attempts exhausted. The number was: " + targetNumber);
                    guessButton.setEnabled(false);
                    playAgainButton.setVisible(true);
                }
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewRound();
            }
        });

        startNewRound();
    }

    private void startNewRound() {
        rounds++;
        roundLabel.setText("Round " + rounds);

        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        resultLabel.setText("");
        guessTextField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new task1().setVisible(true);
            }
        });
    }
}