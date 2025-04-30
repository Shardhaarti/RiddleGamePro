import javax.swing.*;

public class GameProgressBar {
    private JProgressBar progressBar;
    private int totalRiddles;

    public GameProgressBar(int total) {
        totalRiddles = total;
        progressBar = new JProgressBar(0, totalRiddles);
        progressBar.setStringPainted(true);
    }

    public void updateProgress(int current) {
        progressBar.setValue(current);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }
}
