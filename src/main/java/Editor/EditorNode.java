package Editor;

import java.awt.*;

public class EditorNode extends Component {
    int id;
    int timeStamp;
    boolean added = false;

    int xPos, yPos, width, height;

    EditorNode(int id, int timeStamp, int trackYPos, int width, int height) {
        this.width = width;
        this.height = height;

        this.id = id;
        this.timeStamp = timeStamp;

        this.yPos = (height * id) + trackYPos;
        this.xPos = width * timeStamp;
    }

    public void updateXPos(int globalTimeStamp) {
        xPos = width * (timeStamp - globalTimeStamp);
    }

    public boolean checkForClick(int xPos, int yPos) {
        return this.xPos <= xPos && xPos <= this.xPos + width
                && this.yPos <= yPos && yPos <= this.yPos + height;
    }

    public void toggleAdded() {
        added = !added;
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (added) {
            g.setColor(Color.GREEN);
            g.fillRect(xPos, yPos, width-1, height-1);
        }
    }
}
