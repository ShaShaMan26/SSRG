import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameInstance {
    private final Dimension DISPLAY_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    GameWindow gameWindow = new GameWindow(DISPLAY_DIMENSIONS);
    GameSpace gameSpace = new GameSpace(DISPLAY_DIMENSIONS);
    Song song;
    int timeStamp;


    GameInstance(Song song) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.song = song;

        populateNotes();

        gameWindow.add(gameSpace);
        gameWindow.addKeyListener(gameSpace.noteReceiverController);

        this.song.startMusic();
    }

    public void populateNotes() {
        ArrayList<Note> notes = song.getNotes();
        for (Note note : notes) {
            gameSpace.addNote(note);
        }
    }

    public void run() {
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
                timeStamp += song.bpm / amountOfTicks;
                gameSpace.updateNoteAisles(timeStamp, (int) amountOfTicks);
                delta--;
            }
        }
    }
}
