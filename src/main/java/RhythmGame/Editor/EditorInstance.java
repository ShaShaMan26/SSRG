package RhythmGame.Editor;

import RhythmGame.Game.*;
import RhythmGame.Instance;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.*;

public class EditorInstance {
    GameWindow gameWindow;
    public EditorSpace editorSpace;
    Song song;
    File levelFile;
    boolean active = true;
    boolean wantsToTest = false;
    Dimension displayDimension;

    public EditorInstance(File levelFile, GameWindow gameWindow, Dimension displayDimension) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        this.displayDimension = displayDimension;
        this.gameWindow = gameWindow;

        this.levelFile = levelFile;
        this.song = new Song(levelFile);
        editorSpace = new EditorSpace(displayDimension, song);

        this.gameWindow.add(editorSpace);
        this.gameWindow.addMouseListener(new EditorMouseListener(this));

        this.gameWindow.addKeyListener(new EditorActionListener(this));

        gameWindow.requestFocus();
    }

    public void save() throws IOException {
        JSONArray noteArray = new JSONArray();
        for (EditorNode node : editorSpace.editorTrack.editorNodes) {
            if (node.added) {
                JSONObject note = new JSONObject();
                note.put("id", node.id);
                note.put("timeStamp", node.timeStamp);
                noteArray.add(note);
            }
        }

        FileWriter fileWriter = new FileWriter(levelFile);
        fileWriter.write("""
                {
                "SongData":""");
        fileWriter.write(song.songData.toJSONString());
        fileWriter.write("""
                ,
                "NoteData":""");
        fileWriter.write(noteArray.toJSONString());
        fileWriter.write("\n}");
        fileWriter.close();
    }

    public void checkWantsToTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        if (wantsToTest) {
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }
            for (MouseListener mouseListener : gameWindow.getMouseListeners()) {
                gameWindow.removeMouseListener(mouseListener);
            }

            GameInstance gameInstance = new GameInstance(levelFile, gameWindow, displayDimension, true, editorSpace.editorNeedle.playbackTimestamp);

            editorSpace.setVisible(false);

            song.playing = false;
            song.stopMusic();

            gameInstance.run();

            editorSpace.setVisible(true);

            this.gameWindow.addMouseListener(new EditorMouseListener(this));
            this.gameWindow.addKeyListener(new EditorActionListener(this));

            wantsToTest = false;
        }
    }

    public void run() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (active) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                checkWantsToTest();
                gameWindow.repaint();
                editorSpace.editorNeedle.setPlaybackTimestamp((int)(song.audioClip.getMicrosecondPosition() / (1000000 / (song.bpm / 60))));
                if (song.playing && editorSpace.editorNeedle.isOutOfBounds()) {
                    editorSpace.editorTrack.globalTimeStamp = editorSpace.editorNeedle.playbackTimestamp;
                    editorSpace.editorTrack.updateNodes(editorSpace.editorTrack.globalTimeStamp);
                }
                delta--;
            }
        }

        song.audioClip.close();
    }
}
