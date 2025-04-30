import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class RiddleGamePro {
    private JFrame frame;
    private JTextArea riddleArea;
    private JTextField answerField;
    private JLabel hintLabel, scoreLabel, statusLabel, timerLabel, rollNumberLabel;
    private JButton submitButton, hintButton, nextButton, themeToggleButton;

    private boolean darkMode = false;
    private int score = 0;
    private int index = 0;

    private final String[] riddles = {
            "I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?",
            "The more you take, the more you leave behind. What am I?",
            "I'm not alive, but I can grow; I don't have lungs, but I need air; I don't have a mouth, but water kills me. What am I?",
            "What has hands but can’t clap?",
            "What gets wetter the more it dries?",
            "What can travel around the world while staying in the same corner?",
            "What has a head and a tail but no body?",
            "What has keys but can’t open locks?",
            "What comes once in a minute, twice in a moment, but never in a thousand years?",
            "What begins with T, ends with T, and has T in it?",
            "What has an eye but cannot see?",
            "What is full of holes but still holds water?",
            "What can you catch but not throw?",
            "What has many teeth, but cannot bite?",
            "What is always in front of you but can’t be seen?",
            "What goes up but never comes down?",
            "What can you break, even if you never pick it up or touch it?",
            "I’m tall when I’m young, and I’m short when I’m old. What am I?",
            "The more of me you take, the more you leave behind. What am I?",
            "What has one eye but can’t see?"
    };

    private final String[] answers = {
            "echo", "footsteps", "fire", "clock",
            "towel", "stamp", "coin", "piano",
            "m", "teapot", "needle", "sponge",
            "cold", "comb", "future", "your age",
            "promise", "candle", "footsteps", "needle"
    };

    private final String[] hints = {
            "It bounces back sound.", "You leave them when you walk.",
            "It burns but needs air.", "Used to tell time.",
            "Dries you off.", "On envelopes.", "Round and shiny.", "Makes music.",
            "A letter in the alphabet.", "Used to brew tea.", "Used for sewing.", "It’s absorbent.",
            "It makes you sneeze.", "Used on your hair.", "It’s ahead of you.", "Everyone has one.",
            "You give your word.", "It gives light.", "It’s a repeat.", "Also used for sewing."
    };

    private RiddleTimer riddleTimer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RiddleGamePro::new);
    }

    public RiddleGamePro() {
        frame = new JFrame("Riddle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Initialize components
        rollNumberLabel = new JLabel("Roll Number: 24BSCYS047", SwingConstants.LEFT);
        rollNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        rollNumberLabel.setForeground(Color.BLACK);

        timerLabel = new JLabel("Time Left: 30s", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        // Setup timer (initialize the RiddleTimer here)
        riddleTimer = new RiddleTimer(30, timerLabel, this::onTimeUp);  // 30 seconds

        // Header
        JLabel title = new JLabel("Riddle Challenge", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setOpaque(true);
        title.setBackground(new Color(72, 133, 237));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Center Panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        riddleArea = new JTextArea();
        riddleArea.setFont(new Font("Serif", Font.PLAIN, 18));
        riddleArea.setLineWrap(true);
        riddleArea.setWrapStyleWord(true);
        riddleArea.setEditable(false);
        riddleArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(riddleArea);
        scroll.setPreferredSize(new Dimension(640, 100));
        centerPanel.add(scroll);

        // Answer field and buttons
        answerField = new JTextField();
        answerField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        answerField.setBorder(BorderFactory.createTitledBorder("Your Answer"));
        centerPanel.add(answerField);

        hintLabel = new JLabel(" ", SwingConstants.LEFT);
        hintLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        hintLabel.setForeground(new Color(66, 133, 244));
        centerPanel.add(hintLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        submitButton = new JButton("Submit");
        hintButton = new JButton("Hint");
        nextButton = new JButton("Next");
        themeToggleButton = new JButton("Toggle Theme");
        buttonPanel.add(submitButton);
        buttonPanel.add(hintButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(themeToggleButton);
        centerPanel.add(buttonPanel);

        // Footer Panel
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        scoreLabel.setForeground(new Color(52, 168, 83));

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel footerPanel = new JPanel(new GridLayout(2, 1));
        footerPanel.add(scoreLabel);
        footerPanel.add(statusLabel);

        // Create a panel for the roll number label and shift it down
        JPanel rollNumberPanel = new JPanel();
        rollNumberPanel.setLayout(new BoxLayout(rollNumberPanel, BoxLayout.Y_AXIS));  // Use vertical BoxLayout
        rollNumberPanel.add(Box.createVerticalStrut(50)); // Add vertical space
        rollNumberPanel.add(rollNumberLabel);

        // Adding components to the frame
        frame.add(title, BorderLayout.NORTH);
        frame.add(rollNumberPanel, BorderLayout.WEST);  // Display roll number in the left, but shifted down
        frame.add(timerLabel, BorderLayout.EAST);  // Display timer on the right
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        loadRiddle();

        submitButton.addActionListener(this::submitAnswer);
        hintButton.addActionListener(e -> hintLabel.setText("Hint: " + hints[index]));
        nextButton.addActionListener(e -> {
            index++;
            loadRiddle();
        });
        themeToggleButton.addActionListener(e -> toggleTheme(centerPanel));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadRiddle() {
        if (index >= riddles.length) {
            riddleArea.setText("🎉 You've finished all riddles!");
            answerField.setEnabled(false);
            submitButton.setEnabled(false);
            hintButton.setEnabled(false);
            nextButton.setEnabled(false);
            statusLabel.setText("");
        } else {
            riddleArea.setText(riddles[index]);
            answerField.setText("");
            hintLabel.setText(" ");
            statusLabel.setText("Riddle " + (index + 1) + " of " + riddles.length);
            riddleTimer.reset(30);  // Reset to 30 seconds for each new riddle
            riddleTimer.start();    // Start the timer
        }
    }

    private void submitAnswer(ActionEvent e) {
        String userAnswer = answerField.getText().trim();
        if (userAnswer.equalsIgnoreCase(answers[index])) {
            playSound("correct.wav");
            JOptionPane.showMessageDialog(frame, "✅ Correct!", "Well Done", JOptionPane.INFORMATION_MESSAGE);
            score++;
            scoreLabel.setText("Score: " + score);
        } else {
            playSound("incorrect.wav");
            JOptionPane.showMessageDialog(frame, "❌ Wrong!\nCorrect Answer: " + answers[index], "Oops!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toggleTheme(JPanel centerPanel) {
        darkMode = !darkMode;
        Color bg = darkMode ? Color.DARK_GRAY : Color.decode("#f0f2f5");
        Color fg = darkMode ? Color.WHITE : Color.BLACK;

        centerPanel.setBackground(bg);
        riddleArea.setBackground(darkMode ? Color.GRAY : Color.WHITE);
        riddleArea.setForeground(fg);
        answerField.setBackground(darkMode ? Color.GRAY : Color.WHITE);
        answerField.setForeground(fg);
        hintLabel.setForeground(darkMode ? Color.LIGHT_GRAY : new Color(66, 133, 244));
        scoreLabel.setForeground(darkMode ? Color.LIGHT_GRAY : Color.BLACK);
        statusLabel.setForeground(darkMode ? Color.LIGHT_GRAY : Color.BLACK);

        frame.getRootPane().setBackground(bg);
    }

    private void onTimeUp() {
        playSound("timesup.wav");
        JOptionPane.showMessageDialog(frame, "⏰ Time's up!\nCorrect Answer: " + answers[index], "Time Over", JOptionPane.WARNING_MESSAGE);
        index++;
        loadRiddle();
    }

    private void playSound(String soundFile) {
        try {
            File sound = new File("sounds/" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
