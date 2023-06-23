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

        for (int i = 1; i <= songNames.length; i++) {
            System.out.println(i + ". " + songNames[i-1]);
        }

        Scanner songSelectionListener = new Scanner(System.in);
        int selectedSongIndex = songSelectionListener.nextInt() - 1;

        new Instance(new File(System.getenv("APPDATA")+"\\SSRG\\songs\\" + songNames[selectedSongIndex] + "\\songData.json")).run();
    }
}
