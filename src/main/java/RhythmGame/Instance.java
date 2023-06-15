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
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Instance {
    private final Dimension DISPLAY_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    public GameWindow gameWindow = new GameWindow(DISPLAY_DIMENSIONS);
    File levelFile;
    JPanel currentPanel = new JPanel();
    boolean wantsToSwitchToGame = false;
    boolean wantsToSwitchToEditor = false;
    boolean wantsToSwitchToMenu = false;

    Instance(File levelFile) {
        this.levelFile = levelFile;
        goToMainMenu();
    }

    public void checkForSwitch() throws UnsupportedAudioFileException, LineUnavailableException, IOException, ParseException {
        if (wantsToSwitchToGame) {
            gameWindow.remove(currentPanel);
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }

            GameInstance gameInstance = new GameInstance(levelFile, gameWindow, DISPLAY_DIMENSIONS);
            currentPanel = gameInstance.gameSpace;

            gameInstance.run();

            wantsToSwitchToGame = false;
            wantsToSwitchToMenu = true;
        } else if (wantsToSwitchToEditor) {
            gameWindow.remove(currentPanel);
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }

            EditorInstance editorInstance = new EditorInstance(levelFile, gameWindow, DISPLAY_DIMENSIONS);
            currentPanel = editorInstance.editorSpace;

            editorInstance.run();

            wantsToSwitchToEditor = false;
            wantsToSwitchToMenu = true;
        } else if (wantsToSwitchToMenu) {
            gameWindow.remove(currentPanel);
            for (KeyListener keyListener : gameWindow.getKeyListeners()) {
                gameWindow.removeKeyListener(keyListener);
            }

            MainMenuInstance mainMenuInstance = new MainMenuInstance(gameWindow, DISPLAY_DIMENSIONS, this);
            currentPanel = mainMenuInstance.mainMenu;
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
