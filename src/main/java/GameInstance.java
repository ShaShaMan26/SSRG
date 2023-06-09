import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameInstance {
    private final Dimension DISPLAY_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    GameWindow gameWindow = new GameWindow(DISPLAY_DIMENSIONS);
    GameSpace gameSpace = new GameSpace(DISPLAY_DIMENSIONS);
    ScoreDisplay scoreDisplay = new ScoreDisplay(DISPLAY_DIMENSIONS);
    Song song;
    int timeStamp;
    int score = 0;


    GameInstance(Song song) {
        this.song = song;

        populateNotes();

        gameSpace.add(scoreDisplay);
        gameWindow.add(gameSpace);
        gameWindow.addKeyListener(gameSpace.noteReceiverController);
    }

    public void populateNotes() {
        ArrayList<Note> notes = song.getNotes();
        for (Note note : notes) {
            gameSpace.addNote(note);
        }
    }

    public void checkCollisions() {
        score += gameSpace.checkNoteAisleReceptions();
    }

    public void run() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        song.startMusic();
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
                checkCollisions();
                scoreDisplay.setScore(score);
                delta--;
            }
        }
    }
}
