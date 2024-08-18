
package chip8;

/**
 *
 * @author Slam
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Graficos extends JPanel {

    private final int cols;
    private final int rows;
    private final int scale;
    private final BufferedImage display;

    public Graficos(int scale) {
        this.cols = 64;
        this.rows = 32;
        this.scale = scale;

        // Tamaño del canvas (BufferedImage)
        this.display = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);
        
        // Configura el tamaño del panel que contendrá la imagen escalada
        this.setPreferredSize(new java.awt.Dimension(cols * scale, rows * scale));
    }

    public boolean setPixel(int x, int y) {
        if (x >= cols) {
            x -= cols;
        } else if (x < 0) {
            x += cols;
        }

        if (y >= rows) {
            y -= rows;
        } else if (y < 0) {
            y += rows;
        }

        int pixelLoc = x + (y * cols);
        int color = (display.getRGB(x, y) == Color.BLACK.getRGB()) ? Color.WHITE.getRGB() : Color.BLACK.getRGB();
        display.setRGB(x, y, color);

        return color == Color.WHITE.getRGB(); // Retorna verdadero si el píxel se apagó
    }

    public void limpiarPantalla() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                display.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Escala la imagen para que se ajuste al tamaño del panel
        g.drawImage(display, 0, 0, cols * scale, rows * scale, null);
    }

    public void repintarPantalla() {
        // Esto llama a paintComponent y actualiza la pantalla
        repaint();
    }

    
}
