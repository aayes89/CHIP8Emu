
package chip8;

/**
 *
 * @author Slam
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Teclado implements KeyListener {

    private final Map<Integer, Integer> KEYMAP;
    private boolean[] keysPressed = new boolean[16]; // Array para 16 teclas CHIP-8
    private KeyPressHandler onNextKeyPress;

    public Teclado() {
        KEYMAP = new HashMap<>();
        KEYMAP.put(KeyEvent.VK_1, 0x1); // 1
        KEYMAP.put(KeyEvent.VK_2, 0x2); // 2
        KEYMAP.put(KeyEvent.VK_3, 0x3); // 3
        KEYMAP.put(KeyEvent.VK_4, 0xC); // 4
        KEYMAP.put(KeyEvent.VK_Q, 0x4); // Q
        KEYMAP.put(KeyEvent.VK_W, 0x5); // W
        KEYMAP.put(KeyEvent.VK_E, 0x6); // E
        KEYMAP.put(KeyEvent.VK_R, 0xD); // R
        KEYMAP.put(KeyEvent.VK_A, 0x7); // A
        KEYMAP.put(KeyEvent.VK_S, 0x8); // S
        KEYMAP.put(KeyEvent.VK_D, 0x9); // D
        KEYMAP.put(KeyEvent.VK_F, 0xE); // F
        KEYMAP.put(KeyEvent.VK_Z, 0xA); // Z
        KEYMAP.put(KeyEvent.VK_X, 0x0); // X
        KEYMAP.put(KeyEvent.VK_C, 0xB); // C
        KEYMAP.put(KeyEvent.VK_V, 0xF); // V
    }

    public synchronized boolean isKeyPressed(int keyCode) {
        return keyCode >= 0 && keyCode < keysPressed.length && keysPressed[keyCode];
    }

    @Override
    public synchronized void keyPressed(KeyEvent event) {
        Integer key = KEYMAP.get(event.getKeyCode());
        if (key != null) {
            keysPressed[key] = true;

            if (onNextKeyPress != null) {
                onNextKeyPress.onKeyPress(key);
                onNextKeyPress = null;
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent event) {
        Integer key = KEYMAP.get(event.getKeyCode());
        if (key != null) {
            keysPressed[key] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // No se necesita manejar este evento para el propósito de esta clase
    }

    public void setOnNextKeyPress(KeyPressHandler handler) {
        this.onNextKeyPress = handler;
    }

    public interface KeyPressHandler {

        void onKeyPress(int key);
    }
}
