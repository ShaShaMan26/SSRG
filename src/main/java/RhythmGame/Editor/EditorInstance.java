package RhythmGame.Editor;

import RhythmGame.Game.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.*;

public class EditorInstance {
    GameWindow gameWindow;
    public EditorSpace editorSpace;
    Song song;
    File levelFile;
    boolean active = true;

    public EditorInstance(File levelFile, GameWindow gameWindow, Dimension displayDimension) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        this.gameWindow = gameWindow;

        this.levelFile = levelFile;
        this.song = new Song(levelFile);
        editorSpace = new EditorSpace(displayDimension, song);

        this.gameWindow.add(editorSpace);
        this.gameWindow.addMouseListener(new EditorMouseListener(this));

        this.gameWindow.addKeyListener(new EditorActionListener(this));
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

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (active) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                gameWindow.repaint();
                editorSpace.editorNeedle.setPlaybackTimestamp((int)(song.audioClip.getMicrosecondPosition() / (1000000 / (song.bpm / 60))));
                if (song.playing && editorSpace.editorNeedle.isOutOfBounds()) {
                    editorSpace.editorTrack.globalTimeStamp = editorSpace.editorNeedle.playbackTimestamp;
                    editorSpace.editorTrack.updateNodes(editorSpace.editorTrack.globalTimeStamp);
                }
                delta--;
            }
        }

    }
}
