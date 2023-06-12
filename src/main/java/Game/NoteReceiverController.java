package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NoteReceiverController implements KeyListener {
    GameSpace gameSpace;
    NoteReceiverController(GameSpace gameSpace) {
        this.gameSpace = gameSpace;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'd' -> gameSpace.noteAisle1.setActive(true);
            case 'f' -> gameSpace.noteAisle2.setActive(true);
            case 'j' -> gameSpace.noteAisle3.setActive(true);
            case 'k' -> gameSpace.noteAisle4.setActive(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'd' -> gameSpace.noteAisle1.setActive(false);
            case 'f' -> gameSpace.noteAisle2.setActive(false);
            case 'j' -> gameSpace.noteAisle3.setActive(false);
            case 'k' -> gameSpace.noteAisle4.setActive(false);
        }
    }
}
