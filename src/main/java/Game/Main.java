package Game;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        new GameInstance(new Song(new JSONParser().parse(new FileReader("C:\\Users\\shash\\IdeaProjects\\RhythmGame\\src\\main\\java\\Songs\\Test_Song\\songData.json")))).run();
    }
}
