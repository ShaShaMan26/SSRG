package RhythmGame.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow(Dimension displayDimensions) {
        this.setTitle("SSRG");
        this.setSize(displayDimensions);
        this.setLayout(null);
        this.setUndecorated(true);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
