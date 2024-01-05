package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upKey, downKey, leftKey, rightKey;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //returns num of the key pressed

        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            upKey = true;
        }
        if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            downKey = true;
        }
        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            leftKey = true;
        }
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightKey = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            upKey = false;
        }
        if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            downKey = false;
        }
        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            leftKey = false;
        }
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightKey = false;
        }
    }
}
