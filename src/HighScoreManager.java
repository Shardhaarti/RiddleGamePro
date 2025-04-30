import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String SCORE_FILE = "highscores.txt";
    private List<ScoreEntry> scores = new ArrayList<>();

    public HighScoreManager() {
        loadScores();
    }

    public void addScore(String playerName, int score) {
        scores.add(new ScoreEntry(playerName, score));
        Collections.sort(scores);
        if (scores.size() > 5) {
            scores = scores.subList(0, 5); // Keep only top 5
        }
        saveScores();
    }

    public List<ScoreEntry> getHighScores() {
        return scores;
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            scores.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
                }
            }
            Collections.sort(scores);
        } catch (IOException e) {
            // No file yet — ignore
        }
    }

    private void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORE_FILE))) {
            for (ScoreEntry entry : scores) {
                writer.println(entry.name + "," + entry.score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inner class to represent a score entry
    public static class ScoreEntry implements Comparable<ScoreEntry> {
        String name;
        int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(ScoreEntry other) {
            return Integer.compare(other.score, this.score); // Descending order
        }

        @Override
        public String toString() {
            return name + ": " + score;
        }
    }
}
