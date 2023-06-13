package Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorMouseListener extends Component implements MouseListener {
    EditorInstance editorInstance;

    EditorMouseListener(EditorInstance editorInstance) {
        this.editorInstance = editorInstance;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point mouseClickPos = MouseInfo.getPointerInfo().getLocation();

        for (EditorNode editorNode : editorInstance.editorSpace.editorTrack.displayedEditorNodes) {
            if (editorNode.checkForClick(mouseClickPos.x, mouseClickPos.y)) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    editorNode.toggleAdded();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    editorInstance.song.setMusicPos(editorNode.timeStamp / 2);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
