
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationUtils {

    // Fade-in animation for a component over a specified duration
    public static void fadeInComponent(JComponent comp, int duration) {
        Timer timer = new Timer(20, null);  // Timer runs every 20 ms
        final float[] opacity = {0f}; // Starting opacity is 0 (fully transparent)

        timer.addActionListener(e -> {
            opacity[0] += 0.02f;  // Gradually increase opacity (0.02 per step)
            if (opacity[0] >= 1f) {
                opacity[0] = 1f;
                timer.stop();  // Stop the timer when the fade-in is complete
            }

            comp.repaint();  // Repaint the component to apply the new opacity
        });

        timer.start();
    }

    // Apply opacity when drawing the component
    public static void applyOpacity(Graphics g, float opacity) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity)); // Set opacity
    }

    // Scale animation for a component over a specified duration
    public static void scaleComponent(JComponent comp, int duration, double startScale, double endScale) {
        Timer timer = new Timer(20, null);  // Timer runs every 20 ms
        final double[] scale = {startScale};  // Starting scale value

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scale[0] += (endScale - startScale) / (duration / 20.0);  // Gradual scaling

                if (scale[0] >= endScale) {
                    scale[0] = endScale;
                    timer.stop();  // Stop the timer when the scale reaches the end value
                }

                comp.repaint();  // Repaint the component to apply the new scale
            }
        });

        timer.start();
    }

    // Slide-in animation for a component from the left (to right)
    public static void slideInComponent(JComponent comp, int duration, int startX, int endX) {
        Timer timer = new Timer(20, null);  // Timer runs every 20 ms
        final int[] xPosition = {startX};  // Starting x position

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xPosition[0] += (endX - startX) / (duration / 20.0);  // Gradual horizontal movement

                if (xPosition[0] >= endX) {
                    xPosition[0] = endX;
                    timer.stop();  // Stop the timer when the slide-in is complete
                }

                comp.repaint();  // Repaint the component to apply the new position
            }
        });

        timer.start();
    }
}

