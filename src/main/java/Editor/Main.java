package Editor;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        EditorInstance editorInstance = new EditorInstance(new File("C:\\Users\\shash\\IdeaProjects\\RhythmGame\\src\\main\\java\\Songs\\Test_Song\\songData.json"));
        editorInstance.run();
    }
}
