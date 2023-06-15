package Editor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class EditorActionListener implements KeyListener {
    EditorInstance editorInstance;

    EditorActionListener(EditorInstance editorInstance) {
        this.editorInstance = editorInstance;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                System.exit(0);
                break;
            case 37:
                editorInstance.editorSpace.editorTrack.moveBackward();
                break;
            case 39:
                editorInstance.editorSpace.editorTrack.moveForward();
                break;
            case 32:
                if (editorInstance.song.playing) {
                    editorInstance.song.stopMusic();
                } else {
                    editorInstance.song.startMusic();
                }
                break;
            case 82:
                editorInstance.song.resetMusic();
                editorInstance.editorSpace.editorNeedle.setPlaybackTimestamp(0);
                break;
            case 83:
                try {
                    editorInstance.save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

