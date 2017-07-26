package io.github.apollozhu.awt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface AZKeyAdapter extends KeyListener {
    /**
     * Invoked when a key has been typed.
     * This event occurs when a key press is followed by a key release.
     */
    default void keyTyped(KeyEvent e) { }

    /**
     * Invoked when a key has been pressed.
     */
    default void keyPressed(KeyEvent e) { }

    /**
     * Invoked when a key has been released.
     */
    default void keyReleased(KeyEvent e) { }
}
