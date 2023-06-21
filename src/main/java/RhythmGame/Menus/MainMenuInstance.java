package RhythmGame.Menus;

import RhythmGame.Game.GameWindow;
import RhythmGame.Instance;

public class MainMenuInstance {
    public MainMenu mainMenu;
    MainMenuKeyListener mainMenuKeyListener;

    public MainMenuInstance(GameWindow gameWindow, Instance instance) {
        mainMenu = new MainMenu(instance);
        mainMenuKeyListener = new MainMenuKeyListener(instance, this);
        gameWindow.add(mainMenu);
        gameWindow.addKeyListener(mainMenuKeyListener);

        gameWindow.requestFocus();
    }
}
