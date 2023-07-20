import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class IsKeyPressed {
    private static volatile boolean leftPressed = false;
    private static volatile boolean rightPressed = false;
    private static volatile boolean spacePressed = false;
    private static volatile boolean wPressed = false;
    public static boolean isLEFTPressed() {
        synchronized (IsKeyPressed.class) {
            return leftPressed;
        }
    }
    public static boolean isRIGHTPressed() {
        synchronized (IsKeyPressed.class) {
            return rightPressed;
        }
    }
    public static boolean isSPACEPressed() {
        synchronized (IsKeyPressed.class) {
            return spacePressed;
        }
    }
    public static boolean isWPressed() {
        synchronized (IsKeyPressed.class) {
            return wPressed;
        }
    }

    public static void main() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        
                        if (ke.getKeyCode() == KeyEvent.VK_A) {
                            leftPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                            rightPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                            spacePressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = true;
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (ke.getKeyCode() == KeyEvent.VK_A) {
                            leftPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                            rightPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                            spacePressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = false;
                        }
                        break;
                    }
                    return false;
                }
            }
        });
    }
}