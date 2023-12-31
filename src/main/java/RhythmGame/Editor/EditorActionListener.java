package RhythmGame.Editor;

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
        editorInstance.editorSpace.savingStatus = "";
        switch (e.getKeyCode()) {
            case 27:
                editorInstance.active = false;
                break;
            case 37:
            case 65:
                editorInstance.editorSpace.editorTrack.moveBackward();
                break;
            case 39:
            case 68:
                editorInstance.editorSpace.editorTrack.moveForward();
                break;
            case 32:
                if (editorInstance.song.playing) {
                    editorInstance.song.stopMusic();
                } else {
                    editorInstance.song.startMusic();
                }
                break;
            case 87:
            case 38:
                editorInstance.editorSpace.editorTrack.increaseScrollSpeed();
                break;
            case 83:
            case 40:
                editorInstance.editorSpace.editorTrack.decreaseScrollSpeed();
                break;
            case 82:
                editorInstance.song.resetMusic();
                editorInstance.editorSpace.editorNeedle.setPlaybackTimestamp(0);
                editorInstance.editorSpace.editorTrack.scrollSpeed = 1;
                break;
            case 49:
                editorInstance.wantsToSave = true;
                break;
            case 84:
                editorInstance.wantsToTest = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

