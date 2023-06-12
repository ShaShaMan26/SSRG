package Editor;

import Game.Song;

import javax.swing.*;
import java.awt.*;

public class EditorSpace extends JPanel {
    EditorTrack editorTrack;

    EditorSpace(Dimension displayDimensions, Song song) {
        editorTrack = new EditorTrack(displayDimensions, song);

        this.add(editorTrack);

        this.setSize(displayDimensions);
        this.setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Component component : this.getComponents()) {
            component.paint(g);
        }
    }
}
