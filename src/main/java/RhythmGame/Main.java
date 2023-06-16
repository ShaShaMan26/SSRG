package RhythmGame;

import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        new File(System.getenv("APPDATA")+"\\RhythmGame").mkdir();
        new File(System.getenv("APPDATA")+"\\RhythmGame\\songs").mkdir();
        new Instance(new File(System.getenv("APPDATA")+"\\RhythmGame\\songs\\Wii_Shop_Theme\\songData.json")).run();
    }
}
