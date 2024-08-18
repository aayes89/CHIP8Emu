
package chip8;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Slam
 */
import javax.sound.sampled.*;

public class Sonido {

    private SourceDataLine line;
    private boolean isPlaying;

    public Sonido() {
        try {
            // Configure the audio format
            AudioFormat format = new AudioFormat(44100, 8, 1, true, true);
            line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            System.out.println("LineUnavailableE: " + e.getMessage());
        }
    }

    public void play0(int frequency, int duration) {
        if (line != null && !isPlaying) {
            isPlaying = true;

            // Genera sonido en un hilo separado
            new Thread(() -> {
                byte[] buffer = new byte[2];
                double angle = 0;
                double increment = 2.0 * Math.PI * frequency / 44100;
                for (int i = 0; i < 44100 * duration / 1000 && isPlaying; i++) {
                    buffer[0] = (byte) (Math.sin(angle) * 127);
                    buffer[1] = buffer[0]; // Mono audio
                    line.write(buffer, 0, buffer.length);
                    angle += increment;
                    if (angle > 2.0 * Math.PI) {
                        angle -= 2.0 * Math.PI;
                    }
                }
                isPlaying = false;
            }).start();
        }
    }

    public synchronized void play(int frequency, int duration) {
        if (line != null && !isPlaying) {
            isPlaying = true;

            new Thread(() -> {
                byte[] buffer = new byte[1];
                double angle = 0;
                double increment = 2.0 * Math.PI * frequency / 44100;
                int length = (int) (44100 * (duration / 1000.0));

                for (int i = 0; i < length && isPlaying; i++) {
                    buffer[0] = (byte) (Math.sin(angle) * 127);
                    line.write(buffer, 0, 1);
                    angle += increment;
                    if (angle > 2.0 * Math.PI) {
                        angle -= 2.0 * Math.PI;
                    }
                }

                stop();
            }).start();
        }
    }

    public synchronized void stop() {
        isPlaying = false;
        if (line != null) {
            line.flush();
        }
    }

    public void close() {
        stop();
        if (line != null) {
            line.stop();
            line.close();
        }
    }
}
