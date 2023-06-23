package RhythmGame.Game;

import RhythmGame.Editor.EditorInstance;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameInstance {
    GameWindow gameWindow;
    public GameSpace gameSpace;
    ScoreDisplay scoreDisplay;
    JLabel songLabel;
    Song song;
    int timeStamp;
    int score = 0;
    boolean active = true;
    boolean isTest;
    int startTime;
    Dimension displayDimension;

    public GameInstance(File levelFile, GameWindow gameWindow, Dimension displayDimension, boolean isTest, int startTime) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        this.displayDimension = displayDimension;
        this.isTest = isTest;
        this.startTime = startTime;
        this.gameWindow = gameWindow;

        this.song = new Song(levelFile);

        gameSpace = new GameSpace(displayDimension);
        scoreDisplay = new ScoreDisplay(displayDimension);

        songLabel = new JLabel();
        songLabel.setVisible(false);
        songLabel.setFont(new Font("Monospaced", Font.BOLD, displayDimension.height / 40));
        songLabel.setForeground(Color.WHITE);
        songLabel.setHorizontalTextPosition(JLabel.CENTER);
        songLabel.setVerticalTextPosition(JLabel.TOP);
        int iconSideLength = displayDimension.height / 5;
        songLabel.setIcon(new ImageIcon(song.icon.getImage().getScaledInstance(iconSideLength, iconSideLength, Image.SCALE_DEFAULT)));
        songLabel.setBounds(0, 0, iconSideLength, displayDimension.height);
        songLabel.setText("<html><p style=\"width:"+(int)(iconSideLength * 1.25)+"px\">" + song.name + "<br>" + song.author + "</p></html>");
        songLabel.setVerticalAlignment(JLabel.NORTH);
        gameSpace.add(songLabel);

        populateNotes(startTime);

        gameSpace.add(scoreDisplay);
        this.gameWindow.add(gameSpace);
        this.gameWindow.addKeyListener(new NoteReceiverController(this));
        timeStamp = -(gameSpace.noteAisle1.receiverHeight * 10);

        gameWindow.requestFocus();
    }

    public void populateNotes(int startTime) {
        ArrayList<Note> notes = song.getNotes(startTime);
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

        int scale = ((int) song.bpm / 60);
        song.setMusicPos(startTime / scale);

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

                if (song.audioClip.getMicrosecondPosition() >= song.length * 1000000) {
                    active = false;
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

        if (isTest) {
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }
            for (MouseListener mouseListener : gameWindow.getMouseListeners()) {
                gameWindow.removeMouseListener(mouseListener);
            }

            gameWindow.remove(gameSpace);
        }

        song.audioClip.close();
    }
}
