package Editor;

import java.awt.*;

public class EditorNeedle extends Component {
    EditorTrack parentEditorTrack;
    int playbackTimestamp = 0;

    EditorNeedle(EditorTrack parentEditorTrack) {
        this.parentEditorTrack = parentEditorTrack;
    }

    public void setPlaybackTimestamp(int playbackTimestamp) {
        this.playbackTimestamp = playbackTimestamp;
    }

    public boolean isOutOfBounds() {
        int currentXPos = (playbackTimestamp - parentEditorTrack.globalTimeStamp) * parentEditorTrack.nodeWidth;
        return currentXPos > parentEditorTrack.width
                || currentXPos < 0;
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.CYAN);

        int timestampDelta = playbackTimestamp - parentEditorTrack.globalTimeStamp;
        g.drawLine(parentEditorTrack.nodeWidth * timestampDelta, parentEditorTrack.yPos,
                parentEditorTrack.nodeWidth * timestampDelta, parentEditorTrack.yPos + parentEditorTrack.height);
    }
}
