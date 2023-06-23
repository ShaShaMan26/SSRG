package RhythmGame;

import RhythmGame.Editor.*;
import RhythmGame.Game.*;
import RhythmGame.Menus.MainMenu;
import RhythmGame.Menus.MainMenuInstance;
import RhythmGame.Menus.MainMenuKeyListener;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Instance {
    public final Dimension DISPLAY_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    public GameWindow gameWindow = new GameWindow(DISPLAY_DIMENSIONS);
    public File levelFile;
    public Song song;
    private JPanel currentPanel = new JPanel();
    public boolean wantsToSwitchToGame = false;
    private boolean wantsToSwitchToEditor = false;
    public boolean wantsToSwitchToMenu = false;

    Instance(File levelFile) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        this.levelFile = levelFile;
        this.song = new Song(levelFile);
        goToMainMenu();
    }

    public void setLevelFile(File levelFile) throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        this.levelFile = levelFile;
        this.song = new Song(levelFile);
        goToMainMenu();
    }

    public void checkForSwitch() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        if (wantsToSwitchToGame) {
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }
            for (MouseListener mouseListener : gameWindow.getMouseListeners()) {
                gameWindow.removeMouseListener(mouseListener);
            }

            GameInstance gameInstance = new GameInstance(this, false, 0);

            gameWindow.remove(currentPanel);

            wantsToSwitchToGame = false;
            wantsToSwitchToMenu = true;

            gameInstance.run();

            currentPanel = gameInstance.gameSpace;
        } else if (wantsToSwitchToEditor) {
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }
            for (MouseListener mouseListener : gameWindow.getMouseListeners()) {
                gameWindow.removeMouseListener(mouseListener);
            }

            EditorInstance editorInstance = new EditorInstance(this);

            gameWindow.remove(currentPanel);

            editorInstance.run();

            currentPanel = editorInstance.editorSpace;

            wantsToSwitchToEditor = false;
            wantsToSwitchToMenu = true;
        } else if (wantsToSwitchToMenu) {
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }
            for (MouseListener mouseListener : gameWindow.getMouseListeners()) {
                gameWindow.removeMouseListener(mouseListener);
            }

            MainMenuInstance mainMenuInstance = new MainMenuInstance(gameWindow, this);

            gameWindow.remove(currentPanel);
            currentPanel = mainMenuInstance.mainMenu;

            wantsToSwitchToMenu = false;
        }
    }

    public void goToMainMenu() {
        wantsToSwitchToMenu = true;
    }

    public void goToGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        wantsToSwitchToGame = true;
    }

    public void goToEditor() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        wantsToSwitchToEditor = true;
    }

    public void run() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                checkForSwitch();
                gameWindow.repaint();
                delta--;
            }
        }
    }
}
