import java.awt.*;

public class Note extends Component {
    int id;
    int timeStamp;
    int yPos;
    NoteAisle parentAisle;

    Note(int id, int timeStamp) {
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public void setParentAisle(NoteAisle parentAisle) {
        this.parentAisle = parentAisle;
    }

    public void move(int globalTimeStamp, int amountOfTicks) {
        yPos = parentAisle.height - ((timeStamp*amountOfTicks) - globalTimeStamp);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLUE);
        g.fillRect(parentAisle.xPos, yPos, parentAisle.width, parentAisle.receiverHeight);

        double borderScale = .125;
        int borderNum = (int)(parentAisle.receiverHeight * borderScale);
        int innerXPos = parentAisle.xPos + borderNum;
        int innerYPos = yPos + borderNum;

        g.setColor(Color.ORANGE);
        g.fillRect(innerXPos, innerYPos, parentAisle.width - (borderNum * 2), parentAisle.receiverHeight - (borderNum * 2));
    }
}
