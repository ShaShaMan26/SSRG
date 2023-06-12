package Game;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.json.simple.*;

import javax.sound.sampled.*;

public class Song extends Component {
    JSONObject songData;
    JSONArray noteData;
    String name;
    public long length;
    public long bpm;
    File track;
    File icon;

    public Song(Object songDataFile) {
        JSONObject JSONData = (JSONObject) songDataFile;

        this.songData = (JSONObject) JSONData.get("SongData");
        this.noteData = (JSONArray) JSONData.get("NoteData");

        this.name = (String) songData.get("name");
        this.length = (long) songData.get("length");
        this.bpm = (long) songData.get("bpm");
        this.track = new File((String) songData.get("trackName"));
        //this.icon = new File();
    }

    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        noteData.iterator().forEachRemaining(currentNote -> {
            notes.add(new Note((int) (long) ((JSONObject) currentNote).get("id"), (int) (long) ((JSONObject) currentNote).get("timeStamp")));
        });

        return notes;
    }

    public void startMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(track);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }
}
