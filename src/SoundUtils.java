import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundUtils {

    public static void playSound(String soundFileName) {
        try {
            // Load the sound file from the sounds/ directory in the root of the project
            File soundFile = new File("sounds/" + soundFileName);  // ✅ Correct path
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();  // Play the sound
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
