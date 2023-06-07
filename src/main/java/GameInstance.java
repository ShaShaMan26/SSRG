import java.awt.*;

public class GameInstance {
    private final Dimension DISPLAY_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    GameWindow gameWindow = new GameWindow(DISPLAY_DIMENSIONS);
    GameSpace gameSpace = new GameSpace(DISPLAY_DIMENSIONS);


    GameInstance() {
        gameWindow.add(gameSpace);
        gameWindow.addKeyListener(gameSpace.noteReceiverController);
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                gameWindow.repaint();
                delta--;
            }
        }
    }
}
