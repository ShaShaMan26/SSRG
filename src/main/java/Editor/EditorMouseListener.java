package Editor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorMouseListener extends Component implements MouseListener {
    EditorSpace editorSpace;

    EditorMouseListener(EditorSpace editorSpace) {
        this.editorSpace = editorSpace;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point mouseClickPos = MouseInfo.getPointerInfo().getLocation();

        for (EditorNode editorNode : editorSpace.editorTrack.editorNodes) {
            editorNode.checkForClick(mouseClickPos.x, mouseClickPos.y);
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
