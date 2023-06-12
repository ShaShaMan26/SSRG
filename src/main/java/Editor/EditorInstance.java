package Editor;

import Game.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class EditorInstance {
    private final Dimension DISPLAY_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    GameWindow gameWindow = new GameWindow(DISPLAY_DIMENSIONS);
    EditorSpace editorSpace;
    Song song;
    int timeStamp;

    EditorInstance(Song song) {
        editorSpace = new EditorSpace(DISPLAY_DIMENSIONS, song);
        this.song = song;

        populateNotes();

        gameWindow.add(editorSpace);
        gameWindow.addMouseListener(new EditorMouseListener(editorSpace));
    }

    public void populateNotes() {
        ArrayList<Note> notes = song.getNotes();
        for (Note note : notes) {
            //editorSpace.addNote(note);
        }
    }

    public void run() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                gameWindow.repaint();
                delta--;
            }
        }
    }
}
