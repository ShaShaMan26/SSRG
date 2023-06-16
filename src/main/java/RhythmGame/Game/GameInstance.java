package RhythmGame.Game;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameInstance {
    GameWindow gameWindow;
    public GameSpace gameSpace;
    ScoreDisplay scoreDisplay;
    Song song;
    File levelFile;
    int timeStamp;
    int score = 0;
    boolean active = true;

    public GameInstance(File levelFile, GameWindow gameWindow, Dimension displayDimension) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        this.gameWindow = gameWindow;
        gameSpace = new GameSpace(displayDimension);
        scoreDisplay = new ScoreDisplay(displayDimension);

        this.levelFile = levelFile;

        this.song = new Song(levelFile);

        populateNotes();

        gameSpace.add(scoreDisplay);
        this.gameWindow.add(gameSpace);
        this.gameWindow.addKeyListener(new NoteReceiverController(this));
        timeStamp = -(gameSpace.noteAisle1.receiverHeight * 10);
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

    public void run() {
        song.startMusic();
        while (song.audioClip.getMicrosecondPosition() == 0);
        song.stopMusic();
        song.resetMusic();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (active) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                if (timeStamp >= -gameSpace.noteAisle1.receiverHeight) {
                    song.startMusic();
                }

                gameSpace.updateNoteAisles(timeStamp, (int) amountOfTicks);
                gameWindow.repaint();
                timeStamp += song.bpm / amountOfTicks;
                checkCollisions();
                scoreDisplay.setScore(score);
                delta--;
            }
        }
        song.stopMusic();
        song.resetMusic();
    }
}
