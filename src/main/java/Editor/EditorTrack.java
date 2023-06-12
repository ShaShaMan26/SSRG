package Editor;

import Game.Song;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.ArrayList;

public class EditorTrack extends Component {
    int globalTimeStamp = 0;
    int yPos;
    int width;
    int height;
    int nodeHeight;
    int nodeWidth;
    int numOfDisplayedNodes;
    Song song;
    ArrayList<EditorNode> editorNodes = new ArrayList<>();

    EditorTrack(Dimension displayDimensions, Song song) {
        this.song = song;

        width = displayDimensions.width;
        height = displayDimensions.height / 2;

        yPos = (int) ((displayDimensions.height / 2) - (height / 1.75));

        nodeHeight = height / 4;
        nodeWidth = nodeHeight / 2;

        numOfDisplayedNodes = width / nodeWidth;

        for (int timeStamp = 0; timeStamp < song.length; timeStamp++) {
            for (int id = 0; id < 4; id++) {
                editorNodes.add(new EditorNode(id, timeStamp, yPos, nodeWidth, nodeHeight));
            }
        }
    }

    public void updateNodes(int globalTimeStamp) {
        this.globalTimeStamp = globalTimeStamp;

        for (EditorNode node : editorNodes) {
            node.updateXPos(globalTimeStamp);
        }
    }

    public void moveBackward() {
        if (globalTimeStamp > 0) {
            globalTimeStamp--;
        }
        updateNodes(globalTimeStamp);
    }

    public void moveForward() {
        if (globalTimeStamp + numOfDisplayedNodes < song.length) {
            globalTimeStamp++;
        }
        updateNodes(globalTimeStamp);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, yPos, width, height);

        g.setColor(Color.WHITE);

        // grid pattern
        for (int i = 0; i <= height / nodeHeight; i++) {
            g.drawLine(0, yPos + (nodeHeight * i), width, yPos + (nodeHeight * i));
        }

        for (int i = 0; i <= numOfDisplayedNodes; i++) {
            g.drawLine(nodeWidth * i, yPos, nodeWidth * i, yPos + height);
        }

        // nodes
        for (int i = 0; i < editorNodes.size(); i++) {
            EditorNode currentEditorNode = editorNodes.get(i);

            if (currentEditorNode.added) {
                currentEditorNode.paint(g);
            }
        }
    }
}
