package RhythmGame.Menus;

import RhythmGame.Game.GameWindow;
import RhythmGame.Instance;
import java.awt.*;

public class MainMenuInstance {
    public MainMenu mainMenu;
    MainMenuKeyListener mainMenuKeyListener;

    public MainMenuInstance(GameWindow gameWindow, Dimension displayDimensions, Instance instance) {
        mainMenu = new MainMenu(displayDimensions);
        mainMenuKeyListener = new MainMenuKeyListener(instance, this);
        gameWindow.add(mainMenu);
        gameWindow.addKeyListener(mainMenuKeyListener);
    }
}
