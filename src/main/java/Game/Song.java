package Game;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.json.simple.*;

import javax.sound.sampled.*;

public class Song extends Component {
    public JSONObject songData;
    public JSONArray noteData;
    String name;
    public long length;
    public long bpm;
    File track;
    File icon;
    public Clip audioClip;
    public boolean playing = false;

    public Song(Object songDataFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JSONObject JSONData = (JSONObject) songDataFile;

        this.songData = (JSONObject) JSONData.get("SongData");
        this.noteData = (JSONArray) JSONData.get("NoteData");

        this.name = (String) songData.get("name");
        this.length = (long) songData.get("length");
        this.bpm = (long) songData.get("bpm");
        this.track = new File((String) songData.get("trackName"));
        //this.icon = new File();


        AudioInputStream audioStream = AudioSystem.getAudioInputStream(track);
        audioClip = AudioSystem.getClip();
        audioClip.open(audioStream);
    }

    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        noteData.iterator().forEachRemaining(currentNote -> {
            notes.add(new Note((int) (long) ((JSONObject) currentNote).get("id"), (int) (long) ((JSONObject) currentNote).get("timeStamp")));
        });

        return notes;
    }

    public void startMusic() {
        audioClip.start();
        playing = true;
    }

    public void stopMusic() {
        audioClip.stop();
        playing = false;
    }

    public void resetMusic() {
        audioClip.setMicrosecondPosition(0);
    }

    public void setMusicPos(int timeStamp) {
        audioClip.setMicrosecondPosition(timeStamp * 1000000L);
    }
}
