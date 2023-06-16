package RhythmGame.Editor;

import RhythmGame.Game.*;

import java.awt.*;
import java.util.ArrayList;

public class EditorTrack extends Component {
    int globalTimeStamp = 0;
    int yPos;
    int width;
    int height;
    int nodeHeight;
    int nodeWidth;
    int totalNumOfNodes;
    int scrollSpeed = 1;
    Song song;
    ArrayList<EditorNode> editorNodes = new ArrayList<>();
    ArrayList<EditorNode> displayedEditorNodes = new ArrayList<>();

    EditorTrack(Dimension displayDimensions, Song song) {
        this.song = song;

        width = displayDimensions.width;
        height = displayDimensions.height / 2;

        yPos = (int) (height - (height / 1.75));

        nodeHeight = height / 4;
        nodeWidth = nodeHeight / 2;
        totalNumOfNodes = (int) (song.length * (song.bpm / 60));

        for (int timeStamp = 0; timeStamp < totalNumOfNodes; timeStamp++) {
            for (int id = 0; id < 4; id++) {
                editorNodes.add(new EditorNode(id, timeStamp, yPos, nodeWidth, nodeHeight));
            }
        }

        populateNodes();

        updateDisplayedNodes();
    }


    public void populateNodes() {
        ArrayList<Note> notes = song.getNotes();
        for (Note note : notes) {
            for (EditorNode node : editorNodes) {
                if (note.id == node.id && note.timeStamp == node.timeStamp) {
                    node.toggleAdded();
                }
            }
        }
    }

    public void updateNodes(int globalTimeStamp) {
        this.globalTimeStamp = globalTimeStamp;

        for (EditorNode node : editorNodes) {
            node.updateXPos(globalTimeStamp);
        }

        updateDisplayedNodes();
    }

    public void updateDisplayedNodes() {
        displayedEditorNodes.clear();
        for (int i = 0; i < ((width / nodeWidth) * 4) + 4; i++) {
            displayedEditorNodes.add(editorNodes.get(i+(globalTimeStamp * 4)));
        }
    }

    public void increaseScrollSpeed() {
        if (scrollSpeed < 16) {
            scrollSpeed *= 2;
        }
    }

    public void decreaseScrollSpeed() {
        scrollSpeed /= 2;
        if (scrollSpeed < 2) {
            scrollSpeed = 1;
        }
    }

    public void moveBackward() {
        if (globalTimeStamp > 0) {
            globalTimeStamp -= scrollSpeed;
            if (globalTimeStamp < 0) {
                globalTimeStamp = 0;
            }
        }
        updateNodes(globalTimeStamp);
    }

    public void moveForward() {
        if (globalTimeStamp + (displayedEditorNodes.size() / 4) < totalNumOfNodes) {
            globalTimeStamp += scrollSpeed;
            if (globalTimeStamp > totalNumOfNodes - (displayedEditorNodes.size() / 4)) {
                globalTimeStamp = totalNumOfNodes - (displayedEditorNodes.size() / 4);
            }
        }
        updateNodes(globalTimeStamp);
    }

    private String formatToTime(int timeStamp) {
        StringBuilder str = new StringBuilder();

        str.append(timeStamp / 60);
        str.append(":");
        str.append(timeStamp - ((timeStamp / 60) * 60));

        if (str.length() < 4) {
            str.deleteCharAt(2);
            str.append("0");
            str.append(timeStamp - ((timeStamp / 60) * 60));
        }

        return str.toString();
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
        for (int i = 0; i <= displayedEditorNodes.size(); i++) {
            g.drawLine(nodeWidth * i, yPos, nodeWidth * i, yPos + height);
        }

        // nodes
        for (EditorNode editorNode : displayedEditorNodes) {
            editorNode.paint(g);
        }

        // scrollSpeed
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, height / 18));

        g.drawString(scrollSpeed + "x", 0, yPos - (height / 40));


        // timeline
        g.setFont(new Font("Arial", Font.PLAIN, height / 25));
        int scale = ((int) song.bpm / 60);

        for (int i = 0; i <= displayedEditorNodes.size() / scale; i++) {
            String timeStampString = formatToTime(i + Math.ceilDiv(globalTimeStamp, scale));

            int xPos = (nodeWidth * scale) * i;

            if (globalTimeStamp % 2 != 0) {
                xPos += nodeWidth;
            }

            g.drawString(timeStampString, xPos, yPos+(nodeHeight*4) + (height / 25));
        }
    }
}
