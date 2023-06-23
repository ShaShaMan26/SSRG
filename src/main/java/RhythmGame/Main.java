package RhythmGame;

import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        new File(System.getenv("APPDATA")+"\\SSRG").mkdir();
        new File(System.getenv("APPDATA")+"\\SSRG\\songs").mkdir();

        String[] songNames = new File(System.getenv("APPDATA") + "\\SSRG\\songs").list();

        assert songNames != null;
        new Instance(new File(System.getenv("APPDATA")+"\\SSRG\\songs\\" + songNames[0] + "\\songData.json")).run();
    }
}
