package RhythmGame.Menus;

import RhythmGame.Instance;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MainMenuKeyListener implements KeyListener {
    Instance instance;

    public MainMenuKeyListener(Instance instance) {
        this.instance = instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '\u001B' -> System.exit(0);
            case 'e' -> {
                try {
                    instance.goToEditor();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case 'g' -> {
                try {
                    instance.goToGame();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
