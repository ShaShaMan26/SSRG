import java.awt.*;
import java.util.ArrayList;

public class NoteAisle extends Component {
    Dimension displayDimensions;
    int height;
    int width;
    int id;
    int xPos;
    int receiverHeight;
    boolean active = false;
    ArrayList<Note> notes = new ArrayList<>();

    NoteAisle(Dimension displayDimensions, int id) {
        this.displayDimensions = displayDimensions;
        this.id = id;

        height = displayDimensions.height;

        // get 16:9 scaled width
        width = (displayDimensions.height / 9) * 16;
        // scale down width
        width = (int)(width * .35);
        // split into fours
        width /= 4;

        receiverHeight = width / 2;

        xPos = (displayDimensions.width / 2) - (width * 2) + (width * id);
    }

    public void update(int timeStamp, int amountOfTicks) {
        for (Note note : notes){
            note.move(timeStamp, amountOfTicks);
        }
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void paint(Graphics g) {
        super.paint(g);

        // aisle
        g.setColor(Color.DARK_GRAY);
        g.fillRect(xPos, 0, width, height);

        // receiver
        if (active) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillRect(xPos, height - receiverHeight, width, receiverHeight);

        double borderScale = .1;
        int borderNum = (int)(receiverHeight * borderScale);
        int innerXPos = xPos + borderNum;
        int innerYPos = (height - receiverHeight) + borderNum;

        g.setColor(Color.cyan);
        g.fillRect(innerXPos, innerYPos, width - (borderNum * 2), receiverHeight - (borderNum * 2));

        // notes
        for (Note note : notes) {
            note.paint(g);
        }
    }
}
