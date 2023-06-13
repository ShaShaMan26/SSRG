package Editor;

import Game.Song;

import javax.swing.*;
import java.awt.*;

public class EditorSpace extends JPanel {
    EditorTrack editorTrack;
    EditorNeedle editorNeedle;

    EditorSpace(Dimension displayDimensions, Song song) {
        editorTrack = new EditorTrack(displayDimensions, song);
        editorNeedle = new EditorNeedle(editorTrack);

        this.add(editorTrack);
        this.add(editorNeedle);

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
