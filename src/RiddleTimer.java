import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiddleTimer {
    private Timer timer;
    private int timeLeft;
    private JLabel timerLabel;
    private Runnable onTimeUp;

    public RiddleTimer(int seconds, JLabel label, Runnable onTimeUp) {
        this.timeLeft = seconds;
        this.timerLabel = label;
        this.onTimeUp = onTimeUp;

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    updateLabel();
                } else {
                    timer.stop();
                    onTimeUp.run(); // Action when time runs out
                }
            }
        });
    }

    private void updateLabel() {
        timerLabel.setText("Time Left: " + timeLeft + "s");
    }

    public void start() {
        updateLabel();  // Ensure the label starts with the correct time
        timer.start();
    }

    public void stop() {
        if (timer != null) timer.stop();
    }

    public void reset(int newTime) {
        this.timeLeft = newTime;
        updateLabel();
    }
}



