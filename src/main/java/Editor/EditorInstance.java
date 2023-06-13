package Editor;

import Game.*;
import java.awt.*;

public class EditorInstance {
    private final Dimension DISPLAY_DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();
    GameWindow gameWindow = new GameWindow(DISPLAY_DIMENSIONS);
    EditorSpace editorSpace;
    Song song;
    int playbackTimestamp = 0;

    EditorInstance(Song song) {
        editorSpace = new EditorSpace(DISPLAY_DIMENSIONS, song);
        this.song = song;

        gameWindow.add(editorSpace);
        gameWindow.addMouseListener(new EditorMouseListener(this));

        gameWindow.addKeyListener(new EditorActionListener(this));
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
                editorSpace.editorNeedle.setPlaybackTimestamp((int)(song.audioClip.getMicrosecondPosition() / (1000000 / (song.bpm / 60))));
                if (song.playing && editorSpace.editorNeedle.isOutOfBounds()) {
                    editorSpace.editorTrack.globalTimeStamp = editorSpace.editorNeedle.playbackTimestamp;
                    editorSpace.editorTrack.updateNodes(editorSpace.editorTrack.globalTimeStamp);
                }
                delta--;
            }
        }
    }
}
